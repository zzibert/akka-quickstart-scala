package leetcode

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}
object InvertBinaryTree {
  def invertTree(root: TreeNode): TreeNode = {
    if (root == null) {
      return root
    }

    val temp = root.left
    root.left = invertTree(root.right)
    root.right = invertTree(temp)

    root
  }

  def main(args: Array[String]): Unit = {

  }
}
