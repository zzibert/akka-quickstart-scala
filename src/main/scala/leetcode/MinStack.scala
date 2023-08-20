package leetcode

import scala.collection.mutable

class MinStack() {
  val stack = mutable.Stack[(Int, Int)]()
  var minimum = Int.MaxValue

  def push(`val`: Int) {
    if (`val` < minimum) {
      minimum = `val`
    }

    stack.push((`val`, minimum))
  }

  def pop() {
    stack.pop()
    if (stack.nonEmpty) {
      val (_, newMin) = stack.top
      minimum = newMin
    } else {
      minimum = Int.MaxValue
    }
  }

  def top(): Int = {
    stack.top._1
  }

  def getMin(): Int = {
    stack.top._2
  }

}
