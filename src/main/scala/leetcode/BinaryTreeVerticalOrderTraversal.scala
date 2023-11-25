package leetcode

import scala.collection.mutable

object BinaryTreeVerticalOrderTraversal {
  def verticalOrder(root: TreeNode): List[List[Int]] = {
    val result = mutable.ListBuffer[List[Int]]()

    val nodesByLeftIndex = mutable.Map[Int, mutable.ArrayBuffer[(Int, Int)]]()

    def traversePrefix(root: TreeNode, leftIndex: Int, level: Int): Unit = {
      if (root != null) {
        val buffer = nodesByLeftIndex.getOrElseUpdate(leftIndex, mutable.ArrayBuffer[(Int, Int)]())
        buffer.addOne((level, root.value))

        traversePrefix(root.left, leftIndex + 1, level+1)
        traversePrefix(root.right, leftIndex - 1, level+1)
      }
    }

    traversePrefix(root, 0, 0)

    nodesByLeftIndex
      .toList
      .view
      .sortBy(-_._1)
      .map(_._2)
      .foreach { buffer =>
        val element = buffer.view.sortBy(_._1).map(_._2)
        result.addOne(element.toList)
      }

    result.toList
  }
}
