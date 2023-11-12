package leetcode

import scala.collection.mutable

class Codec {
  private val keyValue = mutable.Map[Int, Int]()

  // Encodes a list of strings to a single string.
  def serialize(root: TreeNode): String = {
    traverse(root, 0)
    val result = keyValue.view.toList.map(node => s"${node._1}+${node._2}").mkString("|")
    keyValue.clear()
    result
  }

  // Decodes a single string to a list of strings.
  def deserialize(data: String): TreeNode = {
    if (data.nonEmpty) {
      val nodes = data.split('|')
      nodes foreach { node =>
        val tuple = node.split('+')
        val key = tuple.head.toInt
        val value = tuple.last.toInt
        keyValue.update(key, value)
      }

      deTraverse(0)
    } else {
      null
    }
  }

  def deTraverse(index: Int): TreeNode = {
    keyValue.get(index) match {
      case Some(value) =>
        val node = new TreeNode(value)
        node.left = deTraverse(getLeftChildIndex(index))
        node.right = deTraverse(getRightChildIndex(index))
        node

      case None =>
        null

    }
  }

  def getLeftChildIndex(parentIndex: Int): Int = {
    (parentIndex * 2) + 1
  }

  def getRightChildIndex(parentIndex: Int): Int = {
    (parentIndex * 2) + 2
  }

  def traverse(root: TreeNode, index: Int): Unit = {
    if (root != null) {
      keyValue.update(index, root.value)

      val leftIndex = (index * 2) + 1
      traverse(root.left, leftIndex)

      val rightIndex = (index * 2) + 2
      traverse(root.right, rightIndex)
    }
  }
}
