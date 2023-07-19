package leetcode

import scala.collection.mutable

object ValidAnagram {
  def isAnagram(s: String, t: String): Boolean = {
    val sMap = mutable.Map[Char, Int]()
    val tMap = mutable.Map[Char, Int]()

    for (ch <- s) {
      sMap += (ch -> (sMap.getOrElse(ch, 0) + 1))
    }

    for (ch <- t) {
      tMap += (ch -> (tMap.getOrElse(ch, 0) + 1))
    }

    if (tMap.keySet != sMap.keySet) {
      return false
    }

    sMap foreach {
      case (ch, count) =>
        if (tMap(ch) != count) {
          return false
        }
    }

    true
  }
}
