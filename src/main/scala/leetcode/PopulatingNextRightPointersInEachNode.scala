package leetcode

import scala.collection.mutable

class Node(var _value: Int) {
  var value: Int = _value
  var left: Node = null
  var right: Node = null
  var next: Node = null
}

object PopulatingNextRightPointersInEachNode {
  def connect(root: Node): Node = {
    val queue = mutable.Queue[Node]()

    var level = 1
    var count = 0
    var previous = null

    if (root == null) {
      return root
    }

    queue.enqueue(root)

    while (queue.nonEmpty) {
      val node = queue.dequeue()
      if (node.left != null) {
        queue.enqueue(node.left)
        queue.enqueue(node.right)
      }
      count += 1

      if (count == level) {
        node.next = null
        level *= 2
        count = 0
      } else {
        node.next = queue.head
      }
    }

    root
  }
}
