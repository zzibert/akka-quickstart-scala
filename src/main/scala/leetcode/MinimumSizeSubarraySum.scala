object Solution {
  def minSubArrayLen(target: Int, nums: Array[Int]): Int = {
    var i = 0
    var currentSum = 0
    val length = nums.length

    while (i < length && currentSum < target) {
      currentSum += nums(i)
      i += 1
    }

    if (currentSum < target) {
      0
    } else {
      var first = 0
      var last = i // first outside sliding window
      var slidingWindowSize = last - first

      while (first < length) {
        while (currentSum - nums(last-1) >= target) {
          currentSum -= nums(last-1)
          last -= 1
          slidingWindowSize = last - first
        }

        currentSum -= nums(first)
        first += 1
        if (currentSum >= target) {
          slidingWindowSize = last - first
        } else {
          if (last != length) {
            currentSum += nums(last)
            last += 1
          }
        }
      }
      slidingWindowSize
    }
  }
}