package leetcode

import scala.collection.mutable

class NonOverlappingInterval {
  case class Interval(start: Int, end: Int)
  def eraseOverlapIntervals(intervals: Array[Array[Int]]): Int = {
    var result = 0

    val buffer = mutable.ArrayBuffer[Interval]()

    val sorted = intervals.sortBy(x => (x(0), x(1))) foreach { interval =>
      buffer.addOne(Interval(start = interval(0), end = interval(1)))
    }

    buffer.foreach(println)

    var i = 0

    while (i < (buffer.length-1)) {
      if (doesOverlap(buffer(i), buffer(i+1))) {
        if (i+2 < buffer.length) {
          if (doesOverlap(buffer(i+1), buffer(i+2))) {
            buffer.remove(i+1)
            result += 1
          } else {
            buffer.remove(i)
            result += 1
          }
        } else {
          buffer.remove(i)
          result += 1
        }
      } else {
        i += 1
      }
    }

    result
  }

  def doesOverlap(first: Interval, second: Interval): Boolean = {
    first.end > second.start
  }


}
