package leetcode

import scala.collection.mutable

object MinimumHeightTrees {
  def findMinHeightTrees(n: Int, edges: Array[Array[Int]]): List[Int] = {
    val destinations = mutable.Map[Int, Array[Int]]()
    val maxHeights = mutable.Map[(Int, Int), Int]()
    val maxHeightPerVertices = Array.fill(n)(0)
    var globalMin = Int.MaxValue
    val count = mutable.ListBuffer[Int]()

    edges foreach { edge =>
      val first = edge(0)
      val second = edge(1)

      val buffer1 = destinations.getOrElseUpdate(first, Array[Int]())
      destinations += (first -> (buffer1.appended(second)))

      val buffer2 = destinations.getOrElseUpdate(second, Array[Int]())
      destinations += (second -> (buffer2.appended(first)))
    }

    for (i <- 0 until n) {
      val maxHeight = findMaxHeight(i, i, maxHeights, destinations, 0)
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
  def findMaxHeight(root: Int, rootParent: Int, maxHeights: mutable.Map[(Int, Int), Int],  destinations: mutable.Map[Int, Array[Int]], height: Int): Int = {
    val potentials = destinations.getOrElse(root, Array.empty).filterNot(_ == rootParent)

    if (potentials.isEmpty) {
      height
    } else {
      val heights =
        potentials map { vertices =>
          maxHeights.get((root, vertices)) match {
            case Some(result) => result+height
            case None =>
              val result = findMaxHeight(vertices, root, maxHeights, destinations, 1)
              maxHeights += ((root, vertices) -> result)
              height+result
          }
        }
      heights.max
    }
  }
}
