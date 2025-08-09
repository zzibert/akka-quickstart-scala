package leetcode

object MinimumSizeSubarraySum {
  def minSubArrayLen(target: Int, nums: Array[Int]): Int = {
    for (length <- 1 to nums.length) {
      var summa = 0
      for (i <- 0 until length) {
        summa += nums(i)
      }

      for (i <- length until nums.length) {
        if (summa >= target) {
          return length
        }
        summa -= nums(i-length)
        summa += nums(i)
      }
      if (summa >= target) {
        return length
      }
    }

    0
  }
}
