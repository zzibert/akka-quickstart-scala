package leetcode

import scala.collection.mutable

object Solution {
  def largestRectangleArea(heights: Array[Int]): Int = {
    val stack = mutable.Stack[(Int, Int)]()
    var result = 0

    heights.zipWithIndex foreach {
      case (height, index) =>

        if (stack.isEmpty) {
          stack.push((height, index))
        } else {
          val (head, _) = stack.head
          if (height > head) {
            stack.push((height, index))
          } else {
            while (stack.nonEmpty && height < stack.head._1) {
              val currentValue = height * (index+1 - stack.head._2)
              if (currentValue > result) {
                result = currentValue
              }
              stack.pop()
            }

            stack.push((height, index))
          }
        }
    }

    while (stack.nonEmpty) {
      val index = heights.length
      val currentValue = stack.head._1 * (index - stack.head._2)
      if (currentValue > result) {
        result = currentValue
      }
      val lastHead = stack.head._1
      stack.pop()

      if (stack.isEmpty) {
        if (lastHead * index > result) {
          result = lastHead * index
        }
      }
    }

    result
  }
}
