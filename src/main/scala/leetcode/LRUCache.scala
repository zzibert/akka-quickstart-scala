package leetcode

import scala.collection.mutable

class LRUCache(_capacity: Int) {
  val cache = mutable.Map[Int, (Int, Int)]()
  val used = mutable.ArrayBuffer[Int]
  var counter = 0
  def get(key: Int): Int = {
    cache.get(key) match {
      case Some(value) =>

    }
  }

  def put(key: Int, value: Int) {

  }

}
