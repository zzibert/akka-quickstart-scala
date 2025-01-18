package leetcode

import scala.collection.mutable

class HitCounter() {
  val hits = mutable.ListBuffer[Int]()

  def hit(timestamp: Int): Unit = {
    hits.addOne(timestamp)
  }

  def getHits(timestamp: Int): Int = {
    getHitsHelper(timestamp, hits.toList)
  }

  def getHitsHelper(timestamp: Int, timestamps: List[Int]): Int = {
    var result = 0
    if (timestamps.length > 4) {
      val middle = timestamps.length / 2
      val middleElement = timestamps(middle)

      if (middleElement >= timestamp - 300 && middleElement < timestamp) {
        var i = middle
        while (i >= 0 && timestamps(i) >= timestamp - 300) {
          result += 1
          i -= 1
        }
        var j = middle + 1
        while (j < timestamps.length && timestamps(j) < timestamp) {
          result += 1
          j += 1
        }
      } else if (middleElement >= timestamp) {
        val newTimestamps = timestamps.take(middle)
        result = getHitsHelper(timestamp, newTimestamps)
      } else {
        val newTimestamps = timestamps.drop(middle)
        result = getHitsHelper(timestamp, newTimestamps)
      }

    } else {
      for (i <- 0 until timestamps.length) {
        if (timestamps(i) >= timestamp - 300 && timestamps(i) < timestamp) {
          result += 1
        }
      }
    }

    result
  }
}
