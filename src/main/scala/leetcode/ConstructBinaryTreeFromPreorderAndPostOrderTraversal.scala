import leetcode.TreeNode

object Solution {

  def constructFromPrePost(
      preorder: Array[Int],
      postorder: Array[Int]
  ): TreeNode = {
    if (preorder.isEmpty) {
      null
    } else {
      val root = preorder.head
      val node = new TreeNode(root)

      if (preorder.tail.nonEmpty) {
        val leftRoot = preorder.tail.head
        val leftRootIndex = postorder.indexOf(leftRoot)
        val leftPostorder = postorder.take(leftRootIndex)
        val leftPreorder = Array(leftRoot) ++ leftPostorder
        node.left = constructFromPrePost(leftPreorder, leftPostorder)
      }

      if (postorder.length > 1) {
        val rightRoot = postorder(postorder.length-2)
        val rightRootIndex = preorder.indexOf(rightRoot)
        val rightPreorder = preorder.drop(rightRootIndex)
        val rightPostorder = preorder.drop(rightRootIndex+1)
        node.right = constructFromPrePost(rightPreorder, rightPostorder)
      }

      node
    }
  }
}
