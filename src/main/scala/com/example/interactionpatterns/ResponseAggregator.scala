package com.example.interactionpatterns

//import akka.actor.typed.{ ActorRef, Behavior }
//import akka.actor.typed.scaladsl.Behaviors
//import scala.concurrent.duration.DurationInt
//
//object Hotel1 {
//  final case class RequestQuote(replyTo: ActorRef[Quote])
//  final case class Quote(hotel: String, price: BigDecimal)
//}
//object Hotel2 {
//  final case class RequestPrice(replyTo: ActorRef[Price])
//  final case class Price(hotel: String, price: BigDecimal)
//}
//
//// Any since no common type between Hotel1 and Hotel2
//type Reply = Any
//
//object HotelCustomer {
//  sealed trait Command
//  final case class Quote(hotel: String, price: BigDecimal)
//  final case class AggregatedQuotes(quotes: List[Quote]) extends Command
//
//  def apply(hotel1: ActorRef[Hotel1.RequestQuote], hotel2: ActorRef[Hotel2.RequestPrice]): Behavior[Command] = {
//
//    Behaviors.setup[Command] { context =>
//      context.spawnAnonymous(
//        Aggregator[Reply, AggregatedQuotes](
//          sendRequests = { replyTo =>
//            hotel1 ! Hotel1.RequestQuote(replyTo)
//            hotel2 ! Hotel2.RequestPrice(replyTo)
//          },
//          expectedReplies = 2,
//          context.self,
//          aggregateReplies = replies =>
//            // The hotels have different protocols with different replies,
//            // convert them to `HotelCustomer.Quote` that this actor understands.
//            AggregatedQuotes(
//              replies
//                .map {
//                  case Hotel1.Quote(hotel, price) => Quote(hotel, price)
//                  case Hotel2.Price(hotel, price) => Quote(hotel, price)
//                  case unknown                    => throw new RuntimeException(s"Unknown reply $unknown")
//                }
//                .sortBy(_.price)
//                .toList),
//          timeout = 5.seconds))
//
//      Behaviors.receiveMessage {
//        case AggregatedQuotes(quotes) =>
//          context.log.info("Best {}", quotes.headOption.getOrElse("Quote N/A"))
//          Behaviors.same
//      }
//    }
//  }
//}
