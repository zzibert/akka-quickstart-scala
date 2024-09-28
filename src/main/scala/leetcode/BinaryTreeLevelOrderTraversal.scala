package leetcode

import scala.collection.mutable

/**
  * Definition for a binary tree node.
  * class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  *   var value: Int = _value
  *   var left: TreeNode = _left
  *   var right: TreeNode = _right
  * }
  */
object Solution {
  def levelOrder(root: TreeNode): List[List[Int]] = {
    val queue = mutable.Queue[TreeNode]()

    var result = List[List[Int]]()

    queue.enqueue(root)

    while (queue.nonEmpty) {
      var level = List[Int]()
      val length = queue.length
      for (_ <- 0 until length) {
        val node = queue.dequeue()
        if (node != null) {
          level = level :+ node.value
          queue.enqueue(node.left)
          queue.enqueue(node.right)
        }
      }

      if (level.nonEmpty) {
        result = result :+ level
      }
    }

    result
  }
}
