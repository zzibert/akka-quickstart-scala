package leetcode

import scala.collection.mutable

class Codec {
  // Encodes a list of strings to a single string.
  def serialize(root: TreeNode): String = {
    serializeHelper(root, 0)
  }

  def serializeHelper(root: TreeNode, position: Int): String = {
    if (root != null) {
      s"|${position}%${root.value}" + serializeHelper(
        root.left,
        leftChildIndex(position)) + serializeHelper(root.right,
                                                    rightChildIndex(position))
    } else {
      ""
    }
  }

  def leftChildIndex(index: Int) = (index * 2) + 1

  def rightChildIndex(index: Int) = (index * 2) + 2

  // Decodes a single string to a list of strings.
  def deserialize(data: String): TreeNode = {
    val nodes =
      data
        .split('|')
        .tail
        .map(node => node.split("%"))
        .map(valueAndPosition =>
          (valueAndPosition(0).toInt, valueAndPosition(1).toInt))

    if (nodes.isEmpty) {
      null
    } else {
      deserializeHelper(nodes)
    }
  }

  def deserializeHelper(nodes: Array[(Int, Int)]): TreeNode = {
    val positionToValue = mutable.Map[Int, Int]()

    nodes foreach {
      case (position, value) =>
        positionToValue.update(position, value)
    }

    val node = nodes.head
    val root = new TreeNode(node._2)

    createNodes(root, 0, positionToValue)

    root
  }

  def createNodes(
      node: TreeNode,
      position: Int,
      positionToValue: mutable.Map[Int, Int]
  ): Unit = {
    // left child
    val leftIndex = leftChildIndex(position)
    positionToValue.get(leftIndex) match {
      case Some(leftChildValue) =>
        val leftChild = new TreeNode(leftChildValue)
        node.left = leftChild
        createNodes(leftChild, leftIndex, positionToValue)

      case _ =>
    }

    // right child
    val rightIndex = rightChildIndex(position)
    positionToValue.get(rightIndex) match {
      case Some(rightChildValue) =>
        val rightChild = new TreeNode(rightChildValue)
        node.right = rightChild
        createNodes(rightChild, rightIndex, positionToValue)

      case _ =>
    }
  }
}
