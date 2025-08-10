object Solution {
  def coinChange(coins: Array[Int], amount: Int): Int = {
    val numberOfCoins = Array.fill(amount + 1)(-1)
    numberOfCoins(0) = 0

    if (amount == 0) {
      0
    } else {
      for {
        i <- 0 until amount
        if numberOfCoins(i) != -1
        coin <- coins
      } {
        val currentCoins = numberOfCoins(i)
        val newAmount = i + coin
        if (newAmount > 0 && newAmount <= amount) {
          val nextCoins = currentCoins + 1
          if (numberOfCoins(newAmount) == -1 || numberOfCoins(newAmount) > nextCoins) {
            numberOfCoins(newAmount) = nextCoins
          }
        }
      }

      numberOfCoins(amount)
    }
  }
}