package leetcode

class DeleteTheMiddleNodeOfTheLinkedList {
  def deleteMiddle(head: ListNode): ListNode = {
    var slow = head
    if (head.next == null) {
      null
    } else if (head.next.next == null) {
      head.next = null
      head
    } else {
      var fast = head.next.next
      while (fast.next != null && fast.next.next != null) {
        slow = slow.next
        fast = fast.next.next
      }
      val next = slow.next.next
      slow.next = next

      head
    }
  }
}
