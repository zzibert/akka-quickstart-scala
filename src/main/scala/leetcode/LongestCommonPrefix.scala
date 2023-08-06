package leetcode

import scala.collection.mutable


class LongestCommonPrefix {
  def longestCommonPrefix(strs: Array[String]): String = {
    val letters = mutable.Map[Int, mutable.Set[Char]]()
    var result = ""
    var shortest = Int.MaxValue

    strs foreach { str =>
      if (str.length < shortest) {
        shortest = str.length
      }
    }

    strs foreach { str =>
      str.zipWithIndex foreach {
        case (c, i) =>
          val s = letters.getOrElseUpdate(i, mutable.Set[Char]())
          s.add(c)
      }
    }

    val length = letters.keys.toArray.length

    for (i <- 0 until shortest) {
      val s = letters(i).toArray
      if (s.length == 1) {
        result += s(0)
      } else {
         return result
      }
    }

    result
  }
}
