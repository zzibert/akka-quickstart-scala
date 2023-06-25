package com.example.interactionpatterns

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ ActorRef, ActorSystem, Behavior, PostStop, Terminated }
import akka.pattern.StatusReply
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.util.{ Failure, Success }

object CookieFabric {
  sealed trait Command
  case class GiveMeCookies(count: Int, replyTo: ActorRef[StatusReply[Cookies]]) extends Command
  case class Cookies(count: Int)

  def apply(): Behaviors.Receive[CookieFabric.GiveMeCookies] =
    Behaviors.receiveMessage { message =>
      if (message.count >= 5)
        message.replyTo ! StatusReply.Error("Too many cookies.")
      else
        message.replyTo ! StatusReply.Success(Cookies(message.count))
      Behaviors.same
    }
}

import akka.actor.typed.scaladsl.AskPattern._
import akka.util.Timeout

// asking someone requires a timeout if the timeout hits without response
// the ask is failed with a TimeoutException
implicit val timeout: Timeout = 3.seconds
// implicit ActorSystem in scope
implicit val system: ActorSystem[_] = theSystem

val result: Future[CookieFabric.Cookies] = cookieFabric.askWithStatus(ref => CookieFabric.GiveMeCookies(3, ref))

// the response callback will be executed on this execution context
implicit val ec = system.executionContext

result.onComplete {
  case Success(CookieFabric.Cookies(count))      => println(s"Yay, $count cookies!")
  case Failure(StatusReply.ErrorMessage(reason)) => println(s"No cookies for me. $reason")
  case Failure(ex)                               => println(s"Boo! didn't get cookies: ${ex.getMessage}")
}
