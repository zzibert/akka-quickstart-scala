package leetcode

class Trie() {
  val root = new Node(' ')

  def insert(word: String): Unit = {
    val array = word.toCharArray
    root.insert(array)
  }

  def search(word: String): Boolean = {
    val array = word.toCharArray
    root.search(array)
  }

  def startsWith(prefix: String): Boolean = {
    val array = prefix.toCharArray
    root.startsWith(array)
  }

}

class Node(val char: Char) {
  var isWord: Boolean = false
  var children: Seq[Node] = Seq.empty

  def insert(word: Array[Char]): Unit = {
    if (word.isEmpty) {
      isWord = true
    } else {
      children.find(child => child.char == word.head) match {
        case Some(child) =>
          child.insert(word.tail)

        case None =>
          val child = new Node(word.head)
          children = children :+ child
          child.insert(word.tail)
      }
    }
  }

  def search(word: Array[Char]): Boolean = {
    if (word.isEmpty) {
      isWord
    } else {
      children.find(child => child.char == word.head) match {
        case Some(child) =>
          child.search(word.tail)

        case None =>
          false
      }
    }
  }

  def startsWith(prefix: Array[Char]): Boolean = {
    if (prefix.size == 1) {
      children.exists(child => child.char == prefix.head)
    } else {
      children.find(child => child.char == prefix.head) match {
        case Some(child) => child.startsWith(prefix.tail)

        case None => false
      }
    }
  }
}
