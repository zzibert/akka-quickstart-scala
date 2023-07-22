package leetcode

import scala.collection.mutable.ArrayBuffer

class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}

object PalindromeLinkedList {
  def isPalindrome(head: ListNode): Boolean = {
    val nodes = ArrayBuffer[Int]()
    var h = head

    while (h != null) {
      nodes.addOne(h.x)
      h = h.next
    }

    val length = nodes.length

    for (i <- 0 to (length / 2)) {
      if (nodes(i) != nodes((length-1)-i)) {
        return false
      }
    }

    true
  }
}
