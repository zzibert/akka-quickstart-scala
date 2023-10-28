package leetcode

import scala.collection.mutable

object GenerateParentheses {
  def generateParenthesis(n: Int): List[String] = {
    val queue = mutable.Queue[String]()
    var counter = 1

    if (n < 1) {
      Nil
    } else {
      queue.enqueue("()")
      while (counter < n) {
        val length = queue.length
        for (_ <- 0 until length) {
          val parentheses = queue.dequeue()
          for (i <- 0 until parentheses.length) {
            val newValue = parentheses.take(i) + "()" + parentheses.drop(i)
            queue.enqueue(newValue)
          }
        }
        counter += 1
      }

      queue.toList.distinct
    }
  }
}
