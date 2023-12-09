package leetcode

import scala.collection.mutable

object BasicCalculator {
  def calculateHelper(trimmed: Array[Char]): Int = {
    val parentheses = mutable.Stack[Int]()
    var result = 0
    var plusOrMinus: Boolean = true
    val numberBuffer = mutable.ArrayBuffer[Char]()

    trimmed.zipWithIndex foreach {
      case (char, index) =>
        char match {
          case '+' =>
            if (numberBuffer.nonEmpty) {
              val number = toNumber(numberBuffer)
              if (plusOrMinus) {
                result += number
              } else {
                result -= number
              }
            }
            numberBuffer.clear()
            if (parentheses.isEmpty) {
              plusOrMinus = true
            }

          case '-' =>
            if (numberBuffer.nonEmpty) {
              val number = toNumber(numberBuffer)
              if (plusOrMinus) {
                result += number
              } else {
                result -= number
              }
            }
            numberBuffer.clear()
            if (parentheses.isEmpty) {
              plusOrMinus = false
            }

          case '(' =>
            if (numberBuffer.nonEmpty) {
              val number = toNumber(numberBuffer)
              if (plusOrMinus) {
                result += number
              } else {
                result -= number
              }
            }
            numberBuffer.clear()
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
              numberBuffer.addOne(numberChar)
//              val number = getInt(numberChar)
//              if (plusOrMinus) {
//                result += number
//              } else {
//                result -= number
//              }
            }
        }
    }

    if (numberBuffer.nonEmpty) {
      val number = toNumber(numberBuffer)
      if (plusOrMinus) {
        result += number
      } else {
        result -= number
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

  def toNumber(buffer: mutable.ArrayBuffer[Char]): Int = {
    buffer.mkString.toInt
  }
}
