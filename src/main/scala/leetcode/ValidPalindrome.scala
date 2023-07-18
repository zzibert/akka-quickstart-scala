package leetcode

object ValidPalindrome {
  def isPalindrome(s: String): Boolean = {
    val str = s.trim.toLowerCase().filter(c => c.isLetter || c.isDigit)

    var length = str.length

    if (length < 2) {
      return true
    }

    for (i <- 0 until (length / 2)) {
      if (str(i) != str((length - (i+1)))) {
        return false
      }
    }

    return true
  }


}
