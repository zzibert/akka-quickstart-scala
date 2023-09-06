package leetcode

import scala.collection.mutable

object FindIfPathExistsInGraph {
  def validPath(n: Int, edges: Array[Array[Int]], source: Int, destination: Int): Boolean = {
    if (source == destination) {
      return true
    }

    val roots = mutable.ArrayBuffer[Int]()

    for (i <- 0 until n) {
      roots.addOne(i)
    }

    edges foreach { edge =>
      val root1 = roots(edge(0))
      val root2 = roots(edge(1))

      if (root1 != root2) {
        val newRoot = root1
        val replaceRoot = root2

        for (i <- 0 until n) {
          if (roots(i) == replaceRoot) {
            roots(i) = newRoot
          }
        }
      }
    }

    roots(source) == roots(destination)
  }
}
