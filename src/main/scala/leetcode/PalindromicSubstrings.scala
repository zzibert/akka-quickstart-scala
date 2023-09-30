package leetcode

object PalindromicSubstrings {
  def countSubstrings(s: String): Int = {
    var result = 0
    for (length <- 1 to s.length) {
      for (i <- 0 to (s.length - length)) {
        if (isPalindrome(s.drop(i).take(length))) {
          result += 1
        }
      }
    }

    result
  }

  def isPalindrome(chars: String): Boolean = {
    val length = chars.length

    if (length == 1) {
      return true
    }

    for (i <- 0 until (length/ 2)) {
      if (chars(i) != chars((length-1) - i)) {
        return false
      }
    }

    true
  }
}
