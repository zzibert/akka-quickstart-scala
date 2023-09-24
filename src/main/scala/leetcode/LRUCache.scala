package leetcode

import scala.collection.mutable

class LRUCache(_capacity: Int) {
    val cache = mutable.Map[Int, (Int, Int)]()
    val used = mutable.ArrayBuffer[(Int, Int)]()
    var counter = 0
  def get(key: Int): Int = {
    cache.get(key) match {
      case Some((value, _)) =>
        cache += (key -> (value, counter))
        used.addOne((key, counter))
        counter += 1
        value
      case None =>
        -1
    }
  }

  def put(key: Int, value: Int) {
    cache += (key -> (value, counter))
    used.addOne((key, counter))
    counter += 1

    if (cache.size > _capacity) {
      val leastUsedKey = findLeastUsedKey(cache, used)
      cache -= leastUsedKey
    }
  }

  def findLeastUsedKey(cache: mutable.Map[Int, (Int, Int)], used: mutable.ArrayBuffer[(Int, Int)]): Int = {
    for (i <- 0 until used.length) {
      val (usedKey, usedCounter) = used(i)
      cache.get(usedKey) match {
        case Some((_, counter)) =>
          if (counter == usedCounter) {
            used.dropInPlace(i+1)
            return usedKey
          }

        case _ =>
      }
    }

    0
  }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = new LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */
