package leetcode

import scala.collection.mutable

object ReorderList {
  def reorderList(head: ListNode): Unit = {
    var current = head
    val nodes = mutable.ListBuffer[ListNode]()

    while (current != null) {
      nodes.prepend(current)
      current = current.next
    }

    nodes.takeInPlace(nodes.length/2)


    current = head

    while (nodes.nonEmpty) {
      val currentNext = current.next
      current.next = nodes.head
      nodes.dropInPlace(1)
      current.next.next = currentNext
      current = currentNext
    }

    current.next = null
  }
}
