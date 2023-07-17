package leetcode

import scala.collection.mutable

object ValidParentheses {

  def isValid(s: String): Boolean = {
    val stack = mutable.Stack[Char]()
    s foreach { c =>
      if (c == '(' || c == '[' || c == '{') {
        stack.push(c)
      } else if (stack.nonEmpty) {
        val compare = stack.pop()
        if ((c == ')') && (compare != '(')) {
          return false
        } else if ((c == ']') && (compare != '[')) {
          return false
        } else if ((c == '}') && (compare != '{')) {
          return false
        }
      } else {
        return false
      }
    }

    stack.isEmpty
  }

  def main(args: Array[String]): Unit = {
    println(isValid("()[]{}"))
  }

}
