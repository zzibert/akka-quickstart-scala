package leetcode

import scala.collection.mutable

object SingleNumber {
  def singleNumber(nums: Array[Int]): Int = {
    if (nums.length == 1) {
      return nums(0)
    }

    nums.sortInPlace()

    for (i <- 0 until nums.length by 2) {
      if (i == nums.length-1 || nums(i) != nums(i+1)) {
        return nums(i)
      }
    }
    nums(0)
  }

}
