package leetcode

object LongestPalindromicSubstring {
  def longestPalindrome(s: String): String = {
    val length = s.length
    var palindromeLength = length

    while (palindromeLength > 0) {
      for (i <- 0 to (length - palindromeLength)) {
        val stringTry = s.drop(i).take(palindromeLength)
        if (isPalindrome(stringTry)) {
          return stringTry
        }
      }
      palindromeLength -= 1
    }

    ""
  }

  def isPalindrome(s: String): Boolean = {
    for (i <- 0 until (s.length/2)) {
      if (s(i) != s((s.length-1)-i)) {
        return false
      }
    }
    true
  }
}
