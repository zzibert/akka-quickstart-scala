package leetcode

object ConstructBinaryTreeFromPreorderAndInorderTraversal {
  def buildTree(preorder: Array[Int], inorder: Array[Int]): TreeNode = {
    if (inorder.isEmpty || preorder.isEmpty) {
      null
    } else {
      val middleOption = preorder.find(inorder.contains(_))

      middleOption match {
        case Some(middle) =>
          val root = new TreeNode(middle)

          val middleIndexPreorder = preorder.indexWhere(_ == middle)
          val middleIndexInorder = inorder.indexWhere(_ == middle)

          root.left = buildTree(preorder.drop(middleIndexPreorder+1), inorder.take(middleIndexInorder))
          root.right = buildTree(preorder.drop(middleIndexPreorder+1), inorder.drop(middleIndexInorder+1))

          root
        case None =>
          null
      }
    }
  }
}
