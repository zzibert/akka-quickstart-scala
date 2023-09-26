package leetcode

object FlattenBinaryTreeToLinkedList {
  def flatten(root: TreeNode): Unit = {
    if (root != null) {
      modifyToRight(root)
    }
  }

  def modifyToRight(root: TreeNode): TreeNode = {
    if (root.left == null && root.right == null) {
      root
    } else if (root.left == null) {
      root.right = modifyToRight(root.right)
      root
    } else if (root.right == null) {
      val left = modifyToRight(root.left)
      root.left = null
      root.right = left
      root
    } else {
      val left = modifyToRight(root.left)
      val right = modifyToRight(root.right)
      traverseAndAppend(left, right)
      root.right = left
      root.left = null
      root
    }
  }

  def traverseAndAppend(root: TreeNode, right: TreeNode): Unit = {
    if (root.right != null) {
      traverseAndAppend(root.right, right)
    } else {
      root.right = right
    }
  }
}
