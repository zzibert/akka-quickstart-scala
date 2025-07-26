
object Solution {
  def rob(nums: Array[Int]): Int = {
    val loots = Array.fill[Int](nums.length)(0)

    if (nums.length < 2) {
      nums(0)
    } else if (nums.length < 3) {
      Math.max(nums(0), nums(1))
    } else if (nums.length < 4) {
      Math.max(nums(0) + nums(2), nums(1))
    } else {
      loots(0) = nums(0)
      loots(1) = nums(1)
      loots(2) = nums(2) + nums(0)
      for (i <- 3 until nums.length) {
        val left = loots(i - 3)
        val right = loots(i - 2)
        loots(i) = nums(i) + Math.max(left, right)
      }
      Math.max(loots(nums.size - 2), loots(nums.length - 1))
    }
  }
}