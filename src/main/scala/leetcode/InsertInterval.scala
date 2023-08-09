package leetcode

import scala.collection.mutable

object InsertInterval {
  def insert(intervals: Array[Array[Int]], newInterval: Array[Int]): Array[Array[Int]] = {
    val insertResult = mutable.ArrayBuffer[Array[Int]]()
    var insertOption: Option[Array[Int]] = Some(newInterval)
    // insert Stage
    intervals foreach { interval =>
      if (insertOption.exists(_(0) < interval(0))) {
        insertOption foreach { insert =>
          insertResult.addOne(insert)
          insertOption = None
        }
      }
      insertResult.addOne(interval)
    }

    insertOption foreach { insert =>
      insertResult.addOne(insert)
    }

    merge(insertResult)

    insertResult.toArray
  }

  def merge(intervals: mutable.ArrayBuffer[Array[Int]]): Unit = {
    var i = 0
    while (i < (intervals.length - 1)) {
      if (intervals(i)(1) > intervals(i+1)(1)) {
        intervals.remove(i+1)
      } else if (intervals(i)(1) >= intervals(i + 1)(0)) {
        intervals(i)(1) = intervals(i+1)(1)
        intervals.remove(i+1)
      } else {
        i += 1
      }
    }
  }
}
