object Solution {
  def insert(intervals: Array[Array[Int]], newInterval: Array[Int]): Array[Array[Int]] = {
    var i = 0

    while (i < intervals.length && intervals(i)(0) < newInterval(0)) {
      i += 1
    }

    var withNewInterval = intervals.take(i) ++ Array(newInterval) ++ intervals.drop(i)
    var j = Math.max(0, i - 1)

    while (j < withNewInterval.length-1) {
      val interval = withNewInterval(j)
      val nextInterval = withNewInterval(j+1)

      if (interval(1) >= nextInterval(0)) {
        val merged = Array(interval(0), Math.max(interval(1), nextInterval(1)))
        withNewInterval = withNewInterval.take(j) ++ Array(merged) ++ withNewInterval.drop(j+2)
      } else {
        j += 1
      }
    }

    withNewInterval
  }
}
