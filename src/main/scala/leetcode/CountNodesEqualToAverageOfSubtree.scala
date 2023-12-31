package leetcode

object CountNodesEqualToAverageOfSubtree {
  def averageOfSubtree(root: TreeNode): Int = {
    val (_, _, count) = averageOfSubtreeHelper(root)

    count
  }

  def averageOfSubtreeHelper(root: TreeNode): (Int, Int, Int) = {
    if (root == null) {
      (0, 0, 0)
    } else {
      val (rightCount, rightNodes, rightResult) = averageOfSubtreeHelper(root.right)
      val (leftCount, leftNodes, leftResult) = averageOfSubtreeHelper(root.left)

      val average = (rightCount + leftCount + root.value) / (rightNodes + leftNodes + 1)

      if (average == root.value) {
        (rightCount + leftCount + root.value, leftNodes+rightNodes+1, rightResult+leftResult+1)
      } else {
        (rightCount + leftCount + root.value, leftNodes+rightNodes+1, rightResult+leftResult)
      }
    }
  }
}
