package leetcode

import scala.collection.mutable

class WordDictionary() {
  val zeroNode = new Node(' ')

  class Node(char: Char) {
    val character = char
    var isWord: Boolean = false
    val children = mutable.ArrayBuffer[Node]()

    def search(word: String): Boolean = {
      if (word.isEmpty) {
        isWord
      } else {
        if (word.head == '.') {
          children.exists(_.search(word.tail))
        } else {
          children.find(_.character == word.head) match {
            case Some(child) =>
              child.search(word.tail)

            case None =>
              false
          }
        }
      }
    }

    def addWord(word: String): Unit = {
      if (word.nonEmpty) {
        children.find(_.character == word.head) match {
          case Some(child) =>
            child.addWord(word.tail)

          case None =>
            val node = new Node(word.head)
            children.addOne(node)
            node.addWord(word.tail)
        }
      } else {
        isWord = true
      }
    }
  }

  def addWord(word: String): Unit = {
    zeroNode.addWord(word)
  }

  def search(word: String): Boolean = {
    zeroNode.search(word)
  }

}
