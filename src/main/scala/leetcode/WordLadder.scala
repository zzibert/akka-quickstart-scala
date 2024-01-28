package leetcode

import scala.collection.mutable

object WordLadder {
  def ladderLength(beginWord: String,
                   endWord: String,
                   wordList: List[String]): Int = {
    val differByOneLetter = mutable.Map[String, mutable.ListBuffer[String]]()
    val seen = mutable.Map[String, Boolean]()
    val queue = mutable.Queue[(String, Int)]()

    val completeList = wordList.appended(beginWord).toArray

    for {
      i <- 0 until completeList.length
      j <- i + 1 until completeList.length
    } {
      if (isDifferenceByOneLetter(completeList(i), completeList(j))) {
        val bufferI = differByOneLetter.getOrElseUpdate(
          completeList(i),
          mutable.ListBuffer[String]())
        bufferI.addOne(completeList(j))
        val bufferJ = differByOneLetter.getOrElseUpdate(
          completeList(j),
          mutable.ListBuffer[String]())
        bufferJ.addOne(completeList(i))
      }
    }

    queue.enqueue((beginWord, 1))

    while (queue.nonEmpty) {
      val (word, counter) = queue.dequeue()
      if (word == endWord) {
        return counter
      } else {
        differByOneLetter.get(word) match {
          case Some(neighbors) =>
            neighbors foreach { neighbor =>
              if (!seen.getOrElse(neighbor, false)) {
                seen.update(neighbor, true)
                queue.enqueue((neighbor, counter + 1))
              }
            }

          case _ =>
        }

      }
    }

    0
  }

  def isDifferenceByOneLetter(word1: String, word2: String): Boolean = {
    var differ = false

    for (i <- 0 until word1.length) {
      if (word1(i) != word2(i)) {
        if (!differ) {
          differ = true
        } else {
          return false
        }
      }
    }

    differ
  }
}
