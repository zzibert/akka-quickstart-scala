package leetcode

import scala.collection.mutable

object MinimumHeightTrees {
  def findMinHeightTrees(n: Int, edges: Array[Array[Int]]): List[Int] = {
    val destinations = mutable.Map[Int, mutable.ArrayBuffer[Int]]()
    val maxHeights = mutable.Map[(Int, Int), Int]()
    val maxHeightPerVertices = Array.fill(n)(0)
    var globalMin = Int.MaxValue
    val count = mutable.ListBuffer[Int]()

    edges foreach { edge =>
      val first = edge(0)
      val second = edge(1)

      val buffer1 = destinations.getOrElseUpdate(first, mutable.ArrayBuffer[Int]())
      buffer1.addOne(second)

      val buffer2 = destinations.getOrElseUpdate(second, mutable.ArrayBuffer[Int]())
      buffer2.addOne(first)
    }

    for (i <- 0 until n) {
      val maxHeight = findMaxHeight(i, List(i), maxHeights, destinations, 0)
      maxHeightPerVertices(i) = maxHeight
      if (maxHeight < globalMin) {
        globalMin = maxHeight
      }
    }

    maxHeightPerVertices.zipWithIndex foreach {
      case (height, index) =>
        if (height == globalMin) {
          count.addOne(index)
        }
    }

    count.toList
  }

  def findMaxHeight(root: Int, visited: List[Int], maxHeights: mutable.Map[(Int, Int), Int],  destinations: mutable.Map[Int, mutable.ArrayBuffer[Int]], height: Int): Int = {
    val potentials = destinations.getOrElse(root, mutable.ArrayBuffer.empty).filterNot(visited.contains)

    if (potentials.isEmpty) {
      height
    } else {
      val heights =
        potentials map { vertices =>
          maxHeights.get((root, vertices)) match {
            case Some(result) => result+height
            case None =>
              val result = findMaxHeight(vertices, visited.appended(vertices), maxHeights, destinations, 1)
              maxHeights += ((root, vertices) -> result)
              height+result
          }
        }
      heights.max
    }
  }
}
