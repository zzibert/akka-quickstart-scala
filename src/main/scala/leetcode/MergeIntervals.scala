package leetcode

import scala.collection.mutable.ArrayBuffer

object MergeIntervals {

  implicit val ordering: Ordering[Array[Int]] = Ordering.by { array =>
    array(0)
  }
  def merge(intervals: Array[Array[Int]]): Array[Array[Int]] = {
    val intervalsSorted = ArrayBuffer.from(intervals.sorted)

    var i = 0

    while (i < (intervalsSorted.length-1)) {
      if (intervalsSorted(i)(1) >= intervalsSorted(i+1)(1)) {
        intervalsSorted.remove(i+1)
      } else if (intervalsSorted(i)(1) >= intervalsSorted(i+1)(0)) {
        val newValue =
          if (intervalsSorted(i)(1) >= intervalsSorted(i+1)(1)) {
            intervalsSorted(i)(1)
          } else {
            intervalsSorted(i+1)(1)
          }
        intervalsSorted(i)(1) = newValue
        intervalsSorted.remove(i+1)
      } else {
        i += 1
      }
    }

    intervalsSorted.toArray
  }
}
