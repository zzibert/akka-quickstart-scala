package leetcode

object JumpGame {
  def canJump(nums: Array[Int]): Boolean = {
    val length = nums.length
    val reachable = Array.fill(length)(false)
    reachable(0) = true

    for (i <- 0 until length) {
      if (reachable(i)) {
        val distance = nums(i)
        val last = Math.min(length-1, i+distance)
        for (j <- i+1 to last) {
          reachable(j) = true
        }
      }
    }

    reachable(nums.length-1)
  }
}
