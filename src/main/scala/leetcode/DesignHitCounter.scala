package leetcode

import scala.collection.mutable

class HitCounter() {
  val queue = mutable.Queue[Hit]()
  case class Hit(timestamp: Int)

  def hit(timestamp: Int) {
    queue.enqueue(Hit(timestamp))

  }

  def getHits(timestamp: Int): Int = {
    if (queue.isEmpty) {
      0
    } else {
      while (queue.nonEmpty && timestamp - 300 >= queue.head.timestamp) {
        queue.dequeue()
      }

      queue.length
    }
  }

}
