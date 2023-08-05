package leetcode

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}


object LowestCommonAncestorOfABinarySearchTree {
  def lowestCommonAncestor(root: TreeNode, p: TreeNode, q: TreeNode): TreeNode = {
    val pPath = getPath(root, p.value)
    val qPath = getPath(root, q.value)

    pPath.intersect(qPath).last

  }
  def getPath(root: TreeNode, value: Int): List[TreeNode] = {
    if (root == null) {
      Nil
    } else if (root.value == value) {
      List(root)
    } else {
      val left = getPath(root.left, value)
      val right = getPath(root.right, value)

      if (left.isEmpty && right.isEmpty) {
        Nil
      } else if (left.isEmpty) {
        root :: right
      } else {
        root :: left
      }
    }
  }
}
