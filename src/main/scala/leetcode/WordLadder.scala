package leetcode

object WordLadder {
  def ladderLength(beginWord: String, endWord: String, wordList: List[String]): Int = {
    val oneLetterDifference = Map[String, Set[String]]()
    val tried = Map[String, Boolean]()

    for (i <- 0 until wordList.length) {
      val word = wordList(i)
      var buffer = oneLetterDifference.getOrElse(word, Set[String]())

      for (j <- i+1 until wordList.length) {
        val secondWord = wordList(j)
        if (differByOneLetter(word.toCharArray, secondWord.toCharArray)) {
          buffer.addOne(secondWord)
          val secondBuffer = oneLetterDifference.getOrElse(secondWord, mutable.Set[String]())
          secondBuffer.addOne(word)
          oneLetterDifference.update(secondWord, secondBuffer)
        }
      }

      oneLetterDifference.update(word, buffer)
    }

    val buffer = mutable.Set[String]()
    val beginWordArray = beginWord.toCharArray

    for (i <- 0 until wordList.length) {
      if (differByOneLetter(beginWordArray, wordList(i).toCharArray)) {
        buffer.addOne(wordList(i))
      }
    }

    oneLetterDifference.update(beginWord, buffer)

    val queue = mutable.Queue[(Int, String)]()

    queue.enqueue((1, beginWord))
    tried.update(beginWord, true)

    while (queue.nonEmpty) {
      val (length, word) = queue.dequeue()
      println(s"length: $length, word: $word")
      if (word == endWord) {
        return length
      } else {
        oneLetterDifference.get(word) match {
          case Some(buffer) =>
            buffer.view
              .filterNot(word => tried.getOrElse(word, false))
              .foreach { w =>
                println(s"enqueining: length: ${length+1} word: $w")
                tried.update(w, true)
                queue.enqueue((length+1, w))
              }
          case None =>
        }
      }
    }

    0
  }

  def differByOneLetter(word1: Array[Char], word2: Array[Char]): Boolean = {
    var count = 0

    for (i <- 0 until word1.length) {
      if (word1(i) != word2(i)) {
        count += 1
        if (count > 1) {
          return false
        }
      }
    }

    count < 2
  }
}
