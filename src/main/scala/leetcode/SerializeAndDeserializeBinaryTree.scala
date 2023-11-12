package leetcode

import scala.collection.mutable

class Codec {
  // Encodes a list of strings to a single string.
  def serialize(root: TreeNode): String = {
    val nodes =
      traverse(root) collect {
        case root if root != null => root.value
        case _ => 1001
      }
    nodes.mkString("|")
  }

  // Decodes a single string to a list of strings.
  def deserialize(data: String): TreeNode = {
    if (data.nonEmpty) {
      val nodes = data.split('|').map(_.toInt)

      deTraverse(nodes)
    } else {
      null
    }
  }

  def deTraverse(nodes: Array[Int]): TreeNode = {
    if (nodes.length == 0) {
      null
    } else {
      val treeNodes = nodes map {
        case node if node != 1001 => new TreeNode(node)
        case _ => null
      }

      treeNodes
        .view
        .zipWithIndex
        .filterNot(node => node._1 == null)
        .foreach {
          case (node, index) =>
            node.left = getLeftChild(index, treeNodes)
            node.right = getRightChild(index, treeNodes)
        }


      treeNodes.head
    }
  }

  def getLeftChild(parentIndex: Int, nodes: Array[TreeNode]): TreeNode = {
    val childIndex = (parentIndex * 2) + 1
    if (childIndex < nodes.length) {
      nodes(childIndex)
    } else {
      null
    }
  }

  def getRightChild(parentIndex: Int, nodes: Array[TreeNode]): TreeNode = {
    val childIndex = (parentIndex * 2) + 2
    if (childIndex < nodes.length) {
      nodes(childIndex)
    } else {
      null
    }
  }

  def traverse(root: TreeNode): List[TreeNode] = {
    val queue = mutable.Queue[TreeNode]()
    val result = mutable.ListBuffer[TreeNode]()
    queue.enqueue(root)

    while (queue.exists(_ != null)) {
      val node = queue.dequeue()
      result.addOne(node)
      if (node != null) {
        queue.enqueue(node.left)
        queue.enqueue(node.right)
      } else {
        queue.enqueue(null)
        queue.enqueue(null)
      }
    }

    result.toList
  }
}
