package leetcode

import scala.collection.mutable

object EvaluateReversePolishNotation {
  def evalRPN(tokens: Array[String]): Int = {
    val symbols = List("+", "-", "*", "/")
    val stack = mutable.Stack[Int]()

    tokens foreach { token =>
      if (symbols.contains(token)) {
        val operand2 = stack.pop()
        val operand1 = stack.pop()
        var result = 0
        token match {
          case "+" =>
            result = operand1 + operand2
          case "-" =>
            result = operand1 - operand2
          case "*" =>
            result = operand1 * operand2
          case "/" =>
            result = operand1 / operand2
        }
        stack.push(result)
      } else {
        stack.push(token.toInt)
      }
    }

    stack.pop()
  }
}
