package leetcode

object MergeKSortedLists {
  def mergeKLists(lists: Array[ListNode]): ListNode = {
    val start = new ListNode()
    var current = start

    val listsWithIndex = lists.zipWithIndex

    while (listsWithIndex.exists(_._1 != null)) {
      val minimal = getMinimal(listsWithIndex)
      current.next = listsWithIndex(minimal)._1
      current = current.next
      val next = (listsWithIndex(minimal)._1.next, minimal)
      listsWithIndex(minimal) = next
    }

    current.next = null

    start.next
  }

  def getMinimal(lists: Array[(ListNode, Int)]): Int = {
    var current = Int.MaxValue
    var _index = -1

    lists.view
      .filter(_._1 != null)
      .foreach {
        case (head, index) =>
          if (head.x < current) {
            current = head.x
            _index = index
          }
      }

    _index
  }
}
