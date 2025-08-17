package leetcode

import scala.collection.mutable

object Solution {
  case class Journey(city: Int, cost: Int, stops: Int)

  def findCheapestPrice(n: Int,
                        flights: Array[Array[Int]],
                        src: Int,
                        dst: Int,
                        k: Int): Int = {
    var result = Int.MaxValue

    val destinationsFrom =
      flights
        .groupBy(_(0))
        .map {
          case (city, destinations) =>
            val toAndPrice = destinations.map(x => Array(x(1), x(2)))
            (city, toAndPrice)
        }

    val visited = Array.fill(n)(Int.MaxValue)
    val queue = mutable.Queue[Journey]()

    queue.enqueue(Journey(src, 0, k + 1))
    var found = false

    while (queue.nonEmpty) {
      val journey = queue.dequeue()

      if (journey.city == dst && journey.cost < result) {
        found = true
        result = journey.cost
      }

      if (journey.stops > 0) {
        for {
          destinations <- destinationsFrom.get(journey.city)
          Array(to, price) <- destinations
          if visited(to) > journey.cost + price
        } {
          visited(to) = journey.cost + price
          queue.enqueue(Journey(to, journey.cost + price, journey.stops - 1))
        }
      }
    }

    if (found) {
      result
    } else {
      -1
    }
  }
}
