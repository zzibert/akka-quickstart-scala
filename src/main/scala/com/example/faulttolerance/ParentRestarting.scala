package com.example.faulttolerance

//import akka.actor.typed.{ Behavior, PostStop, PreRestart, SupervisorStrategy }
//import akka.actor.typed.scaladsl.Behaviors
//
//def child(size: Long): Behavior[String] =
//  Behaviors.receiveMessage(msg => child(size + msg.length))
//
//def parent: Behavior[String] = {
//  Behaviors
//    .supervise[String] {
//      Behaviors.setup { ctx =>
//        val child1 = ctx.spawn(child(0), "child1")
//        val child2 = ctx.spawn(child(0), "child2")
//
//        Behaviors.receiveMessage[String] { msg =>
//          // message handling that might throw an exception
//          val parts = msg.split(" ")
//          child1 ! parts(0)
//          child2 ! parts(1)
//          Behaviors.same
//        }
//      }
//    }
//    .onFailure(SupervisorStrategy.restart)
//}
//
//def parent2: Behavior[String] = {
//  Behaviors.setup { ctx =>
//    val child1 = ctx.spawn(child(0), "child1")
//    val child2 = ctx.spawn(child(0), "child2")
//
//    // supervision strategy inside the setup to not recreate children on restart
//    Behaviors
//      .supervise {
//        Behaviors.receiveMessage[String] { msg =>
//          // message handling that might throw an exception
//          val parts = msg.split(" ")
//          child1 ! parts(0)
//          child2 ! parts(1)
//          Behaviors.same
//        }
//      }
//      .onFailure(SupervisorStrategy.restart.withStopChildren(false))
//  }
//}
