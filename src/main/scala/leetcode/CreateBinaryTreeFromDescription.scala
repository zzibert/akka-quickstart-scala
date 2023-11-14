package leetcode

import scala.collection.mutable

object CreateBinaryTreeFromDescription {
  def createBinaryTree(descriptions: Array[Array[Int]]): TreeNode = {
    val children = mutable.Map[Int, Boolean]()
    val nodes = mutable.Map[Int, TreeNode]()

    descriptions foreach { description =>
      val parent = description(0)
      val child = description(1)
      val isLeft = description(2)

      val parentNode = nodes.getOrElseUpdate(parent, new TreeNode(parent))
      val childNode = nodes.getOrElseUpdate(child, new TreeNode(child))

      if (isLeft == 1) {
        parentNode.left = childNode
      } else {
        parentNode.right = childNode
      }

      children.get(parent) match {
        case Some(_) =>

        case None =>
          children.update(parent, false)
      }

      children.update(child, true)
    }

    val rootIndex = children.find(!_._2).get

    nodes.getOrElse(rootIndex._1, null)
  }
}
