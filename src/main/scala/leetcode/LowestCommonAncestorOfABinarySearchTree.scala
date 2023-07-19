package leetcode

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}


object LowestCommonAncestorOfABinarySearchTree {
  def lowestCommonAncestor(root: TreeNode, p: TreeNode, q: TreeNode): TreeNode = {
    val pTraversal = traverseTo(root, p, Seq[TreeNode]())
    val qTraversal = traverseTo(root, q, Seq[TreeNode]())

    pTraversal.intersect(qTraversal).head
  }

  def traverseTo(root: TreeNode, target: TreeNode, chain: Seq[TreeNode]): Seq[TreeNode] = {
    if (root.value == target.value) {
      root +: chain
    } else if (root.value < target.value) {
      traverseTo(root.right, target, root +: chain)
    } else {
      traverseTo(root.left, target, root +: chain)
    }
  }
}
