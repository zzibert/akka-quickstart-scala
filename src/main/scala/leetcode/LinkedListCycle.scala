package leetcode

import scala.collection.mutable.Map

class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}

object LinkedListCycle {
  def hasCycle(head: ListNode): Boolean = {
    val nodes = Map[ListNode, Boolean]()
    var h = head

    while(h != null) {
      if (nodes.getOrElse(h, false)) {
        return true
      } else {
        nodes += (h -> true)
        h= h.next
      }
    }

    false
  }
}
