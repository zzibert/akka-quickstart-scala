package leetcode

import scala.collection.mutable

object Solution {
  def findOrder(numCourses: Int,
                prerequisites: Array[Array[Int]]): Array[Int] = {
    val allowedCourses = mutable.Queue[Int]()
    val inDegree = Array.fill(numCourses)(0)
    val graph = Array.fill(numCourses)(List[Int]())
    val result = mutable.ArrayBuffer[Int]()

    prerequisites foreach {
      case Array(node, vertice) =>
        inDegree(node) += 1
        graph(vertice) = node :: graph(vertice)
    }

    for (i <- 0 until numCourses) {
      if (inDegree(i) == 0) {
        allowedCourses.enqueue(i)
      }
    }

    while (allowedCourses.nonEmpty) {
      val removedClass = allowedCourses.dequeue()
      result.addOne(removedClass)
      val freedNodes = graph(removedClass)
      freedNodes.foreach { node =>
        inDegree(node) -= 1
        if (inDegree(node) == 0) {
          allowedCourses.enqueue(node)
        }
      }
    }

    if (result.length == numCourses) {
      result.toArray
    } else {
      Array()
    }
  }
}
