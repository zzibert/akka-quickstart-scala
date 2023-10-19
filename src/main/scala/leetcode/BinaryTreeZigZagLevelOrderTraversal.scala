package leetcode

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class BinaryTreeZigZagLevelOrderTraversal {
  def zigzagLevelOrder(root: TreeNode): List[List[Int]] = {
    val result = ListBuffer[ListBuffer[Int]]()
    val queue = mutable.Queue[TreeNode]()
    if (root == null) {
      return Nil
    }

    queue.enqueue(root)

    while (queue.nonEmpty) {
      val length = queue.length
      val current = ListBuffer[Int]()
      for (_ <- 0 until length) {
        val node = queue.dequeue()
        if (node != null) {
          current.addOne(node.value)
          queue.enqueue(node.left)
          queue.enqueue(node.right)
        }
      }
      if (current.nonEmpty) {
        result.addOne(current)
      }
    }

    (result.zipWithIndex collect {
      case (list, index) =>
        if (index % 2 == 1) {
          list.reverse.toList
        } else {
          list.toList
        }
    }).toList
  }
}
