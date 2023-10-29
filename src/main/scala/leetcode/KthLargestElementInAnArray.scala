package leetcode

import scala.collection.mutable

object KthLargestElementInAnArray {
  def findKthLargest(nums: Array[Int], k: Int): Int = {
    val root = new TreeNode(nums.head)
    val heap = new Heap()

    for (i <- 0 until nums.length) {
      heap.insert(nums(i))
    }

    for (i <- 1 until k) {
      heap.delete()
    }

    heap.getFirst()
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

      swapIfSmaller(nodes(0))
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
          swapIfBigger(parent, parentIndex+1)
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

  def main(args: Array[String]): Unit = {
//    val nums = Array(164, 6892, 8650, 7176, 767, 3527, 6835, 6215, 3600, 4390, 5820, 1406, 1866, 8651, 2452, 4442, 3569, 9450, 8219, 3778, 3749, 85, 801, 7769, 7604, 4393)
    val nums = Array(3,2,1,5,6,4)
    val reversed = nums.sorted.reverse

//    for (i <- 1 until reversed.length) {
      print(s"reversed: ${reversed(1)} result: ${findKthLargest(nums, 2)}")
//      println()
//    }

//    findKthLargest(nums, 12)
  }
}
