package leetcode

import scala.collection.mutable

object Solution {
  def lengthOfLongestSubstring(s: String): Int = {
    val array = s.toCharArray
    var maxLength = 0
    var i = 0
    var isNotDetermined = true
    while (isNotDetermined && i < s.length) {
      var j = i
      var isUnique = true
      var currentLength = 0
      val characters = mutable.Map[Char, Boolean]()
      while (isUnique && j < s.length) {
        val char = array(j)
        characters.get(char) match {
          case Some(_) => isUnique = false
          case None =>
            characters.update(char, true)
            currentLength += 1
        }

        j += 1
      }

      if (currentLength > maxLength) {
        maxLength = currentLength
      }

      i += 1

      if (currentLength >= (s.length - i)) {
        isNotDetermined = false
      }
    }

    maxLength
  }
}
