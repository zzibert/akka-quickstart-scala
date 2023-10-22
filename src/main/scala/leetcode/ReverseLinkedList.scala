//package leetcode
//
//class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
//  var value: Int = _value
//  var left: TreeNode = _left
//  var right: TreeNode = _right
//}
//
//object ReverseLinkedList {
//  def reverseList(head: ListNode): ListNode = {
//    var current = head
//    if (current == null || current.next == null) {
//      return current
//    }
//
//    var next = current.next
//    current.next = null
//
//    while (true) {
//      if (next.next == null) {
//        next.next = current
//        return next
//      } else {
//        val temp = next.next
//        next.next = current
//        current = next
//        next = temp
//      }
//    }
//    next
//  }
//}
