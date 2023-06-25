package com.example.interactionpatterns

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ ActorRef, ActorSystem, Behavior, PostStop, Terminated }
import akka.pattern.StatusReply
import akka.util.Timeout
import scala.concurrent.duration.DurationInt
import scala.util.{ Failure, Success }

object Hal {
  sealed trait Command
  case class OpenThePodBayDoorsPlease(replyTo: ActorRef[StatusReply[String]]) extends Command

  def apply(): Behaviors.Receive[Hal.Command] =
    Behaviors.receiveMessage[Command] {
      case OpenThePodBayDoorsPlease(replyTo) =>
        // reply with a validation error description
        replyTo ! StatusReply.Error("I'm sorry, Dave. I'm afraid I can't do that.")
        Behaviors.same
    }
}

object Dave {

  sealed trait Command
  // this is a part of the protocol that is internal to the actor itself
  private case class AdaptedResponse(message: String) extends Command

  def apply(hal: ActorRef[Hal.Command]): Behavior[Dave.Command] =
    Behaviors.setup[Command] { context =>
      // asking someone requires a timeout, if the timeout hits without response
      // the ask is failed with a TimeoutException
      implicit val timeout: Timeout = 3.seconds

      // A StatusReply.Success(m) ends up as a Success(m) here, while a
      // StatusReply.Error(text) becomes a Failure(ErrorMessage(text))
      context.askWithStatus(hal, Hal.OpenThePodBayDoorsPlease.apply) {
        case Success(message)                        => AdaptedResponse(message)
        case Failure(StatusReply.ErrorMessage(text)) => AdaptedResponse(s"Request denied: $text")
        case Failure(_)                              => AdaptedResponse("Request failed")
      }

      Behaviors.receiveMessage {
        // the adapted message ends up being processed like any other
        // message sent to the actor
        case AdaptedResponse(message) =>
          context.log.info("Got response from hal: {}", message)
          Behaviors.same
      }
    }
}
