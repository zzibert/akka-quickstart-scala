package leetcode

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class TimeMap() {
  val store = mutable.Map[String, ArrayBuffer[(String, Int)]]()

  def set(key: String, value: String, timestamp: Int) {
    val buffer = store.getOrElseUpdate(key, ArrayBuffer[(String, Int)]())
    buffer.addOne((value, timestamp))
  }

  def get(key: String, timestamp: Int): String = {
    val buffer = store.getOrElseUpdate(key, ArrayBuffer[(String, Int)]())
    binarySearch(buffer, timestamp)
  }

  def binarySearch(buffer: ArrayBuffer[(String, Int)], timestamp: Int): String = {
    val length = buffer.length

    if (length < 3) {
      var i = length-1
      while (i >= 0) {
        if (buffer(i)._2 <= timestamp) {
          return buffer(i)._1
        }
        i -= 1
      }
      ""
    } else {
      val middleIndex = length / 2

      if (isLessOrEqualAndNextIsBigger(buffer, middleIndex, timestamp)) {
        buffer(middleIndex)._1
      } else {
        if (buffer(middleIndex)._2 < timestamp) {
          binarySearch(buffer.drop(middleIndex+1), timestamp)
        } else {
          binarySearch(buffer.take(middleIndex+1), timestamp)
        }
      }
    }
  }

  def isLessOrEqualAndNextIsBigger(buffer: ArrayBuffer[(String, Int)], index: Int, timestamp: Int): Boolean = {
    if (buffer(index)._2 <= timestamp) {
      if (isInbound(index + 1, buffer.length)) {
        buffer(index + 1)._2 > timestamp
      } else {
        true
      }
    } else {
      false
    }
  }

  def isInbound(index: Int, length: Int) =
    length > index && index >= 0

}



/**
 * Your TimeMap object will be instantiated and called as such:
 * var obj = new TimeMap()
 * obj.set(key,value,timestamp)
 * var param_2 = obj.get(key,timestamp)
 */
