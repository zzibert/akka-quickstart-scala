package leetcode

import scala.collection.mutable

object Solution {
  def lowestCommonAncestor(root: TreeNode,
                           p: TreeNode,
                           q: TreeNode): TreeNode = {
    val pPathElement = mutable.ListBuffer[Array[TreeNode]]()
    val qPathElement = mutable.ListBuffer[Array[TreeNode]]()

    findPath(root, p.value, Array.empty, pPathElement)
    findPath(root, q.value, Array.empty, qPathElement)

    val pPath = pPathElement.head.reverse
    val qPath = qPathElement.head.reverse

    qPath.find(element => pPath.contains(element)).getOrElse(new TreeNode())
  }

  def findPath(
      root: TreeNode,
      value: Int,
      path: Array[TreeNode],
      result: mutable.ListBuffer[Array[TreeNode]]
  ): Unit = {
    if (root != null) {
      if (root.value == value) {
        result.addOne(path :+ root)
      } else {
        findPath(root.left, value, path :+ root, result)
        findPath(root.right, value, path :+ root, result)
      }
    }
  }
}
