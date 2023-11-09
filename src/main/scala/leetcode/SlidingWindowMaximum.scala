package leetcode

import scala.collection.mutable

object SlidingWindowMaximum {
  def maxSlidingWindow(nums: Array[Int], k: Int): Array[Int] = {
    val heap = new Heap()
    val result = mutable.ArrayBuffer[Int]()

    val initial = mutable.ArrayBuffer.from(nums.take(k))
    val rest = mutable.ArrayBuffer.from(nums.drop(k))

    initial.foreach(heap.insert)
    result.addOne(heap.getFirst())

    while (rest.nonEmpty) {
      initial.dropInPlace(1)
      initial.addOne(rest.head)
      heap.insert(rest.head)
      rest.dropInPlace(1)

      while (heap.length > 1 && !initial.contains(heap.getFirst())) {
        heap.delete()
      }

      result.addOne(heap.getFirst())
    }


    result.toArray
  }

  class Heap() {
    private val nodes = mutable.ArrayBuffer[TreeNode]()

    def length = nodes.length

    def insert(value: Int): Unit = {
      if (nodes.isEmpty) {
        val node = new TreeNode(value)
        nodes.addOne(node)
      } else {
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
    }

    def delete(): Unit = {
      val value = nodes(length - 1).value
      nodes(0).value = value
      deleteLast()
      nodes.remove(length - 1)

      if (length > 0) {
        swapIfSmaller(nodes(0))
      }
      //      println(s"Deleted: ${value}")
      //      traverse(nodes)
      //      println()
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
      val parentIndex = getParent(index)
      if (parentIndex >= 0) {
        val parent = nodes(parentIndex)
        if (parent.value < child.value) {
          swapValues(parent, child)
          swapIfBigger(parent, parentIndex + 1)
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

    def getParent(index: Int): Int = {
      (index / 2) - 1
    }
  }
}
