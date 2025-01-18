package leetcode

import scala.collection.mutable

class TimeMap() {
  val map = mutable.Map[String, List[(Int, String)]]()

  def set(key: String, value: String, timestamp: Int): Unit = {
    map.get(key) match {
      case Some(list) =>
        val last = list.last


      case None =>
        val list = List((timestamp, value))
        map.update(key, list)
    }
  }

  def get(key: String, timestamp: Int): String = {

  }

}


/**
 * Your TimeMap object will be instantiated and called as such:
 * var obj = new TimeMap()
 * obj.set(key,value,timestamp)
 * var param_2 = obj.get(key,timestamp)
 */
