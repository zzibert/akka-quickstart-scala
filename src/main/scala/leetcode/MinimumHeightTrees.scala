package leetcode

import scala.collection.mutable

object MinimumHeightTrees {
  def findMinHeightTrees(n: Int, edges: Array[Array[Int]]): List[Int] = {
    val destinations = mutable.Map[Int, mutable.ArrayBuffer[Int]]()
    val maxHeightPerVertices = Array.fill(n)(0)
    var globalMin = Int.MaxValue
    val count = mutable.ListBuffer[Int]()

    // TODO: Store previous max heights per vertices

    edges foreach { edge =>
      val first = edge(0)
      val second = edge(1)

      val buffer1 = destinations.getOrElseUpdate(first, mutable.ArrayBuffer[Int]())
      buffer1.addOne(second)

      val buffer2 = destinations.getOrElseUpdate(second, mutable.ArrayBuffer[Int]())
      buffer2.addOne(first)
    }

    for (i <- 0 until n) {
      val maxHeight = findMaxHeight(i, List(i), destinations, 0)
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

  def findMaxHeight(root: Int, visited: List[Int], destinations: mutable.Map[Int, mutable.ArrayBuffer[Int]], height: Int): Int = {
    val potentials = destinations.getOrElse(root, mutable.ArrayBuffer.empty).filterNot(visited.contains)

    if (potentials.isEmpty) {
      height
    } else {
      val heights = potentials.map(vertices => findMaxHeight(vertices, visited.appended(vertices), destinations, height+1))
      heights.max
    }
  }
}
