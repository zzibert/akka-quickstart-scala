package leetcode

import scala.collection.mutable

class NonOverlappingInterval {
  case class Interval(start: Int, end: Int)
  def eraseOverlapIntervals(intervals: Array[Array[Int]]): Int = {
    var result = 0

    val buffer = mutable.ArrayBuffer[Interval]()

    intervals.sortBy(x => (x(0), x(1))) foreach { interval =>
      buffer.addOne(Interval(start = interval(0), end = interval(1)))
    }

    buffer.foreach(println)

    var i = 0

    while (i < (buffer.length-1)) {
      val j = i+1
      while (j < buffer.length && sameStart(buffer(i), buffer(j))) {
        buffer.remove(j)
        result += 1
      }

      i += 1
    }

    while (true) {
      val overlaps = Array.fill(buffer.length)(0)
      for (i <- 0 until buffer.length) {
        var numberOfOverlaps = 0
        var j = i + 1
        while (j < buffer.length && doesOverlap(buffer(i), buffer(j))) {
          overlaps(i) += 1
          overlaps(j) += 1
          numberOfOverlaps += 1
          j += 1
        }
      }

      val maxOverlaps = overlaps.zipWithIndex.maxBy(x => x._1)
      if (maxOverlaps._1 < 1) {
        return result
      }
      println(s"removed ${buffer(maxOverlaps._2)}")
      buffer.remove(maxOverlaps._2)
      result += 1
    }

    result
  }

  def doesOverlap(first: Interval, second: Interval): Boolean = {
    first.end > second.start
  }

  def sameStart(first: Interval, second: Interval): Boolean = {
    first.start == second.start
  }

}
