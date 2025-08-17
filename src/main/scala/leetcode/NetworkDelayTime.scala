package leetcode

import scala.collection.mutable

object Solution {
  case class Journey(to: Int, time: Int)

  def networkDelayTime(times: Array[Array[Int]], n: Int, k: Int): Int = {
    implicit val ordering: Ordering[Journey] = Ordering.by(_.time)
    val queue = mutable.PriorityQueue[Journey]()(ordering.reverse)
    val minimumTimeToNode = Array.fill(n)(Int.MaxValue)
    minimumTimeToNode(k-1) = 0

    val visited = Array.fill(n)(false)

    val destinations =
      times
        .groupBy(_(0))
        .map { case (source, trips) =>
          val toAndTime = trips.map(x => Array(x(1)-1, x(2)))
          (source - 1, toAndTime)
        }

    queue.enqueue(Journey(k-1, 0))

    while (queue.nonEmpty) {
      val journey = queue.dequeue()
      visited(journey.to) = true
      for {
        journeys <- destinations.get(journey.to)
        Array(to, time) <- journeys
        if !visited(to)
      } {
        if (minimumTimeToNode(to) > journey.time + time) {
          minimumTimeToNode(to) = journey.time + time
        }

        queue.enqueue(Journey(to, journey.time + time))
      }
    }

    val result = minimumTimeToNode.max

    if (result == Int.MaxValue) {
      -1
    } else {
      result
    }
  }
}
