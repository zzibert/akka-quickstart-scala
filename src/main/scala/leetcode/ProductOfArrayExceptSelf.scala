package leetcode

import scala.collection.mutable

object Solution {
  def productExceptSelf(nums: Array[Int]): Array[Int] = {
    val leftSide = mutable.Map[Int, Int]()
    val rightSide = mutable.Map[Int, Int]()
    val result: Array[Int] = Array.ofDim(nums.length)

    var value = nums(0)
    leftSide.update(0, value)

    for (i <- 1 until nums.length) {
      value *= nums(i)
      leftSide.update(i, value)
    }

    value = 1
    var i = nums.length - 1

    while (i >= 0) {
      value *= nums(i)
      rightSide(i) = value
      i -= 1
    }

    for (i <- 0 until nums.length) {
      val leftAnswer = if (i == 0) 1 else leftSide(i - 1)
      val rightAnswer = if (i < nums.length - 1) rightSide(i + 1) else 1

      result(i) = leftAnswer * rightAnswer
    }

    result
  }
}
