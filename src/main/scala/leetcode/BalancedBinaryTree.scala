package leetcode

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object BalancedBinaryTree {
  def isBalanced(root: TreeNode): Boolean = {
    if (root == null) {
      true
    } else if (Math.abs(maxDepth(root.left, 0) - maxDepth(root.right, 0)) > 1) {
      false
    } else {
      isBalanced(root.right) && isBalanced(root.left)
    }
  }

  def maxDepth(root: TreeNode, depth: Int): Int = {
    if (root == null) {
      depth
    } else {
      maxDepth(root.left, depth + 1) max maxDepth(root.right, depth + 1)
    }
  }
}
