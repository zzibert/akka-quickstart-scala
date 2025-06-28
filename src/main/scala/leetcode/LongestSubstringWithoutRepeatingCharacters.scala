import scala.collection.mutable

object Solution {
  def lengthOfLongestSubstring(s: String): Int = {
    val seen = mutable.Map[Char, Int]()
    var maxLength = 1
    var start = 0

    if (s.isEmpty) {
      return 0
    }

    while (start < s.length) {
      val character = s(start)
      seen.get(character) match {
        case Some(result) =>
          val length = seen.keys.size
          if (length > maxLength) {
            maxLength = length
          }
          seen.clear()
          start = result+1

        case None =>
          seen.update(character, start)
          start += 1
      }
    }

    Math.max(maxLength, seen.keys.size)
  }
}
