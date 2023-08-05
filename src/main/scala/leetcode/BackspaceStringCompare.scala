package leetcode

import scala.collection.mutable

object BackspaceStringCompare {
  def backspaceCompare(s: String, t: String): Boolean = {
    val lettersS = createFinalString(s)
    val lettersT = createFinalString(t)

    while (true) {
      if (lettersS.isEmpty && lettersT.isEmpty) {
        return true
      } else if (lettersS.isEmpty || lettersT.isEmpty) {
        return false
      } else {
        val x = lettersS.pop()
        val y = lettersT.pop()
        if (x != y) {
          return false
        }
      }
    }

    true
  }

  def createFinalString(s: String): mutable.Stack[Char] = {
    val letters = mutable.Stack[Char]()
    for (c <- s) {
      if (c == '#') {
        if (letters.nonEmpty) {
          letters.pop()
        }
      } else {
        letters.push(c)
      }
    }

    letters
  }
}
