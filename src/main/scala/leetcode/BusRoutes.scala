package leetcode

import scala.collection.mutable

object BusRoutes {
  def numBusesToDestination(routes: Array[Array[Int]], source: Int, target: Int): Int = {
    if (source == target) {
      return 0
    }

    val destinations = mutable.Map[Int, mutable.ArrayBuffer[Array[Int]]]()

    val queue = mutable.Queue[(Int, Array[Int])]()

    routes foreach { route =>
      route foreach { stop =>
        val buffer = destinations.getOrElseUpdate(stop, mutable.ArrayBuffer[Array[Int]]())
        buffer.addOne(route)
      }
    }

    val initial = routes.filter(_.contains(source)).flatten.distinct

    queue.enqueue((1, initial))

    while (queue.nonEmpty) {
      val (counter, current) = queue.dequeue()
      if (current.contains(target)) {
        return counter
      } else {
        current foreach { stop =>
          destinations.get(stop) match {
            case Some(stops) =>
              stops.view.map((counter+1, _)).foreach(queue.enqueue)
              destinations -= stop

            case None =>
          }
        }
      }
    }

    -1
  }
}
