

object Solution {
  def lengthOfLIS(nums: Array[Int]): Int = {
    var result = 0
    val tabulation = Array.fill(nums.length)(0)

    for {
      i <- 0 until nums.length
      j <- 0 until i
      if nums(j) < nums(i)
    } {
      var current = 0
      if (tabulation(j) > current) {
        current = tabulation(j)
      }

      tabulation(i) = current + 1
      if (tabulation(i) > result) {
        result = tabulation(i)
      }
    }

    result
  }
}