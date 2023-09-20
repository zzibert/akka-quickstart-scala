package leetcode

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object GroupAnagrams {
  def groupAnagrams(strs: Array[String]): List[List[String]] = {
    val anagrams = mutable.Map[String, ListBuffer[String]]()

    strs foreach { str =>
      val key = str.toSeq.sorted.unwrap
      val buffer = anagrams.getOrElseUpdate(key, ListBuffer[String]())
      buffer.addOne(str)
    }

    anagrams.values.map(_.toList).toList
  }
}
