package leetcode

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object ValidateBinarySearchTree {
  def isValidBST(root: TreeNode): Boolean = {
    val values = isValidBSTHelper(root)

    values == values.sorted.distinct
  }

  def isValidBSTHelper(root: TreeNode): List[Int] = {
    if (root == null) {
      Nil
    } else {
      isValidBSTHelper(root.left) ::: List(root.value) ::: isValidBSTHelper(root.right)
    }
  }

}
