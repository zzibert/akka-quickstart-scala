package leetcode

import scala.collection.mutable

object LongestPalindrome {
  def longestPalindrome(s: String): Int = {
    val letters = mutable.Map[Char, Int]()
    var longest = 0
    var _addOne = false

    s foreach { c =>
      letters.getOrElseUpdate(c, 0)
      letters(c) += 1
    }

    letters.values foreach { count =>
      if (count % 2 == 0) {
        longest += count
      } else {
        longest += (count - 1)
        _addOne = true
      }
    }

    if (_addOne) {
      longest + 1
    } else {
      longest
    }
  }
}
