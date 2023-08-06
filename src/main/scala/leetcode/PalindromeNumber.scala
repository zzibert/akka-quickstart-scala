package leetcode

object PalindromeNumber {
  def isPalindrome(x: Int): Boolean = {
    val str = x.toString

    for (i <- 0 until str.length / 2) {
      if (str(i) != str((str.length-i)-1)) {
        return false
      }
    }

    return true
  }
}
