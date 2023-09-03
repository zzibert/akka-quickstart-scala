package leetcode

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object GraphValidTree {
  def validTree(n: Int, edges: Array[Array[Int]]): Boolean = {
    val roots = ArrayBuffer[Int]()

    for (i <- 0 until n) {
      roots.addOne(i)
    }

    edges foreach { edge =>
      val node0 = edge(0)
      val node1 = edge(1)

      if (roots(node0) == roots(node1)) {
        return false
      }

      if (roots(node0) < roots(node1)) {
        for (i <- 1 until n) {
          if (roots(i) == roots(node1)) {
            roots(i) = roots(node0)
          }
        }
      } else {
        for (i <- 1 until n) {
          if (roots(i) == roots(node0)) {
            roots(i) = roots(node1)
          }
        }
      }

    }

    roots.distinct.length == 1
  }

}
