package leetcode

object AddTwoNumbers {
  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    addTwoNumbersHelper(l1, l2, 0)
  }

  def addTwoNumbersHelper(l1: ListNode, l2: ListNode, overflow: Int): ListNode = {
    if (l1 == null && l2 == null) {
      if (overflow > 0) {
        new ListNode(overflow)
      } else {
        null
      }
    } else if (l1 == null) {
      addTwoNumbersHelper(new ListNode(0), l2, overflow)
    } else if (l2 == null) {
      addTwoNumbersHelper(l1, new ListNode(0), overflow)
    } else {
      val x = l1.x + l2.x + overflow
      val newNode = new ListNode()
      val newOverflow = x / 10
      newNode.x = x % 10
      newNode.next = addTwoNumbersHelper(l1.next, l2.next, newOverflow)
      newNode
    }
  }


}
