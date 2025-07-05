object Solution {
  def lowestCommonAncestor(root: TreeNode,
                           p: TreeNode,
                           q: TreeNode): TreeNode = {

    val pPath = getPath(root, p.value, List.empty[TreeNode])
    val qPath = getPath(root, q.value, List.empty[TreeNode])

    pPath.find(node => qPath.contains(node)).get
  }

  def getPath(root: TreeNode,
              target: Int,
              path: List[TreeNode]): List[TreeNode] = {
    if (root != null) {
      if (root.value == target) {
        root :: path
      } else {
        val rightPath = getPath(root.right, target, root :: path)
        val leftPath = getPath(root.left, target, root :: path)
        val children = List(rightPath, leftPath).filter(_.nonEmpty)

        if (children.isEmpty) {
          List.empty
        } else {
          children.head
        }
      }

    } else {
      List.empty
    }
  }
}
