package com.example.lifecycle

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.scaladsl.LoggerOps
import akka.actor.typed.{ ActorRef, ActorSystem, Behavior, PostStop, Terminated }

object MasterControlProgram {
  sealed trait Command
  final case class SpawnJob(name: String, replyToWhenDone: ActorRef[JobDone]) extends Command

  final case class JobDone(name: String)

  private final case class JobTerminated(name: String, replyToWhenDone: ActorRef[JobDone]) extends Command

  case object GracefulShutdown extends Command

  def apply(): Behavior[Command] = {
    Behaviors
      .receive[Command] { (context, message) =>
        message match {
          case SpawnJob(jobName, replyToWhenDone) =>
            context.log.info("Spawning job {}!", jobName)
            val job = context.spawn(Job(jobName), name = jobName)
            context.watchWith(job, JobTerminated(jobName, replyToWhenDone))
            Behaviors.same
          case JobTerminated(jobName, replyToWhenDone) =>
            context.log.info("Job stopped: {}", jobName)
            replyToWhenDone ! JobDone(jobName)
            Behaviors.same
        }
      }
      .receiveSignal {
        case (context, PostStop) =>
          context.log.info("Master Control Program stopped")
          Behaviors.same
      }
  }
}

object Job {
  sealed trait Command

  def apply(name: String): Behavior[Command] = {
    Behaviors.receiveSignal[Command] {
      case (context, PostStop) =>
        context.log.info("Worker {} stopped", name)
        Behaviors.same
    }
  }
}
