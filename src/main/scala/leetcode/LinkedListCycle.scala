package leetcode

import scala.collection.mutable.Map

class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}

object LinkedListCycle {
  def hasCycle(head: ListNode): Boolean = {
    if (head == null || head.next == null) {
      false
    } else {
      var slow = head
      var fast = head.next

      while(fast != null && fast.next != null) {
        if (slow == fast) {
          return true
        } else {
          slow = slow.next
          fast = fast.next.next
        }
      }

      false
    }
  }
}
