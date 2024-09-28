package leetcode

import scala.collection.mutable

object Solution {
  def evalRPN(tokens: Array[String]): Int = {
    val stack = mutable.Stack[Int]()

    tokens foreach { token =>
      token match {
        case "+" =>
          val (number2, number1) = getNumbers(stack)
          val result = number1 + number2
          stack.push(result)

        case "-" =>
          val (number2, number1) = getNumbers(stack)
          val result = number1 - number2
          stack.push(result)

        case "*" =>
          val (number2, number1) = getNumbers(stack)
          val result = number1 * number2
          stack.push(result)

        case "/" =>
          val (number2, number1) = getNumbers(stack)
          val result = number1 / number2
          stack.push(result)

        case numberString =>
          val number = Integer.parseInt(numberString, 10)
          stack.push(number)
      }
    }

    stack.pop()
  }

  def getNumbers(stack: mutable.Stack[Int]): (Int, Int) = {
    val n1 = stack.pop()
    val n2 = stack.pop()
    (n1, n2)
  }
}
