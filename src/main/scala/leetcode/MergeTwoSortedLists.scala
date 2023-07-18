package leetcode



class ListNode(_x: Int = 0, _next: ListNode = null) {
  var next: ListNode = _next
  var x: Int = _x
}

//Input: list1 = [1,2,4], list2 = [1,3,4]
//Output: [1,1,2,3,4,4]



object MergeTwoSortedLists {

  def mergeTwoLists(list1: ListNode, list2: ListNode): ListNode = {
    var head: ListNode = null
    var current1: ListNode = null
    var current2: ListNode = null

    if (list1 == null) {
      return list2
    } else if (list2 == null) {
      return list1
    } else if (list1.x < list2.x) {
      head = list1
      current1 = list1
      current2 = list2
    } else {
      head = list2
      current1 = list2
      current2 = list1
    }

    while (true) {
      if (current1.next == null) {
        current1.next = current2
        return head
      } else if (current2 == null) {
        return head
      } else if (current1.next.x < current2.x) {
        current1 = current1.next
      } else {
        val temp = current1.next
        current1.next = current2
        current2 = temp
        current1 = current1.next
      }
    }

    head
  }

  def main(args: Array[String]): Unit = {

  }

}
