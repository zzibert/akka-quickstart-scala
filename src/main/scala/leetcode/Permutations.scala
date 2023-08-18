package leetcode

import scala.collection.mutable

object Permutations {
  def permute(nums: Array[Int]): List[List[Int]] = {
    val result = mutable.ListBuffer[List[Int]]()

    permutateHelper(Nil, nums, result)

    result.toList
  }

  def permutateHelper(permutation: List[Int], nums: Array[Int], result: mutable.ListBuffer[List[Int]]): Unit = {
    if (nums.length > 0) {
      nums.zipWithIndex foreach {
        case (num, index) =>
          permutateHelper(num :: permutation, nums.patch(index, Nil, 1), result)
      }
    } else {
      result.addOne(permutation)
    }
  }
}
