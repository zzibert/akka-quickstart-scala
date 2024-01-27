package leetcode

import scala.collection.mutable

object WordBreak {
  def wordBreak(s: String, wordDict: List[String]): Boolean = {
    val trie = new Trie()
    val queue = mutable.Queue[String]()
    val seen = mutable.Map[String, Boolean]()

    wordDict foreach { word =>
      trie.addWord(word, "")
    }

    queue.enqueue(s)

    while (queue.nonEmpty) {
      val s = queue.dequeue()
      val remainders = trie.searchForWords(s)
      if (remainders.exists(_.isEmpty)) {
        return true
      } else {
        remainders foreach { remainder =>
          println(remainder)
          if (!seen.getOrElse(remainder, false)) {
            queue.enqueue(remainder)
            seen.update(remainder, true)
          }
        }
      }
    }

    false
  }

  case class TrieNode(
      value: Int,
      cumulative: String,
      var isWord: Boolean = false,
      var children: Seq[TrieNode] = Nil
  ) {
    def addWord(word: String, cumulative: String): Unit = {
      if (word.isEmpty) {
        isWord = true
      } else {
        children.find(child => child.value == word.head) match {
          case Some(child) =>
            child.addWord(word.tail, cumulative + word.head)

          case None =>
            val newComulative = cumulative + word.head
            val child = TrieNode(word.head, newComulative)
            child.addWord(word.tail, newComulative)
            children = children.appended(child)
        }
      }
    }

    def searchForWords(s: String, words: mutable.ArrayBuffer[String]): Seq[String] = {
      if (isWord) {
        words.addOne(s)
      }

      if (s.isEmpty) {
        words.toSeq
      } else {
        children.find(child => child.value == s.head) match {
          case Some(child) =>
            child.searchForWords(s.tail, words)

          case None =>
            words.toSeq
        }
      }
    }
  }

  class Trie() {
    var children: Seq[TrieNode] = Nil

    def addWord(word: String, cumulative: String): Unit = {
      children.find(child => child.value == word.head) match {
        case Some(child) =>
          child.addWord(word.tail, cumulative)

        case None =>
          val newComulative = cumulative + word.head
          val child = TrieNode(value = word.head, newComulative)
          child.addWord(word.tail, newComulative)
          children = children.appended(child)
      }
    }

    def searchForWords(s: String): Seq[String] = {
      children.find(child => child.value == s.head) match {
        case Some(child) =>
          child.searchForWords(s.tail, mutable.ArrayBuffer())

        case None =>
          Nil
      }
    }
  }
}
