package leetcode

object MajorityElement {
  def majorityElement(nums: Array[Int]): Int = {
    if (nums.length == 1) {
      nums(0)
    } else {
      nums.sortInPlace()
      val index = nums.length / 2

      nums(index)
    }
  }
}
