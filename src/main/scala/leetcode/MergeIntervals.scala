package leetcode

import scala.collection.mutable

object Solution {
  def merge(intervals: Array[Array[Int]]): Array[Array[Int]] = {
    var i = 0
    val result = mutable.ArrayBuffer[Array[Int]]()
    val sortedIntervals = intervals.sortBy(_(0))

    while (i < sortedIntervals.length) {
      var interval = sortedIntervals(i)
      var j = i + 1
      while (j < intervals.length && sortedIntervals(j)(0) <= interval(1)) {
        val bigger = Math.max(interval(1), sortedIntervals(j)(1))
        val smaller = Math.min(interval(0), sortedIntervals(j)(0))
        interval = Array(smaller, bigger)
        i += 1
        j += 1
      }

      result.addOne(interval)
      i += 1
    }

    result.toArray
  }
}
