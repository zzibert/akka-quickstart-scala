package com.example.faulttolerance

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.DeathPactException
import akka.actor.typed.SupervisorStrategy
import akka.actor.typed.scaladsl.Behaviors

object Protocol {
  sealed trait Command
  case class Fail(text: String) extends Command
  case class Hello(text: String, replyTo: ActorRef[String]) extends Command
}
import Protocol._

object Worker {
  def apply(): Behavior[Command] =
    Behaviors.receiveMessage {
      case Fail(text) =>
        throw new RuntimeException(text)
      case Hello(text, replyTo) =>
        replyTo ! text
        Behaviors.same
    }
}

object MiddleManagement {
  def apply(): Behavior[Command] =
    Behaviors.setup[Command] { context =>
      context.log.info("Middle management starting up")
      // default supervision of child, meaning that it will stop on failure
      val child = context.spawn(Worker(), "child")
      // we want to know when the child terminates, but since we do not handle
      // the Terminated signal, we will in turn fail on child termination
      context.watch(child)

      // here we don't handle Terminated at all which means that
      // when the child fails or stops gracefully this actor will
      // fail with a DeathPactException
      Behaviors.receiveMessage { message =>
        child ! message
        Behaviors.same
      }
    }
}

object Boss {
  def apply(): Behavior[Command] =
    Behaviors
      .supervise(Behaviors.setup[Command] { context =>
        context.log.info("Boss starting up")
        // default supervision of child, meaning that it will stop on failure
        val middleManagement = context.spawn(MiddleManagement(), "middle-management")
        context.watch(middleManagement)

        // here we don't handle Terminated at all which means that
        // when middle management fails with a DeathPactException
        // this actor will also fail
        Behaviors.receiveMessage[Command] { message =>
          middleManagement ! message
          Behaviors.same
        }
      })
      .onFailure[DeathPactException](SupervisorStrategy.restart)
}
