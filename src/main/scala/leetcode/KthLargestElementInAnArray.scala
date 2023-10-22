package leetcode

import scala.collection.mutable

object KthLargestElementInAnArray {
  def findKthLargest(nums: Array[Int], k: Int): Int = {
    val root = new TreeNode(nums.head)
    val heap = new Heap(root)

    for (i <- 1 until nums.length) {
      heap.insert(nums(i))
    }

    for (i <- 1 until k) {
      heap.delete()
    }

    heap.getFirst()
  }

  class Heap(root: TreeNode) {
    private val nodes = mutable.ArrayBuffer[TreeNode](root)

    def length = nodes.length

    def insert(value: Int): Unit = {
      val node = new TreeNode(value)
      nodes.addOne(node)
      val parentIndex = getParent(length)
      val parent = nodes(parentIndex)
      if (parent.left == null) {
        parent.left = node
      } else {
        parent.right = node
      }

      swapIfBigger(node, length)
    }

    def delete(): Unit = {
      nodes(0).value = nodes(length - 1).value
      deleteLast()
      nodes.remove(length - 1)

      swapIfSmaller(nodes(0))
    }

    def deleteLast(): Unit = {
      val parentIndex = getParent(length)
      val parent = nodes(parentIndex)
      if (parent.right != null) {
        parent.right = null
      } else {
        parent.left = null
      }
    }

    def getFirst(): Int = {
      if (length > 0) {
        nodes(0).value
      } else {
        0
      }
    }

    def swapIfBigger(child: TreeNode, index: Int): Unit = {
      val parentIndex = getParent(index + 1)
      if (parentIndex >= 0) {
        val parent = nodes(parentIndex)
        if (parent.value < child.value) {
          swapValues(parent, child)
          swapIfBigger(parent, parentIndex)
        }
      }
    }

    def swapIfSmaller(node: TreeNode): Unit = {
      val leftOption =
        Option.when(node.left != null) {
          node.left
        }

      val rightOption =
        Option.when(node.right != null) {
          node.right
        }

      List(leftOption, rightOption).flatten match {
        case List(child) =>
          if (child.value > node.value) {
            swapValues(node, child)
            swapIfSmaller(child)
          }

        case List(left, right) =>
          val bigger =
            if (left.value > right.value) {
              left
            } else {
              right
            }
          if (bigger.value > node.value) {
            swapValues(node, bigger)
            swapIfSmaller(bigger)
          }

        case _ =>
      }
    }

    def swapValues(parent: TreeNode, child: TreeNode): Unit = {
      val temp = child.value
      child.value = parent.value
      parent.value = temp
    }

    def getParent(length: Int): Int = {
      Math.max((length / 2) - 1, 0)
    }
  }

  def traverse(node: TreeNode): Unit = {
    if (node != null) {
      traverse(node.left)
      traverse(node.right)
    }
  }

  def main(args: Array[String]): Unit = {

  }
}
