package leetcode

import scala.collection.mutable

object FindLargestValueInEachTreeRow {
  def largestValues(root: TreeNode): List[Int] = {
    val queue = mutable.Queue[TreeNode]()
    val result = mutable.ListBuffer[Int]()

    if (root != null) {
      queue.enqueue(root)
    }

    while (queue.nonEmpty) {
      val nodesOnLevel = queue.length
      var currentMaxOption: Option[Int] = None
      for (_ <- 0 until nodesOnLevel) {
        val node = queue.dequeue()
        if (node != null) {
          if (currentMaxOption.forall(_ < node.value)) {
            currentMaxOption = Some(node.value)
          }
          queue.enqueue(node.left)
          queue.enqueue(node.right)
        }
      }

      currentMaxOption foreach { currentMax =>
        result.addOne(currentMax)
      }
    }

    result.toList
  }
}
