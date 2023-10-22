//package leetcode
//
//class ListNode(var _x: Int = 0) {
//  var next: ListNode = null
//  var x: Int = _x
//}
//
//object OddEvenLinkedList {
//  def oddEvenList(head: ListNode): ListNode = {
//    if (head == null || head.next == null) {
//      return head
//    }
//
//
//    var oddCurrent: ListNode = head
//    var evenHead: ListNode = head.next
//    var evenCurrent: ListNode = evenHead
//
//    while (oddCurrent != null && oddCurrent.next != null) {
//      val oddTemp = oddCurrent.next.next
//      oddCurrent.next = oddTemp
//      oddCurrent = oddCurrent.next
//      if (oddCurrent != null) {
//        evenCurrent.next = oddCurrent.next
//        evenCurrent = evenCurrent.next
//      }
//    }
//
//    oddCurrent.next = evenHead
//
//    head
//  }
//}
