package leetcode

import scala.collection.mutable

class Codec {
  // Encodes a list of strings to a single string.
  def serialize(root: TreeNode): String = {
    val nodes = mutable.Map[Int, Int]()
    traverse(root, 0, nodes)
    val result = nodes.view.toList.map(node => s"${node._1}+${node._2}").mkString("|")
    nodes.clear()
    result
  }

  // Decodes a single string to a list of strings.
  def deserialize(data: String): TreeNode = {
    val keyValue = mutable.Map[Int, Int]()
    if (data.nonEmpty) {
      val nodes = data.split('|')
      nodes foreach { node =>
        val tuple = node.split('+')
        val key = tuple.head.toInt
        val value = tuple.last.toInt
        keyValue.update(key, value)
      }

      deTraverse(keyValue, 0)
    } else {
      null
    }
  }

  def deTraverse(nodes: mutable.Map[Int, Int], index: Int): TreeNode = {
    nodes.get(index) match {
      case Some(value) =>
        val node = new TreeNode(value)
        node.left = deTraverse(nodes, getLeftChildIndex(index))
        node.right = deTraverse(nodes, getRightChildIndex(index))
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

  def traverse(root: TreeNode, index: Int, nodes: mutable.Map[Int, Int]): Unit = {
    if (root != null) {
      nodes.update(index, root.value)

      val leftIndex = (index * 2) + 1
      traverse(root.left, leftIndex, nodes)

      val rightIndex = (index * 2) + 2
      traverse(root.right, rightIndex, nodes)
    }
  }
}
