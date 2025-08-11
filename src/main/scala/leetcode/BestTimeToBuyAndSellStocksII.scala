object Solution {
  def maxProfit(prices: Array[Int]): Int = {
    var result = 0
    val length = prices.length
    if (prices.length < 2) {
      0
    } else {
      for {
        i <- 0 until length-1
        j = i + 1
      } {
        if (prices(i) < prices(j)) {
          result += prices(j) - prices(i)
        }
      }
    }

    result
  }
}
