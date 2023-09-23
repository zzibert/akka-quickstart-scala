package leetcode

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object TopKFrequentWords {
  def topKFrequent(words: Array[String], k: Int): List[String] = {
    val occurrences = mutable.Map[String, Int]()
    val wordsByOccurrence = mutable.Map[Int, ArrayBuffer[String]]()

    words foreach { word =>
      val value = occurrences.getOrElse(word, 0)
      occurrences += (word -> (value+1))
    }

    occurrences foreach {
      case (word, occurrence) =>
        val buffer = wordsByOccurrence.getOrElseUpdate(occurrence, ArrayBuffer[String]())
        buffer.addOne(word)
    }

    wordsByOccurrence.values foreach { buffer =>
      buffer.sortInPlace()
    }

    wordsByOccurrence.toList.sortBy(_._1).view.reverse.map(_._2).flatten.take(k).toList
  }
}
