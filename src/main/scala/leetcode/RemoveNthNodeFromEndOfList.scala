package leetcode

import scala.collection.mutable.ArrayBuffer

object RemoveNthNodeFromEndOfList {
  def removeNthFromEnd(head: ListNode, n: Int): ListNode = {
    val nodes = ArrayBuffer[ListNode]()
    var current = head

    while (current != null) {
      nodes.addOne(current)
      current = current.next
    }

    if (nodes.length < 2) {
      null
    } else {
      val removalIndex = nodes.length - n
      if (removalIndex == 0) {
        head.next
      } else {
        val previous = nodes(removalIndex-1)
        val next = nodes(removalIndex).next
        previous.next = next
        head
      }
    }
  }
}
