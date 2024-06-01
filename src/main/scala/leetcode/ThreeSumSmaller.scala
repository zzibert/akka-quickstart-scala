package leetcode

object Solution {
  def threeSumSmaller(nums: Array[Int], target: Int): Int = {
    val sortedNums = nums.sorted
    var result = 0
    val lenght = nums.length

    for {
      i <- 0 until lenght
      j <- i+1 until lenght
    } {
      val biggestPossible = (target - 1) - (sortedNums(i) + sortedNums(j))
      var k = j+1
      while (k < lenght && sortedNums(k) <= biggestPossible) {
        k += 1
        result += 1
      }
    }

    result
  }
}
