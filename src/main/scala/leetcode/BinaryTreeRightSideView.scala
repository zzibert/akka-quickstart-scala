package leetcode

import scala.collection.mutable

object Solution {
  def rightSideView(root: TreeNode): List[Int] = {
    val result = mutable.ArrayBuffer[Int]()
    val queue = mutable.Queue[TreeNode]()

    queue.enqueue(root)

    while (queue.nonEmpty) {
      val level = mutable.ArrayBuffer[Int]()
      for (_ <- 0 until queue.length) {
        val node = queue.dequeue()
        if (node != null) {
          level.addOne(node.value)
          queue.enqueue(node.left)
          queue.enqueue(node.right)
        }
      }
      if (level.nonEmpty) {
        result.addOne(level.last)
      }
    }

    result.toList
  }
}

