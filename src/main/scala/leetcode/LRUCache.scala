package leetcode

import scala.collection.mutable

class LRUCache(_capacity: Int) {
    val cache = mutable.Map[Int, (Int, Int)]()
    var counter = 0
  def get(key: Int): Int = {
    cache.get(key) match {
      case Some((value, _)) =>
        cache += (key -> (value, counter))
        counter += 1
        value
      case None =>
        -1
    }
  }

  def put(key: Int, value: Int) {
    cache += (key -> (value, counter))
    counter += 1

    if (cache.size > _capacity) {
      val leastUsedKey = cache.toList.sortBy(keyValue => keyValue._2._2).head._1
      cache -= leastUsedKey
    }
  }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = new LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */
