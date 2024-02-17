package leetcode

import scala.collection.mutable

object MinimumWindowSubstring {
  def minWindow(s: String, t: String): String = {
    var length = t.length

    while (length <= s.length) {
      val characters = mutable.Map[Char, Int]()
      t foreach { c =>
        val count = characters.getOrElseUpdate(c, 0)
        characters.update(c, count + 1)
      }

      // initial fill
      for (i <- 0 until length) {
        val character = s(i)
        characters.get(character) match {
          case Some(count) =>
            characters.update(character, count - 1)

          case _ =>
        }
      }

      if (allEqualOrLessThanZero(characters)) {
        return s.substring(0, length)
      }

      for (next <- length until s.length) {
        val delete = s(next-length)
        characters.get(delete) match {
          case Some(count) =>
            characters.update(delete, count+1)
          case _ =>
        }

        val add = s(next)
        characters.get(add) match {
          case Some(count) =>
            characters.update(add, count-1)
            if (count == 1 && allEqualOrLessThanZero(characters)) {
              println(s"the length is $length")
              return s.substring(next-(length-1), next+1)
            }
          case _ =>
        }
      }

      length += 1
    }

    ""
  }

  def allEqualOrLessThanZero(characters: mutable.Map[Char, Int]): Boolean = {
    characters.forall(_._2 < 1)
  }
}
