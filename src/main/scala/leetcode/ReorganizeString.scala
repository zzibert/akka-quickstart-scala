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
    private var lastDeleted: Option[Char] = None

    def length = nodes.length

    def getParentIndex(index: Int): Int = {
      (index / 2) - 1
    }

    def insert(node: CharNode): Unit = {
      if (nodes.isEmpty) {
        nodes.addOne(node)
      } else {
        nodes.addOne(node)
        val parentIndex = getParentIndex(length)
        val parent = nodes(parentIndex)
        if (parent.left == null) {
          parent.left = node
        } else {
          parent.right = node
        }

        swapIfBigger(node, length)
      }
    }

    def pickBigger(node: CharNode): Option[CharNode] = {
      val leftOption =
        Option.when(node.left != null) {
          node.left
        }

      val rightOption =
        Option.when(node.right != null) {
          node.right
        }


      List(leftOption, rightOption).flatten match {
        case Nil => None
        case list =>
          val biggest = list.maxBy(_.value)
          Option.when(biggest.value > 0) {
            biggest
          }
      }
    }

    def take(node: CharNode): Option[Char] = {
      val character = node.character
      node.value -= 1
      swapIfSmaller(node)
      lastDeleted = Some(character)
      lastDeleted
    }

    def takeGreatest(): Option[Char] = {
      val root = nodes.head
      lastDeleted match {
        case Some(last) =>
          if (root.character == last || root.value < 1) {
            pickBigger(root) match {
              case Some(node) =>
                take(node)

              case None => None
            }
          } else {
            take(root)
          }
        case None =>
          if (root.value > 0) {
            take(root)
          } else {
            pickBigger(root) match {
              case Some(node) =>
                take(node)
              case None => None
            }
          }
      }
    }

    def swapIfSmaller(node: CharNode): Unit = {
      pickBigger(node) match {
        case Some(child) =>
          if (child.value > node.value) {
            swapNodes(node, child)
          }
        case None =>

      }
    }

    def swapIfBigger(child: CharNode, index: Int): Unit = {
      val parentIndex = getParentIndex(index)
      if (parentIndex >= 0) {
        val parent = nodes(parentIndex)
        if (parent.value < child.value) {
          swapNodes(parent, child)
          swapIfBigger(parent, parentIndex+1)
        }
      }
    }

    def swapNodes(parent: CharNode, child: CharNode): Unit = {
      val tempValue = parent.value
      val tempCharacter = parent.character
      parent.value = child.value
      parent.character = child.character
      child.value = tempValue
      child.character = tempCharacter

      swapIfSmaller(child)
    }
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

    while (result.length < s.length) {
      val characterOption = heap.takeGreatest()
      characterOption match {
        case Some(character) =>
          result.addOne(character)

        case None =>
          return ""
      }
    }

    result.mkString("")
  }
}
