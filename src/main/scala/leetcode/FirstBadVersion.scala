package leetcode

import scala.collection.mutable.ArrayBuffer

/* The isBadVersion API is defined in the parent class VersionControl.
      def isBadVersion(version: Int): Boolean = {} */

object FirstBadVersion extends  {
  def firstBadVersion(n: Int): Int = {
    var low = 0
    var high = n

    while(true) {
      if (high - low < 3) {
        for (i <- low to high) {
          if (isBadVersion(i)) {
            return i
          }
        }
      }

      val middle = ((high - low) / 2) + low

      if (isBadVersion(middle)) {
        high = middle
      } else {
        low = middle
      }
    }

    0
  }

  def firstBadVersionHelper(low: Int, high: Int): Int = {
    if (high - low < 3) {
      for (i <- low to high) {
        if (isBadVersion(i)) {
          return i
        }
      }
    }

    val middle = (high - low) / 2

    if (isBadVersion(low+middle)) {
      firstBadVersionHelper(low, middle)
    } else {
      firstBadVersionHelper(middle, high)
    }
  }
}
