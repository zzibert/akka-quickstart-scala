package leetcode

import scala.collection.mutable

object ReorganizeString {
  class CharNode(
     _character: Char,
     _value: Int,
     _left: CharNode = null,
     _right: CharNode = null
  ) {
    var value: Int = _value
    var character: Char = _character
    var left: CharNode = _left
    var right: CharNode = _right
  }

  class CharHeap() {
    private val nodes = mutable.ArrayBuffer[CharNode]()
    private val lastDeleted: Option[Char] = None

    def length = nodes.length

    def insert(node: CharNode): Unit = {
      if (nodes.isEmpty) {
        nodes.addOne(node)
      } else {
        nodes.addOne(node)
        val parentIndex = getParent(length)
        val parent = nodes(parentIndex)
        if (parent.left == null) {
          parent.left = node
        } else {
          parent.right = node
        }

        swapIfBigger(node, length)
      }
    }

    def delete(): Option[Char]
  }
  def reorganizeString(s: String): String = {
    val result = mutable.ArrayBuffer[Char]()
    val heap = new CharHeap()
    val occurrences = mutable.Map[Char, Int]()

    s foreach { c =>
      val count = occurrences.getOrElse(c, 0)
      occurrences.update(c, count+1)
    }

    occurrences.toArray foreach {
      case (character, count) =>
        val node = new CharNode(_value = count, _character = character)
        heap.insert(node)
    }

    while (heap.length > 0) {
      val characterOption = heap.delete()
      characterOption match {
        case Some(character) =>
          result.addOne(character)

        case None =>
          return ""
      }
    }
  }
}
