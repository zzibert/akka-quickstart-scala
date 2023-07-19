package leetcode

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}


object LowestCommonAncestorOfABinarySearchTree {
  def lowestCommonAncestor(root: TreeNode, p: TreeNode, q: TreeNode): TreeNode = {
    val pTraversal = traverseTo(root, p)
    val qTraversal = traverseTo(root, q)
    val pVals = pTraversal.map(_.value)
    val qVals = qTraversal.map(_.value)

    for (node <- pTraversal) {
      if (qVals.contains(node.value)) {
        return node
      }
    }

    null
  }

  def traverseTo(root: TreeNode, target: TreeNode): Seq[TreeNode] = {
    if (root.value == target.value) {
      Seq(root)
    } else if (root.value < target.value) {
      traverseTo(root.right, target) :+ root
    } else {
      traverseTo(root.left, target) :+ root
    }
  }
}
