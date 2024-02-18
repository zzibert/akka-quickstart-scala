package leetcode

import scala.collection.mutable

object BasicCalculator {
  def calculate(s: String): Int = {
    var result = 0
    var numberStr = ""
    val parentheses = mutable.Stack[Int]()
    var digitInProgress = false
    var isPlus = true

    s.zipWithIndex foreach {
      case (char, index) =>
        char match {
          case '+' =>
            if (digitInProgress) {
              val number = numberStr.toInt
              if (isPlus) {
                result += number
              } else {
                result -= number
              }
              digitInProgress = false
              isPlus = true
              numberStr = ""
            }
            isPlus = true
          case '-' =>
            if (digitInProgress) {
              val number = numberStr.toInt
              if (isPlus) {
                result += number
              } else {
                result -= number
              }
              digitInProgress = false
              isPlus = true
              numberStr = ""
            }
            if (isPlus) {
              isPlus = false
            } else {
              isPlus = true
            }
          case '(' =>
            parentheses.push(index)
          case ')' =>
            val startIndex = parentheses.pop() + 1
            val value = calculate(s.substring(startIndex, index))
            return calculate(s.take(startIndex-1) + value.toString + s.drop(index + 1))
          case ' ' =>
          case digit =>
            digitInProgress = true
            numberStr += digit
        }
    }

    if (numberStr.nonEmpty) {
      if (isPlus) {
        result += numberStr.toInt
      } else {
        result -= numberStr.toInt
      }
    }

    println(s"this is the string: ${s}, this is the result: ${result}")
    result
  }
}
