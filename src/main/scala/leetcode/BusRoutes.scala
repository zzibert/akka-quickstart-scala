package leetcode

import scala.collection.mutable

object BusRoutes {
  def numBusesToDestination(routes: Array[Array[Int]], source: Int, target: Int): Int = {
    if (source == target) {
      return 0
    }

    val visited = mutable.Map[Int, Boolean]()

    val destinations = mutable.Map[Int, Array[Int]]()

    val queue = mutable.Queue[(Int, Int)]()

    routes foreach { route =>
      route foreach { stop =>
        var buffer = destinations.getOrElseUpdate(stop, Array[Int]())
        route foreach { element =>
          buffer = buffer :+ element
        }

        destinations.update(stop, buffer.distinct)
      }
    }

    destinations.get(source) match {
      case Some(initials) =>
        initials foreach { initial =>
          queue.enqueue((1, initial))
        }

      case None =>
    }

    while (queue.nonEmpty) {
      val (counter, current) = queue.dequeue()
      if (current == target) {
        return counter
      } else {
        destinations.get(current) match {
          case Some(stops) =>
            stops foreach { stop =>
              visited.get(stop) match {
                case Some(_) =>
                case None =>
                  queue.enqueue((counter+1, stop))
                  visited.update(stop, true)
              }
              destinations -= current
            }

          case None =>
        }
      }
    }

    -1
  }
}
