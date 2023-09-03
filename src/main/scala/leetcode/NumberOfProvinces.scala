package leetcode

import scala.collection.mutable.ArrayBuffer

object NumberOfProvinces {
  def findCircleNum(isConnected: Array[Array[Int]]): Int = {
    val roots = mutable.ArrayBuffer[Int]()
    val length = isConnected.length
    var count = length

    for (i <- 0 until length) {
      roots.addOne(i)
    }

    for {
      i <- 0 until length
      j <- 0 until length
    } {
      if (isConnected(i)(j) == 1) {
        if (roots(i) != roots(j)) {
          count -= 1
          val newRoot = roots(i)
          val replaceRoot = roots(j)

          for (k <- 0 until length) {
            if (roots(k) == replaceRoot) {
              roots(k) = newRoot
            }
          }
        }
      }
    }

    count
  }
}
