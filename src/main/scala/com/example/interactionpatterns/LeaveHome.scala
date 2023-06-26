package com.example.interactionpatterns

import akka.NotUsed
//import akka.actor.typed.scaladsl.Behaviors
//
//// dummy data types just for this sample
//case class Keys()
//
//case class Wallet()
//
//
//object Home {
//  sealed trait Command
//  case class LeaveHome(who: String, replyTo: ActorRef[ReadyToLeaveHome]) extends Command
//  case class ReadyToLeaveHome(who: String, keys: Keys, wallet: Wallet)
//
//  def apply(): Behavior[Command] = {
//    Behaviors.setup[Command] { context =>
//      val keyCabinet: ActorRef[KeyCabinet.GetKeys] = context.spawn(KeyCabinet(), "key-cabinet")
//      val drawer: ActorRef[Drawer.GetWallet] = context.spawn(Drawer(), "drawer")
//
//      Behaviors.receiveMessage[Command] {
//        case LeaveHome(who, replyTo) =>
//          context.spawn(prepareToLeaveHome(who, replyTo, keyCabinet, drawer), s"leaving-$who")
//          Behaviors.same
//      }
//    }
//  }
//
//  // per session actor behavior
//  def prepareToLeaveHome(
//                          whoIsLeaving: String,
//                          replyTo: ActorRef[ReadyToLeaveHome],
//                          keyCabinet: ActorRef[KeyCabinet.GetKeys],
//                          drawer: ActorRef[Drawer.GetWallet]): Behavior[NotUsed] = {
//    // we don't _really_ care about the actor protocol here as nobody will send us
//    // messages except for responses to our queries, so we just accept any kind of message
//    // but narrow that to more limited types when we interact
//    Behaviors
//      .setup[AnyRef] { context =>
//        var wallet: Option[Wallet] = None
//        var keys: Option[Keys] = None
//
//        // we narrow the ActorRef type to any subtype of the actual type we accept
//        keyCabinet ! KeyCabinet.GetKeys(whoIsLeaving, context.self.narrow[Keys])
//        drawer ! Drawer.GetWallet(whoIsLeaving, context.self.narrow[Wallet])
//
//        def nextBehavior(): Behavior[AnyRef] =
//          (keys, wallet) match {
//            case (Some(w), Some(k)) =>
//              // we got both, "session" is completed!
//              replyTo ! ReadyToLeaveHome(whoIsLeaving, w, k)
//              Behaviors.stopped
//
//            case _ =>
//              Behaviors.same
//          }
//
//        Behaviors.receiveMessage {
//          case w: Wallet =>
//            wallet = Some(w)
//            nextBehavior()
//          case k: Keys =>
//            keys = Some(k)
//            nextBehavior()
//          case _ =>
//            Behaviors.unhandled
//        }
//      }
//      .narrow[NotUsed] // we don't let anyone else know we accept anything
//  }
//}
