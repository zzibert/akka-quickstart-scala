package leetcode

import scala.collection.mutable.ArrayBuffer

object TheEarliestMomentWhenEveryoneBecomeFriends {
  def earliestAcq(logs: Array[Array[Int]], n: Int): Int = {
    val sortedLogs = logs.sortBy(_(0))
    val roots = ArrayBuffer[Int]()
    var numberOfDisjointSets = n

    for (i <- 0 until n) {
      roots.addOne(i)
    }

    sortedLogs foreach { log =>
      val friendA = log(1)
      val friendB = log(2)

      if (roots(friendA) != roots(friendB)) {
        val newRoot = roots(friendA)
        val oldRoot = roots(friendB)

        for (i <- 0 until n) {
          if (roots(i) == oldRoot) {
            roots(i) = newRoot
          }
        }
        numberOfDisjointSets -= 1
        if (numberOfDisjointSets == 1) {
          return log(0)
        }
      }
    }

    -1
  }
}
