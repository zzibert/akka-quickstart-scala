package leetcode

import scala.collection.mutable

class LRUCache(_capacity: Int) {
  val cache = mutable.Map[Int, (Int, Int)]()
  var usage = mutable.ListBuffer[(Int, Int)]()

  def get(key: Int): Int = {
    cache.get(key) match {
      case Some((value, counter)) =>
        increment(key, value, counter)

        value

      case None =>
        -1
    }
  }

  def put(key: Int, value: Int): Unit = {
    cache.get(key) match {
      case Some((_, counter)) =>
        increment(key, value, counter)

      case None =>
        if (cache.size == _capacity) {
          var foundLeastRecentlyUsed = false
          while (!foundLeastRecentlyUsed) {
            val node = usage.head
            val (usageKey, usageCounter) = node
            if (usageCounter == cache(usageKey)._2) {
              foundLeastRecentlyUsed = true
              cache.remove(usageKey)
              increment(key, value, 0)
            }
            usage.dropInPlace(1)
          }
        } else {
          increment(key, value, 0)
        }

    }
  }

  def increment(key: Int, value: Int, counter: Int): Unit = {
    cache.update(key, (value, counter + 1))
    usage.addOne((key, counter + 1))
  }

}
