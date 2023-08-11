package leetcode

import scala.collection.mutable

object LongestSubstringWithoutRepeatingCharacters {
  def lengthOfLongestSubstring(s: String): Int = {
    val seen = mutable.Map[Char, Boolean]()
    var result = 0

    for (i <- 0 until s.length) {
      seen.clear()
      var localResult = 0
      var start = i
      while (start < s.length && seen.get(s(start)).isEmpty) {
        seen += (s(start) -> true)
        localResult += 1
        start += 1
      }
      if (localResult > result) {
        result = localResult
      }
    }
    result
  }
}
