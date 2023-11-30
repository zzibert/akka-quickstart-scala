package leetcode

import scala.collection.mutable

object CopyListWithRandomPointer {
  class Node(var _value: Int) {
    var value: Int = _value
    var next: Node = null
    var random: Node = null
  }
  def copyRandomList(head: Node): Node = {
    val nodes = mutable.Map[Node, Int]()
    val newNodes = mutable.Map[Int, Node]()
    var index = 0

    val newHead =
      if (head != null) {
        new Node(head.value)
      } else {
        null
      }

    nodes.update(head, index)
    newNodes.update(index, newHead)

    index += 1

    var oldCurrent = head
    var current = newHead

    while (current != null) {
      oldCurrent = oldCurrent.next

      current.next =
        if (oldCurrent != null) {
          val newNode = new Node(oldCurrent.value)
          nodes.update(oldCurrent, index)
          newNodes.update(index, newNode)
          index += 1
          newNode
        } else {
          null
        }

      current = current.next
    }

    current = newHead
    oldCurrent = head

    while (current != null) {
      val random = oldCurrent.random
      nodes.get(random) match {
        case Some(randomIndex) =>
          val randomNode = newNodes.getOrElse(randomIndex, null)
          current.random = randomNode

        case None =>
      }
      oldCurrent = oldCurrent.next
      current = current.next
    }

    newHead
  }

}
