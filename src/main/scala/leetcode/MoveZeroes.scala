package leetcode

import scala.collection.mutable.ArrayBuffer

object MoveZeroes {
  def moveZeroes(nums: Array[Int]): Unit = {
    var nonZeroIndex = 0

    for (num <- nums) {
      if (num != 0) {
        nums(nonZeroIndex) = num
        nonZeroIndex += 1
      }
    }

    for (i <- nonZeroIndex until nums.length) {
      nums(i) = 0
    }
  }
}
