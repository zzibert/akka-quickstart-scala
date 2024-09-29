package leetcode

import scala.collection.mutable

class MinStack() {
  val stack = mutable.Stack[(Int, Int)]()

  def push(`val`: Int): Unit = {
    val value = `val`
    if (stack.isEmpty) {
      stack.push((value, value))
    } else {
      val min = stack.top._2
      if (value < min) {
        stack.push((value, value))
      } else {
        stack.push((value, min))
      }
    }
  }

  def pop(): Unit = {
    stack.pop()._1
  }

  def top(): Int = {
    stack.top._1
  }

  def getMin(): Int = {
    stack.top._2
  }

}
