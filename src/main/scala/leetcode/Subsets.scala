package leetcode

import scala.collection.mutable

object Subsets {
  def subsets(nums: Array[Int]): List[List[Int]] = {
    val result = mutable.Set[List[Int]]()
    subSetsHelper(nums, result)


    result.toList
  }

  def subSetsHelper(nums: Array[Int], result: mutable.Set[List[Int]]): Unit = {
    result.addOne(nums.toList)

    for (i <- 0 until nums.length) {
      subSetsHelper(nums.patch(i, Nil, 1), result)
    }
  }
}
