object Solution {
  def maxSubArray(nums: Array[Int]): Int = {
    if (nums.length == 1) {
      return nums(0)
    }

    var max = Int.MinValue
    var start = 0

    while (start < nums.length) {
      var currentMax = nums(start)
      if (currentMax > max) {
        max = currentMax
      }
      var end = start + 1
      while (currentMax > 0 && end < nums.length) {
        currentMax += nums(end)
        if (currentMax > max) {
          max = currentMax
        }
        end += 1
      }

      start = end
    }

    max
  }
}
