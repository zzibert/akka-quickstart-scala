package leetcode

import scala.collection.mutable

object DailyTemperatures {
  def dailyTemperatures(temperatures: Array[Int]): Array[Int] = {
    val stack = mutable.Stack[(Int, Int)]()
    val result = Array.fill(temperatures.length)(0)

    for (i <- 0 until temperatures.length) {
      val current = (i, temperatures(i))
      while (stack.nonEmpty && stack.head._2 < current._2) {
        val head = stack.pop()
        result(head._1) = current._1 - head._1
      }
      stack.push(current)
    }

    while (stack.nonEmpty) {
      val current = stack.pop()
      result(current._1) = 0
    }

    result
  }
}
