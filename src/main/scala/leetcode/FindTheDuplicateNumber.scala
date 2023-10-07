package leetcode

object FindTheDuplicateNumber {
  def findDuplicate(nums: Array[Int]): Int = {
    for (i <- 1 until nums.length) {
      var result = 0
      for (j <- 0 until nums.length) {
        if (nums(j) == i) {
          result += 1
          if (result > 1) {
            return i
          }
        }
      }
    }
    0
  }
}
