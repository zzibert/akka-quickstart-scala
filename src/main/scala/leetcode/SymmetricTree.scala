package leetcode

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object SymmetricTree {
  def isSymmetric(root: TreeNode): Boolean = {
    if (root == null) {
      true
    } else {
      isSymmetricHelper(root.left, root.right)
    }
  }

  def isSymmetricHelper(left: TreeNode, right: TreeNode): Boolean = {
    if (left == null && right == null) {
      true
    } else if (left == null || right == null || left.value != right.value) {
      false
    } else {
      isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left)
    }
  }

}
