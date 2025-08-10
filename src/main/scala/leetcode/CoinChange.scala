import scala.collection.mutable

object Solution {
  def coinChange(coins: Array[Int], amount: Int): Int = {
    val coinsBuffer = mutable.ArrayBuffer.from(coins)
    val bestResultYet = mutable.Map[Int, Int]()

    val result = coinChangeHelper(coinsBuffer, amount, 0, bestResultYet)

    if (result == Int.MaxValue) {
      -1
    } else {
      result
    }
  }

  def coinChangeHelper(coins: mutable.ArrayBuffer[Int], amount: Int, numberOfCoins: Int, bestResultYet: mutable.Map[Int, Int]): Int = {
    if (amount == 0) {
      numberOfCoins
    } else if (amount > 0) {
      var result = Int.MaxValue

      for {
        coin <- coins
      } {
        val newAmount = amount - coin
        val newNumberOfCoins = numberOfCoins + 1
        bestResultYet.get(newAmount) match {
          case Some(bestYet) =>
            if (bestYet > newNumberOfCoins) {
              val currentResult = coinChangeHelper(coins, amount - coin, numberOfCoins + 1, bestResultYet.addOne((newAmount, newNumberOfCoins)))
              if (currentResult < result) {
                result = currentResult
              }
            }

          case None =>
            val currentResult = coinChangeHelper(coins, amount - coin, numberOfCoins + 1, bestResultYet.addOne((newAmount, newNumberOfCoins)))
            if (currentResult < result) {
              result = currentResult
            }
        }
      }

      result
    } else {
      Int.MaxValue
    }
  }
}
