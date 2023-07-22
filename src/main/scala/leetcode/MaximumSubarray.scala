package leetcode

import scala.util.control.Breaks.break

class MaximumSubarray {
  def maxSubArray(nums: Array[Int]): Int = {
    var maxSum = nums(0)

    for (i <- 0 until nums.length) {
      var localSum = nums(i)
      var j = i+1

      if (localSum > maxSum) {
        maxSum = localSum
      }

      while (localSum >= 0 && j < nums.length) {
        localSum += nums(j)
        if (localSum > maxSum) {
          maxSum = localSum
        }
        j += 1
      }
    }

    maxSum
  }

}
