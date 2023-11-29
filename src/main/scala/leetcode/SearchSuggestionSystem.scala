package leetcode

import scala.collection.mutable

object SearchSuggestionSystem {

  def suggestedProducts(products: Array[String], searchWord: String): List[List[String]] = {
    val trie = new Trie()
    val result = mutable.ListBuffer[List[String]]()

    products foreach { product =>
      trie.addWord(product)
    }

//    trie.getWords()

    val charArray = searchWord.toCharArray

    var node = trie.root

    for (i <- 0 until searchWord.length) {
      node.findNode(Array(charArray(i))) match {
        case Some(found) =>
          val threeWords = found.firstThreeWords()
          result.addOne(threeWords)
          node = found

        case None =>
          return result.padTo(searchWord.length, Nil).toList
      }
    }

    result.toList
  }

  class Node(parent: String, char: Char) {

    val children = mutable.ListBuffer[Node]()
    val keyword: String = parent.concat(char.toString)
    var isWord = false
    val character = char

    def addChild(chars: Array[Char]): Unit = {
      if (chars.isEmpty) {
        isWord = true
      } else {
        children.find(_.character == chars.head) match {
          case Some(child) =>
            child.addChild(chars.tail)

          case None =>
            val child = new Node(keyword, chars.head)
            children.addOne(child)
            child.addChild(chars.tail)
        }
      }
    }

    def findNode(search: Array[Char]): Option[Node] = {
      if (search.isEmpty) {
        Some(this)
      } else {
        children.find(_.character == search.head) match {
          case Some(child) =>
            child.findNode(search.tail)

          case None =>
            None
        }
      }
    }

    def firstThreeWords(): List[String] = {
      val words = mutable.ListBuffer[String]()
      val queue = mutable.Queue[Node]()

      queue.enqueue(this)

      while (queue.nonEmpty) {
        val node = queue.dequeue()
        if (node.isWord) {
          words.addOne(node.keyword.trim)
        }
        node.children.foreach(queue.enqueue)
      }

      words.view.sorted
        .take(3)
        .toList
    }

    def getWords(): Unit = {
      if (isWord) {
        println(keyword)
      }

      children.foreach(_.getWords())
    }
  }

  class Trie() {
    val root = new Node(parent = "", char = ' ')

    def addWord(word: String): Unit = {
      val chars = word.toCharArray

      root.addChild(chars)
    }

    def findNode(search: Array[Char]): Option[Node] = {
      root.findNode(search)
    }

    def getWords(): Unit = {
      root.getWords()
    }

  }
}
