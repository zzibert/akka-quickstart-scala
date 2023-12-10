package leetcode

import scala.collection.mutable

object MaximumWidthOfBinaryTree {
  def widthOfBinaryTree(root: TreeNode): Int = {
    var maxWidth = 0
    val queue = mutable.Queue[(Int, TreeNode)]()

    queue.enqueue((0, root))

    while(queue.nonEmpty) {
      val length = queue.length
      val level = mutable.ArrayBuffer[Int]()
      for (- <- 0 until length) {
        val (index, node) = queue.dequeue()
        if (node != null) {
          level.addOne(index)
          val leftIndex = getLeftIndex(index)
          queue.enqueue((leftIndex, node.left))
          val rightIndex = getRightIndex(index)
          queue.enqueue((rightIndex, node.right))
        }
      }
      val distance =
        if (level.nonEmpty) {
          (level.max - level.min) + 1
        } else {
          0
        }
      if (distance > maxWidth) {
        maxWidth = distance
      }

    }

    maxWidth
  }

  def getLeftIndex(index: Int): Int = {
    (index*2) + 1
  }

  def getRightIndex(index: Int): Int = {
    (index * 2) + 2
  }
}
