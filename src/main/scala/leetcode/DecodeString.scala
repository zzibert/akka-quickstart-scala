package leetcode

import scala.collection.mutable

object DecodeString {
  def decodeString(s: String): String = {
    var result = ""
    val numbers = mutable.Stack[Int]()
    val startBrackets = mutable.Stack[Int]()
    var numberBuffer = ""

    s.zipWithIndex foreach {
      case (char, index) =>
        char match {
          case number if number.isDigit =>
            numberBuffer += number

          case startBracket if startBracket == '[' =>
            startBrackets.push(index)
            numbers.push(numberBuffer.toInt)
            numberBuffer = ""

          case endBracket if endBracket == ']' =>
            val multiplier = numbers.pop()
            val startBracket = startBrackets.pop()
            if (startBrackets.isEmpty) {
              val str = s.substring(startBracket + 1, index)
              val recursive = decodeString(str)
              result += getString(multiplier, recursive)
            }

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
