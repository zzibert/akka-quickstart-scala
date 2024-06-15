package leetcode

object Solution {
  def findMaxLength(nums: Array[Int]): Int = {
    val length = nums.length
    val isEven = length % 2 == 0

    var result =
      if (isEven) {
        length
      } else {
        length-1
      }

    while (result > 1) {

    }

    result
  }
}
