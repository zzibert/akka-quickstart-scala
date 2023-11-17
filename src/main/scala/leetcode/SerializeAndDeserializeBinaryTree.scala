package leetcode

import scala.collection.mutable

class Codec {
  var nodeString = ""
  var chunks: List[Array[String]] = Nil

  // Encodes a list of strings to a single string.
  def serialize(root: TreeNode): String = {
    serializeHelper(root, 0)

    nodeString
  }

  def leftChildIndex(parentIndex: Int): Int = {
    (parentIndex * 2) + 1
  }

  def rightChildIndex(parentIndex: Int): Int = {
    (parentIndex * 2) + 2
  }

  def serializeHelper(root: TreeNode, position: Int): Unit = {
    if (root != null) {
      nodeString += s"|${position}+${root.value}"

      serializeHelper(root.left, leftChildIndex(position))
      serializeHelper(root.right, rightChildIndex(position))
    }
  }

  // Decodes a single string to a list of strings.
  def deserialize(data: String): TreeNode = {
    chunks =
      data
        .split('|')
        .view
        .tail
        .map(pair => pair.split('+'))
        .toList


    deserializeHelper(0)
  }

  def deserializeHelper(position: Int): TreeNode = {
    val rootOption = chunks.find(pair => pair(0).toInt == position)

    rootOption match {
      case Some(root) =>
        val node = new TreeNode(root(1).toInt)
        node.left = deserializeHelper(leftChildIndex(position))
        node.right = deserializeHelper(rightChildIndex(position))
        node


      case None =>
        null
    }

  }
}
