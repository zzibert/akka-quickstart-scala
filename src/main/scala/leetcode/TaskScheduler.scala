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
    var counter = 0

    def length = nodes.length

    def getParentIndex(index: Int): Int = {
      (index / 2) - 1
    }

    def result = counter

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

    def swapIfBigger(child: TaskNode, index: Int): Unit = {
      val parentIndex = getParentIndex(index)
      if (parentIndex >= 0) {
        val parent = nodes(parentIndex)
        if (parent.value < child.value) {
          swapNodes(parent, child)
          swapIfBigger(parent, parentIndex + 1)
        }
      }
    }

    def takeGreatest(root: TaskNode): Option[TaskNode] = {
      if (root != null) {
        if (previousTasks.contains(root.task)) {
          List(takeGreatest(root.left), takeGreatest(root.right)).flatten match {
            case Nil => None
            case list => Some(list.maxBy(_.value))
          }
        } else {
          Some(root)
        }
      } else {
        None
      }
    }

    def run(): Unit = {
      takeGreatest(nodes(0)) match {
        case Some(node) =>
          addTask(node.task)
          node.value -=1
          swapIfSmaller(node)
        case None =>
          addTask(' ')
      }
    }

    def swapIfSmaller(node: TaskNode): Unit = {
      pickBigger(node) match {
        case Some(child) =>
          if (child.value > node.value) {
            swapNodes(node, child)
          }
        case None =>
      }
    }

    def swapNodes(parent: TaskNode, child: TaskNode): Unit = {
      val tempValue = parent.value
      val tempTask = parent.task
      parent.value = child.value
      parent.task = child.task
      child.value = tempValue
      child.task = tempTask

      swapIfSmaller(child)
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
          val biggest = list.maxBy(_.value)
          Option.when(biggest.value > 0) {
            biggest
          }
      }
    }

    def addTask(task: Char): Unit = {
      previousTasks.drop(1).addOne(task)
      counter += 1
    }
  }
  def leastInterval(tasks: Array[Char], n: Int): Int = {
    val heap = new TaskHeap(n)
    val occurrences = mutable.Map[Char, Int]()

    tasks foreach { task =>
      val count = occurrences.getOrElse(task, 0)
      occurrences.update(task, count+1)
    }

    occurrences foreach {
      case (task, count) =>
        val node = new TaskNode(task, count)
        heap.insert(node)
    }

    while(heap.nodes(0).value > 0) {
      heap.run()
    }

    heap.counter
  }
}
