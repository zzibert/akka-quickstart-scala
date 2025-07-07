object Solution {
  def buildTree(inorder: Array[Int], postorder: Array[Int]): TreeNode = {
    if (postorder.isEmpty) {
      null
    } else {
      val reversePostorder = postorder.reverse
      val root = reversePostorder.head
      val node = new TreeNode(root)
      val rootIndex = inorder.indexOf(root)
      val leftInorder = inorder.take(rootIndex)
      val rightInorder = inorder.drop(rootIndex + 1)
      node.left =
        buildTree(leftInorder, postorder.intersect(leftInorder))
      node.right = buildTree(rightInorder, postorder.intersect(rightInorder))

      node
    }
  }
}
