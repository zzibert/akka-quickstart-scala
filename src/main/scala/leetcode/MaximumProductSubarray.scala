package leetcode

object Solution {
  def maxProduct(nums: Array[Int]): Int = {
    var result = 0

  }

  def maxProductHelperNoZero(nums: Array[Int]): Int = {
    var result = 1

    val negativeNumbers = nums.count(_ < 0)

    if (negativeNumbers % 2 == 0) {
      nums foreach { number =>
        result *= number
      }
    } else {

    }

    result
  }

}
