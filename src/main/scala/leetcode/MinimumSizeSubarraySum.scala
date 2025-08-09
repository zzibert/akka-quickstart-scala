object Solution {
  def minSubArrayLen(target: Int, nums: Array[Int]): Int = {
    val length = nums.length
    val leftValue = Array.fill(length+1)(0)
    var currentValue = 0
    var size = 1
    var conditionMet = false

    for (i <- 0 to length) {
      leftValue(i) = currentValue
      if (i < length) {
        currentValue += nums(i)
      }
    }

    while (!conditionMet && size <= length) {
      for (i <- 0 to length-size) {
        val j = i + size
        val value = leftValue(j) - leftValue(i)
        if (value >= target) {
          conditionMet = true
        }
      }

      if (!conditionMet) {
        size += 1
      }
    }

    if (conditionMet) {
      size
    } else {
      0
    }
  }
}