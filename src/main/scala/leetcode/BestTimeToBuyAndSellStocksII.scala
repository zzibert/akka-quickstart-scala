object Solution {
  def maxProfit(prices: Array[Int]): Int = {
    val length = prices.length
    var profit = 0

    var currentLargest = Int.MinValue
    var currentIndex = 0
    var i = 0

    while (i < length) {
      if (prices(i) > currentLargest) {
        currentLargest = prices(i)
        i += 1
      } else {
        val slice = prices.drop(currentIndex).take(i-currentIndex)
        profit += getProfit(slice)
        currentIndex = i
        currentLargest = Int.MinValue
      }
    }

    val slice = prices.drop(currentIndex).take(i-currentIndex)
    profit += getProfit(slice)

    profit
  }

  def getProfit(prices: Array[Int]): Int = {
    if (prices.isEmpty) {
      0
    } else {
      val last = prices.length-1
      prices(last) - prices(0)
    }
  }
}
