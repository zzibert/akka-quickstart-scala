package leetcode

object Solution {
  def findPeakElement(nums: Array[Int]): Int = {
    var i = 0

    while (!isPeak(i, nums)) {
      i += 1
    }

    i
  }

  def isPeak(i: Int, nums: Array[Int]): Boolean = {
    val isBiggerThanLeftNeighbor = if (i - 1 < 0) true else nums(i) > nums(i-1)
    val isBiggerThanRightNeighbor = if (i + 1 >= nums.length) true else nums(i) > nums(i + 1)

    isBiggerThanLeftNeighbor && isBiggerThanRightNeighbor
  }
}
