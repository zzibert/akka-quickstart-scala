package leetcode

import scala.collection.mutable

object TaskScheduler {

  class TaskNode(
    _task: Char,
    _value: Int,
    _left: TaskNode = null,
    _right: TaskNode = null
  ) {
    var value: Int = _value
    var task: Char = _task
    var left: TaskNode = _left
    var right: TaskNode = _right
  }

  class TaskHeap(_cooldown: Int) {
    val previousTasks = mutable.ArrayBuffer.fill(_cooldown)(' ')
    val nodes = mutable.ArrayBuffer[TaskNode]()

    def length = nodes.length

    def getParentIndex(index: Int): Int = {
      (index / 2) - 1
    }

    def insert(node: TaskNode): Unit = {
      if (nodes.isEmpty) {
        nodes.addOne(node)
      } else {
        nodes.addOne(node)
        val parentIndex = getParentIndex(length)
        val parent = nodes(parentIndex)
        if (parent.left == null) {
          parent.left = node
        } else {
          parent.right = node
        }

        swapIfBigger(node, length)
      }
    }

    def pickBigger(node: TaskNode): Option[TaskNode] = {
      val leftOption =
        Option.when(node.left != null) {
          node.left
        }

      val rightOption =
        Option.when(node.right != null) {
          node.right
        }

      List(leftOption, rightOption).flatten match {
        case Nil => None
        case list =>
          list.filterNot(node => previousTasks.contains(node.task)) match {
            case Nil =>
              list.map(node => pickBigger(node)).flatten.maxBy(_.value)

          }


      }

    }

  }
  def leastInterval(tasks: Array[Char], n: Int): Int = {

  }
}
