package leetcode

import scala.collection.mutable.ArrayBuffer

object NumberOfConnectedComponentsInAnUndirectedGraph {
  def countComponents(n: Int, edges: Array[Array[Int]]): Int = {
    val roots = ArrayBuffer[Int]()

    for (i <- 0 until n) {
      roots.addOne(i)
    }

    edges foreach { edge =>
      val node0 = edge(0)
      val node1 = edge(1)

      union(node0, node1, roots)
    }


    roots.distinct.length
  }

  def union(node0: Int, node1: Int, roots: ArrayBuffer[Int]): Unit = {
    if (roots(node0) != roots(node1)) {
      val newRoot = roots(node0)
      val oldRoot = roots(node1)

      for (i <- 0 until roots.length) {
        if (roots(i) == oldRoot) {
          roots(i) = newRoot
        }
      }
    }
  }
}
