package leetcode

object BinarySearch {
  def search(nums: Array[Int], target: Int): Int = {
    searchHelper(nums.zipWithIndex, target)
  }

  def searchHelper(nums: Array[(Int, Int)], target: Int): Int = {
    if (nums.length == 1) {
      if (nums(0)._1 == target) {
        nums(0)._2
      } else {
        -1
      }
    } else {
      val index = nums.length / 2
      val value = nums(index)._1
      val key = nums(index)._2
      if (value == target) {
        key
      } else if (value > target) {
        searchHelper(nums.take(index), target)
      } else {
        searchHelper(nums.drop(index), target)
      }
    }
  }
}
