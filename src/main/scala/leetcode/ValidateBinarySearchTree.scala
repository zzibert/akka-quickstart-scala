package leetcode

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object ValidateBinarySearchTree {
  def isValidBST(root: TreeNode): Boolean = {

    isValidBSTHelper(root,maxValue = Int.MaxValue, minValue = Int.MinValue)
  }

  def isValidBSTHelper(root: TreeNode, maxValue: Int, minValue: Int): Boolean = {
    if (root == null) {
      return true
    }

    if (root.value <= minValue || root.value >= maxValue) {
      return false
    }

    val leftMin =
      if (root.value < minValue) {
        root.value
      } else {
        minValue
      }

    val leftMax =
      if (root.value < maxValue) {
        root.value
      } else {
        maxValue
      }

    val rightMin =
      if (root.value > minValue) {
        root.value
      } else {
        minValue
      }

    val rightMax =
      if (root.value > maxValue) {
        root.value
      } else {
        maxValue
      }

    isValidBSTHelper(root.left, leftMax, leftMin) && isValidBSTHelper(root.right, rightMax, rightMin)
  }

}
