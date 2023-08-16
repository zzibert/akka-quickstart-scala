package leetcode

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object KthSmallestElementInaBST {
  def kthSmallest(root: TreeNode, k: Int): Int = {
    val traversal = inOrderTraversal(root)

    traversal.drop(k-1)

    traversal.head

  }

  def inOrderTraversal(root: TreeNode): List[Int] = {
    if (root == null) {
      Nil
    } else {
      inOrderTraversal(root.left) ::: List(root.value) ::: inOrderTraversal(root.right)
    }
  }

}
