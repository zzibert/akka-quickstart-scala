package leetcode

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object GraphValidTree {
  def validTree(n: Int, edges: Array[Array[Int]]): Boolean = {
    val roots = ArrayBuffer[Int]()
    var sets = n

    for (i <- 0 until n) {
      roots.addOne(i)
    }

    edges foreach { edge =>
      val node0 = edge(0)
      val node1 = edge(1)

      if (roots(node1) == roots(node0)) {
        return false
      }

      union(node0, node1, roots)
      sets -= 1
    }

    sets == 1
  }

  def union(node0: Int, node1: Int, roots: ArrayBuffer[Int]): Unit = {
    val newRoot = roots(node0)
    val replaceRoot = roots(node1)

    for (i <- 0 until roots.length) {
      if (roots(i) == replaceRoot) {
        roots(i) = newRoot
      }
    }
  }

}
