package leetcode

object SwapNodesInPairs {
  def swapPairs(head: ListNode): ListNode = {
    swapPairsHelper(head)

    head
  }

  def swapPairsHelper(head: ListNode): Unit = {
    if (head != null && head.next != null) {
      val temp = head.x
      head.x = head.next.x
      head.next.x = temp
      swapPairsHelper(head.next.next)
    }
  }
}
