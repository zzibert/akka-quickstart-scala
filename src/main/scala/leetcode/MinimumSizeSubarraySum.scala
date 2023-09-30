package leetcode

object MinimumSizeSubarraySum {
  def minSubArrayLen(target: Int, nums: Array[Int]): Int = {
    var length = 1

    while (length <= nums.length) {
      var summa = nums.take(length).sum
      var index = 0
      if (summa >= target) {
        return length
      }
      for (i <- length until nums.length) {
        summa += nums(i)
        summa -= nums(index)
        index += 1
        if (summa >= target) {
          return length
        }
      }
      length += 1
    }

    0
  }
}
