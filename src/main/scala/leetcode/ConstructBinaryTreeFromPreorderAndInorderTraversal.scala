object Solution {

  def buildTree(preorder: Array[Int], inorder: Array[Int]): TreeNode = {
    if (preorder.isEmpty) {
      null
    } else {
      preorder.find(inorder.contains) match {
        case Some(root) =>
          val node = new TreeNode(root)
          val preorderIndex = preorder.indexOf(root)
          val inorderIndex = inorder.indexOf(root)
          node.left =
            buildTree(preorder.drop(preorderIndex), inorder.take(inorderIndex))
          node.right = buildTree(preorder.drop(preorderIndex),
                                 inorder.drop(inorderIndex + 1))
          node

        case None =>
          null
      }
    }
  }
}
