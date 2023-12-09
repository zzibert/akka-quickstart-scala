package leetcode

import scala.collection.mutable

object BasicCalculator {

  def calculateHelper(trimmed: Array[Char]): Int = {
    val parentheses = mutable.Stack[Int]()
    var result = 0
    var plusOrMinus: Boolean = true

    trimmed.zipWithIndex foreach {
      case (char, index) =>
        char match {
          case '+' =>
            if (parentheses.isEmpty) {
              plusOrMinus = true
            }

          case '-' =>
            if (parentheses.isEmpty) {
              plusOrMinus = false
            }

          case '(' =>
            parentheses.push(index)

          case ')' =>
            val startIndex = parentheses.pop()
            if (parentheses.isEmpty) {
              val localResult =
                if (plusOrMinus) {
                  calculateHelper(trimmed.slice(startIndex + 1, index))
                } else {
                  -calculateHelper(trimmed.slice(startIndex + 1, index))
                }
              result += localResult
            }

          case numberChar =>
            if (parentheses.isEmpty) {
              val number = getInt(numberChar)
              if (plusOrMinus) {
                result += number
              } else {
                result -= number
              }
            }
        }
    }
    println(s"string: ${trimmed.mkString}, result: $result")
    result
  }

  def calculate(s: String): Int = {
    val trimmed = s.trim.filterNot(_.isWhitespace)
    calculateHelper(trimmed.toCharArray)
  }

  def getInt(char: Char): Int = {
    char.toInt - 48
  }
}
