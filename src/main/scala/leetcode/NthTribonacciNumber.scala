package leetcode

object NthTribonacciNumber {
  def tribonacci(n: Int): Int = {
    if (n == 0) {
      0
    } else if (n < 3) {
      1
    } else {
      val numbers = Array.fill(n+1)(0)

      numbers(1) = 1
      numbers(2) = 1

      for (i <- 3 to n) {
        numbers(i) = numbers(i - 1) + numbers(i - 2) + numbers(i - 3)
      }

      numbers(n)
    }
  }
}
