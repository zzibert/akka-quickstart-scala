class Node(val value: Char) {
  var children: Set[Node] = Set.empty
  var isWord = false

  def insert(word: String): Unit = {
    if (word.isEmpty) {
      isWord = true
    } else {
      children.find(_.value == word.head) match {
        case Some(child) =>
          child.insert(word.tail)

        case None =>
          val child = new Node(word.head)
          children += child
          child.insert(word.tail)
      }
    }
  }

  def search(word: String): Boolean = {
    if (word.isEmpty) {
      isWord
    } else {
      children.find(_.value == word.head) match {
        case Some(child) =>
          child.search(word.tail)

        case None =>
          false
      }
    }
  }

  def startsWith(prefix: String): Boolean = {
    if (prefix.isEmpty) {
      true
    } else {
      children.find(_.value == prefix.head) match {
        case Some(child) =>
          child.startsWith(prefix.tail)

        case None =>
          false
      }
    }
  }
}

class Trie() {
  val root = new Node(' ')

  def insert(word: String): Unit = {
    root.insert(word)
  }

  def search(word: String): Boolean = {
    root.search(word)
  }

  def startsWith(prefix: String): Boolean = {
    root.startsWith(prefix)
  }
}
