package leetcode

import scala.collection.mutable

object PathSumII {
  def pathSum(root: TreeNode, targetSum: Int): List[List[Int]] = {
    val result = mutable.ArrayBuffer[List[Int]]()

    pathSumTraversal(Nil, root, targetSum, result)

    result.toList
  }

  def pathSumTraversal(nums: List[Int], root: TreeNode, targetSum: Int, result: mutable.ArrayBuffer[List[Int]]): Unit = {
    if (root != null) {
      if (root.left == null && root.right == null && targetSum == root.value) {
        result.addOne(nums.appended(root.value))
      } else {
        pathSumTraversal(nums.appended(root.value), root.left, targetSum - root.value, result)
        pathSumTraversal(nums.appended(root.value), root.right, targetSum - root.value, result)
      }
    }
  }
}
