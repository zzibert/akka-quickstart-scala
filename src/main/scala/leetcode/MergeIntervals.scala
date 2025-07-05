import scala.collection.mutable

object Solution {
  def merge(intervals: Array[Array[Int]]): Array[Array[Int]] = {
    var i = 0
    val result: mutable.ArrayBuffer[Array[Int]] =
      mutable.ArrayBuffer.from(intervals).sortBy(_(0))

    while (i < result.length) {
      val j = i + 1
      if (j < result.length) {
        if (result(i)(1) >= result(j)(0)) {
          val start = Math.min(result(i)(0), result(j)(0))
          val end = Math.max(result(i)(1), result(j)(1))
          result(i) = Array(start, end)
          result.remove(j)
        } else {
          i += 1
        }
      } else {
        i += 1
      }
    }

    result.toArray
  }
}
