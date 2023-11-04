package leetcode

import scala.collection.mutable

class LFUCache(_capacity: Int) {
  val nodes = mutable.Map[Int, KeyNode]()

  var minimumChain: Option[KeyNode] = None

  case class KeyNode(
      var key: Int,
      var value: Int,
      var counter: Int = 1,
      var previous: KeyNode = null,
      var next: KeyNode = null,
  )

  def size: Int = nodes.size

  def get(key: Int): Int = {
    nodes.get(key) match {
      case Some(node) =>
        val result = node.value
        increment(node)
        result

      case None =>
        -1
    }
  }

  def put(key: Int, value: Int): Unit = {
    nodes.get(key) match {
      case Some(node) =>
        node.value = value
        increment(node)

      case None =>
        if (size < _capacity) {
          addNode(key, value)
        } else {
          minimumChain foreach { deleted =>
            nodes -= deleted.key
            if (deleted.next != null) {
              minimumChain = Some(deleted.next)
            } else {
              minimumChain = None
            }
            addNode(key, value)
          }
        }
    }
  }

  def addNode(key: Int, value: Int): Unit = {
    val node = KeyNode(key = key, value = value)
    nodes += (key -> node)
    minimumChain match {
      case Some(head) =>
        head.previous = node
        node.next = head
        minimumChain = Some(node)
      case None =>
        minimumChain = Some(node)
    }
    swap(node, node.next)
  }

  def increment(node: KeyNode): Unit = {
    node.counter += 1
    swap(node, node.next)
  }

  def swap(node: KeyNode, next: KeyNode): Unit = {
    if (next != null && node.counter >= next.counter) {
      val tempKey = node.key
      val tempVal = node.value
      val tempCounter = node.counter

      node.key = next.key
      node.value = next.value
      node.counter = next.counter

      next.key = tempKey
      next.value = tempVal
      next.counter = tempCounter


      nodes += (node.key -> node)
      nodes += (next.key -> next)

      swap(next, next.next) // iffy
    }
  }

}
