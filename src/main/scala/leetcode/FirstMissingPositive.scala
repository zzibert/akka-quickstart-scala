package leetcode

class FirstMissingPositive {
  def firstMissingPositive(nums: Array[Int]): Int = {
    val potentialNumbers = nums
      .view
      .filter(x => x <= nums.length+1 && x > 0)
      .toSet

    val rightNumbers = (1 to nums.length+1)

    rightNumbers foreach { number =>
      if (!potentialNumbers.contains(number)) {
        return number
      }
    }

    1
  }
}
