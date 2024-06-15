package leetcode

import scala.collection.mutable

object Solution {
  def longestStrChain(words: Array[String]): Int = {

    val wordsByLength = mutable.Map[Int, mutable.ArrayBuffer[String]]()
    val seen = mutable.Map[String, Boolean]()
    var result = 0

    words foreach { word =>
      val length = word.length
      wordsByLength.get(length) match {
        case Some(buffer) =>
          buffer.addOne(word)

        case None =>
          val buffer = mutable.ArrayBuffer(word)
          wordsByLength += (length -> buffer)
      }
    }

    words.sortBy(_.length) foreach { word =>
      seen.get(word) match {
        case Some(_) =>
        case None =>
          seen.update(word, true)
          val longestChain = getLongestChain(word, wordsByLength, seen)
          if (longestChain > result) {
            result = longestChain
          }
      }
    }

    result
  }

  def getLongestChain(
      initialWord: String,
      wordsByLength: mutable.Map[Int, mutable.ArrayBuffer[String]],
      seen: mutable.Map[String, Boolean]): Int = {
    var result = 0
    val queue = mutable.Queue[(String, Int)]()
    queue.enqueue((initialWord, 1))

    while (queue.nonEmpty) {
      val (word, size) = queue.dequeue()
      if (size > result) {
        result = size
      }
      wordsByLength.get(word.size + 1) match {
        case Some(words) =>
          val successors = words.view
            .filter(isSuccessor(word, _))
            .filterNot(x => seen.getOrElse(x, false))
          successors foreach { successor =>
            queue.enqueue((successor, size + 1))
            seen.update(successor, true)
          }

        case None =>
      }
    }

    result
  }

  def isSuccessor(a: String, b: String): Boolean = {
    var differs = false
    var isSuccessor = true
    val arrayA = a.toCharArray
    val arrayB = b.toCharArray

    var indexA = 0
    var indexB = 0

    while (indexA < arrayA.length && isSuccessor) {
      if (arrayA(indexA) != arrayB(indexB)) {
        if (differs) {
          isSuccessor = false
        } else {
          differs = true
          indexB += 1
        }
      } else {
        indexA += 1
        indexB += 1
      }
    }

    isSuccessor
  }
}
