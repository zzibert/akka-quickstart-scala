package com.example.interactionpatterns

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

// a sharded actor that needs counter updates
object CounterConsumer {
  sealed trait Command
  final case class NewCount(count: Long) extends Command
  val TypeKey: EntityTypeKey[Command] = EntityTypeKey[Command]("example-sharded-response")
}

// a sharded counter that sends responses to another sharded actor
object Counter {
  trait Command
  case object Increment extends Command
  final case class GetValue(replyToEntityId: String) extends Command
  val TypeKey: EntityTypeKey[Command] = EntityTypeKey[Command]("example-sharded-counter")

  private def apply(): Behavior[Command] =
    Behaviors.setup { context =>
      counter(ClusterSharding(context.system), 0)
    }

  private def counter(sharding: ClusterSharding, value: Long): Behavior[Command] =
    Behaviors.receiveMessage {
      case Increment =>
        counter(sharding, value + 1)
      case GetValue(replyToEntityId) =>
        val replyToEntityRef = sharding.entityRefFor(CounterConsumer.TypeKey, replyToEntityId)
        replyToEntityRef ! CounterConsumer.NewCount(value)
        Behaviors.same
    }

}
