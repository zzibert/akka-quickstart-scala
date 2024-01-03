package leetcode

import scala.collection.mutable

object MinimumRemoveToMakeValidParantheses {
  def minRemoveToMakeValid(s: String): String = {
    val paranthesesStack = mutable.Stack[Int]()
    val removedParanthesesIndexes = mutable.Map[Int, Boolean]()

    s.zipWithIndex foreach {
      case (char, index) =>
        char match {
          case '(' =>
            paranthesesStack.push(index)

          case ')' =>
            if (paranthesesStack.isEmpty) {
              removedParanthesesIndexes.update(index, true)
            } else {
              paranthesesStack.pop()
            }

          case _ =>
        }
    }

    while (paranthesesStack.nonEmpty) {
      val removedIndex = paranthesesStack.pop()
      removedParanthesesIndexes.update(removedIndex, true)
    }

    (s.zipWithIndex collect {
      case (char, index) if !removedParanthesesIndexes.getOrElse(index, false) => char
    }).mkString
  }
}
