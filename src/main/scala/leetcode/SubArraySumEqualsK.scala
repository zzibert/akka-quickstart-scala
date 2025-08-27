package leetcode

object Solution {
  def subarraySum(nums: Array[Int], k: Int): Int = {
    val length = nums.length
    val sums = Array.fill(length)(0)

    var result = 0
    var currentSum = 0

    for {
      i <- 0 until length
      number = nums(i)
    } {
      currentSum += number
      sums(i) = currentSum
      if (currentSum == k) {
        result += 1
      }
    }

    for {
      i <- 0 until length
      number = nums(i)
      j <- i+1 until length
    } {
      sums(j) -= number
      if (sums(j) == k) {
        result += 1
      }
    }

    result
  }
}