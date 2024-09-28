package leetcode

object Solution {
  def maxSubArray(nums: Array[Int]): Int = {
    var maxSum = Int.MinValue
    var i = 0
    while (i < nums.length) {
      val number = nums(i)
      var currentSum = number

      if (currentSum > maxSum) {
        maxSum = currentSum
      }

      if (number > 0) {
        var j = i + 1
        while (j < nums.length) {
          val nextNumber = nums(j)
          if (nextNumber >= 0) {
            currentSum += nextNumber
            j += 1;

            if (currentSum > maxSum) {
              maxSum = currentSum
            }
          } else if (currentSum - nextNumber > 0) {
            currentSum += nextNumber
            j += 1;
          } else {
            j = nums.length
          }
        }

      }

      i += 1;
    }

    maxSum
  }
}
