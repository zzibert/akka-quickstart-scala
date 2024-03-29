package leetcode

import scala.collection.mutable

class LFUCache(_capacity: Int) {
  val nodes = mutable.Map[Int, KeyNode]()

  val minimumChain = mutable.ArrayBuffer[KeyNode]()

  case class KeyNode(
      var key: Int,
      var value: Int,
      var counter: Int = 1,
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
          val deleted = minimumChain.head
          nodes -= deleted.key
          minimumChain.remove(0)
          addNode(key, value)
        }
    }
  }

  def addNode(key: Int, value: Int): Unit = {
    val node = KeyNode(key = key, value = value)
    nodes += (key -> node)
    if (minimumChain.length > 0) {
      node.next = minimumChain.head
    }
    minimumChain.insert(0, node)
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
