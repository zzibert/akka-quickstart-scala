package leetcode

import scala.collection.mutable
class BinaryTreeRightSideView {
  def rightSideView(root: TreeNode): List[Int] = {
    if (root == null) {
      return Nil
    }

    val result = mutable.ListBuffer[Int]()
    val queue = mutable.Queue[TreeNode]()

    queue.addOne(root)

    while (true) {
      val row = queue.reverse.toList
      if (row.isEmpty) {
        return result.toList
      } else {
        result.addOne(row(0).value)
      }
      queue.clear()
      row
        .view
        .reverse
        .foreach { node =>
          if (node.left != null) {
            queue.addOne(node.left)
          }
          if (node.right != null) {
            queue.addOne(node.right)
          }
        }
    }

    result.toList
  }
}
