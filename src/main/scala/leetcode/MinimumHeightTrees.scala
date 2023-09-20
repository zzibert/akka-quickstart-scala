package leetcode

import scala.collection.mutable

object MinimumHeightTrees {
  def findMinHeightTrees(n: Int, edges: Array[Array[Int]]): List[Int] = {
    val destinations = mutable.Map[Int, Array[Int]]()
    val maxHeightPerVertices = mutable.Map[Int, Int]()
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
      findMaxHeight(i, i, i, maxHeightPerVertices, destinations, 0)
    }

    val globalMin = maxHeightPerVertices.values.min

    maxHeightPerVertices.toList foreach {
      case (node, height) =>
        if (height == globalMin) {
          count.addOne(node)
        }
    }

    count.toList
  }
  def findMaxHeight(origin: Int, root: Int, rootParent: Int, maxHeightPerVertices: mutable.Map[Int, Int],  destinations: mutable.Map[Int, Array[Int]], height: Int): Unit = {
    maxHeightPerVertices.get(root) match {
      case Some(maxHeight) =>
        val currentHeight = height + maxHeight
        if (maxHeightPerVertices.get(origin).forall(_ < currentHeight)) {
          maxHeightPerVertices += (origin -> currentHeight)
        }

      case None =>
        val potentials = destinations.getOrElse(root, Array.empty).filterNot(_ == rootParent)

        if (potentials.isEmpty) {
          if (maxHeightPerVertices.get(origin).forall(_ < height)) {
            maxHeightPerVertices += (origin -> height)
          }
        } else {
          potentials foreach { vertices =>
            findMaxHeight(origin, vertices, root, maxHeightPerVertices, destinations, height + 1)
          }
        }
    }
  }
}
