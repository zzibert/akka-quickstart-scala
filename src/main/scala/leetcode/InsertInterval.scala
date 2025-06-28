package leetcode

object Solution {

  def insert(intervals: Array[Array[Int]],
             newInterval: Array[Int]): Array[Array[Int]] = {
    var newArray = Array[Array[Int]]()

    if (intervals.length == 0) {
      merge(Array(newInterval))
    } else {
      var index = 0
      var i = 0
      while (i < intervals.length && intervals(i)(0) < newInterval(0)) {
        i += 1
      }

      var newIntervals = intervals.take(i) :+ newInterval

      newIntervals = newIntervals ++ intervals.drop(i)

      merge(newIntervals)
    }
  }

  def merge(intervals: Array[Array[Int]]): Array[Array[Int]] = {
    var i = 0
    var newArray = Array[Array[Int]]()

    while (i < intervals.length) {
      var interval = intervals(i)
      var counter = 0
      var j = i + 1

      while (j < intervals.length && interval(1) >= intervals(j)(0)) {
        val first = interval(0)
        val second = Math.max(interval(1), intervals(j)(1))
        interval = Array(first, second)
        counter += 1
        j += 1
      }

      newArray = newArray :+ interval
      i += (counter + 1)

    }

    newArray
  }
}
