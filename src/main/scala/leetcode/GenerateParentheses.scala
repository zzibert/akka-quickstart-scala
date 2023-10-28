package leetcode


object GenerateParentheses {
  def generateParenthesis(n: Int): List[String] = {

    if (n < 1) {
      Nil
    } else {
      generateParenthesesHelper(n, List["()"])
    }
  }

  def generateParenthesesHelper(n: Int, parentheses: String): List[String] = {
    if (n > 1) {
      val length = parentheses.length
      val results =
        for {
          i <- 0 until length
        } yield {
          generateParenthesesHelper(n-1, parentheses.take(i) + "()" + parentheses.drop(i))
        }
      results.view.flatten.toList.distinct
    } else {
      List(parentheses)
    }
  }
}
