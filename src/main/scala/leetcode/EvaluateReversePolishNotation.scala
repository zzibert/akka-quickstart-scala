import scala.collection.mutable

object Solution {
  def evalRPN(tokens: Array[String]): Int = {
    val stack = mutable.Stack[Int]()

    tokens foreach { token =>
      token match {
        case "+" =>
          val number1 = stack.pop()
          val number2 = stack.pop()
          val result = number1 + number2
          stack.push(result)

        case "-" =>
          val number2 = stack.pop()
          val number1 = stack.pop()
          val result = number1 - number2
          stack.push(result)

        case "*" =>
          val number1 = stack.pop()
          val number2 = stack.pop()
          val result = number1 * number2
          stack.push(result)

        case "/" =>
          val number2 = stack.pop()
          val number1 = stack.pop()
          val result = number1 / number2
          stack.push(result)

        case numberString =>
          val number = numberString.toInt
          stack.push(number)
      }
    }

    stack.pop()
  }
}
