package leetcode

import scala.collection.mutable

object ImplementTrie {
  class Trie() {
    var value: Char = '0'
    var isWord: Boolean = false
    var children = mutable.Map[Char, Trie]()

    def insert(word: String) {
      if (word != "") {
        val head = word.head
        val tail = word.tail

        children.get(head) match {
          case Some(child) =>
            child.insert(tail)

          case None =>
            val child = new Trie()
            child.value = head
            children += (head -> child)
            child.insert(tail)
        }
      } else {
        isWord = true
      }
    }

    def search(word: String): Boolean = {
      if (word == "") {
        isWord
      } else {
        val head = word.head
        val tail = word.tail
        children.get(head) match {
          case Some(child) =>
            child.search(tail)
          case None =>
            false
        }
      }
    }

    def startsWith(prefix: String): Boolean = {
      if (prefix == "") {
        true
      } else {
        val head = prefix.head
        val tail = prefix.tail
        children.get(head) match {
          case Some(child) =>
            child.startsWith(tail)
          case None =>
            false
        }
      }
    }

  }
}
