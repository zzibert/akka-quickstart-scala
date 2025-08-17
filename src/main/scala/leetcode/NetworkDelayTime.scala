package leetcode

import scala.collection.mutable

object Solution {
  case class Journey(node: Int, time: Int)

  def networkDelayTime(times: Array[Array[Int]], n: Int, k: Int): Int = {

    val minimumTimePerNode = Array.fill(n)(Int.MaxValue)
    minimumTimePerNode(k-1) = 0
    val queue = mutable.Queue[Journey]()

    val destinations =
      times
        .groupBy(_(0))
        .map { case (source, paths) =>
          val toAndTime = paths.map(x => Array(x(1)-1, x(2)))
          (source-1, toAndTime)
        }

    queue.enqueue(Journey(k-1, 0))

    while (queue.nonEmpty) {
      val journey = queue.dequeue()
      for {
        reachableNodes <- destinations.get(journey.node)
        Array(to, time) <- reachableNodes
        if minimumTimePerNode(to) > journey.time + time
      } {
        minimumTimePerNode(to) = journey.time + time
        queue.enqueue(Journey(to, journey.time + time))
      }
    }

    val maxDuration = minimumTimePerNode.max

    if (maxDuration == Int.MaxValue) -1 else maxDuration
  }
}
