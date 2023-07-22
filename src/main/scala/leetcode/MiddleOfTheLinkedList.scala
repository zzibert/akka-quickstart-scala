package leetcode

class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}

class MiddleOfTheLinkedList {
  def middleNode(head: ListNode): ListNode = {
    if (head.next == null) {
      head
    } else {
      var slow = head
      var fast = head
      while (fast != null && fast.next != null) {
        fast = fast.next.next
        slow = slow.next
      }
      slow
    }
  }
}
