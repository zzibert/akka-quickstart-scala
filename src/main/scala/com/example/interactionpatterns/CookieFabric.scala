//package com.example.interactionpatterns
//
//import akka.actor.typed.scaladsl.Behaviors
//import akka.actor.typed.scaladsl.LoggerOps
//import akka.actor.typed.{ ActorRef, ActorSystem, Behavior, PostStop, Terminated }
//import scala.concurrent.Future
//import scala.concurrent.duration.DurationInt
//
//object CookieFabric {
//  sealed trait Command
//  case class GiveMeCookies(count: Int, replyTo: ActorRef[Reply]) extends Command
//
//  sealed trait Reply
//  case class Cookies(count: Int) extends Reply
//  case class InvalidRequest(reason: String) extends Reply
//
//  def apply(): Behaviors.Receive[CookieFabric.GiveMeCookies] =
//    Behaviors.receiveMessage { message =>
//      if (message.count >= 5)
//        message.replyTo ! InvalidRequest("Too many cookies.")
//      else
//        message.replyTo ! Cookies(message.count)
//      Behaviors.same
//    }
//}
//
//import akka.actor.typed.scaladsl.AskPattern._
//import akka.util.Timeout
//
//// asking someone requires a timeout if the timeout hits without response
//// the ask is failed with a TimeoutException
//implicit val timeout: Timeout = 3.seconds
//// implicit ActorSystem in scope
//implicit val system: ActorSystem[_] = theSystem
//
//val result: Future[CookieFabric.Reply] = cookieFabric.ask(ref => CookieFabric.GiveMeCookies(3, ref))
//
//// the response callback will be executed on this execution context
//implicit val ec = system.executionContext
//
//result.onComplete {
//  case Success(CookieFabric.Cookies(count))         => println(s"Yay, $count cookies!")
//  case Success(CookieFabric.InvalidRequest(reason)) => println(s"No cookies for me. $reason")
//  case Failure(ex)                                  => println(s"Boo! didn't get cookies: ${ex.getMessage}")
//}
