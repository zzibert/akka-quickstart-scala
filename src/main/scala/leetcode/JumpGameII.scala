package leetcode

object JumpGameII {
  def jump(nums: Array[Int]): Int = {
    val length = nums.length
    val numbersOfJumps = Array.fill(length)(Int.MaxValue)
    numbersOfJumps(0) = 0

    for (i <- 0 until length) {
      val jumps = numbersOfJumps(i) + 1
      val limit = Math.min(length-1, i + nums(i))
      for (j <- i+1 to limit) {
        if (jumps < numbersOfJumps(j)) {
          numbersOfJumps(j) = jumps
        }
      }
    }

    numbersOfJumps(length-1)
  }
}
