package com.example

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ ActorRef, ActorSystem, Behavior }
import com.example.faulttolerance.Protocol._
import com.example.faulttolerance._
import com.example.interactionpatterns.Printer

object HelloWorldMain {

  final case class SayHello(name: String)

  def apply(): Behavior[SayHello] =
    Behaviors.setup { context =>
      val greeter = context.spawn(HelloWorld(), "greeter")

      Behaviors.receiveMessage { message =>
        val replyTo = context.spawn(HelloWorldBot(max = 3), message.name)
        greeter ! HelloWorld.Greet(message.name, replyTo)
        Behaviors.same
      }
    }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem(Boss(), "fire-and-forget-sample")

    // note how the system is also the top level actor ref
    val boss: ActorRef[Command] = system

    boss ! Fail("trololo")
  }
}
