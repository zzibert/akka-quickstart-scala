package leetcode

import scala.collection.mutable

object DecodeString {
  def decodeString(s: String): String = {
    var result = ""
    val numbers = mutable.Stack[Int]()
    val startBrackets = mutable.Stack[Int]()

    s.zipWithIndex foreach {
      case (char, index) =>
        char match {
          case number if number.isDigit =>
            numbers.push(toInt(char))

          case startBracket if startBracket == '[' =>
            startBrackets.push(index)

          case endBracket if endBracket == ']' =>
            val multiplier = numbers.pop()
            val startBracket = startBrackets.pop()
            val str = s.substring(startBracket+1, index)
            result += getString(multiplier, str)

          case char =>
            if (startBrackets.isEmpty) {
              result += char
            }
        }
    }

    result
  }

  def getString(multiplier: Int, str: String): String = {
    var result = ""

    for (_ <- 0 until multiplier) {
      result += str
    }

    result
  }

  def toInt(char: Char): Int = {
    char.toInt - 48
  }
}
