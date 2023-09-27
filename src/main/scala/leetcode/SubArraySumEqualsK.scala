package leetcode

object SubArraySumEqualsK {
  def subarraySum(nums: Array[Int], k: Int): Int = {
    var result = 0

    var i = nums.length

    while (i > 0) {
      for (j <- 0 to (nums.length - i)) {
        val example = nums.drop(j).take(i)
        if (example.sum == k) {
          result += 1
        }
      }
      i -= 1
    }

    result
  }
}
