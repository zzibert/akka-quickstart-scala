package leetcode

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class PartitionEqualSubsetSum {
  def canPartition(nums: Array[Int]): Boolean = {
    val result = ListBuffer[Int]()
    val seen = mutable.Map[Int, Boolean]()


    if (nums.sum % 2 != 0) {
      return false
    }

    val target = nums.sum / 2

    canPartitionHelper(nums, target, seen, result)

    !result.isEmpty
  }

  def canPartitionHelper(nums: Array[Int], target: Int, seen: mutable.Map[Int, Boolean], result: ListBuffer[Int]): Unit = {
    val summa = nums.sum
    if (result.isEmpty && !seen.getOrElse(summa, false)) {
      seen += (summa -> true)
      if (summa == target) {
        result.addOne(1)
      } else if (summa > target) {
        for (i <- 0 until nums.length) {
          canPartitionHelper(nums.patch(i, Nil, 1), target, seen, result)
        }
      }
    }
  }
}
