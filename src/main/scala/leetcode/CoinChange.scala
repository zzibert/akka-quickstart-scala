package leetcode

import scala.collection.mutable

object Solution {
  def coinChange(coins: Array[Int], amount: Int): Int = {

    val result = mutable.ListBuffer[Int]()

    coinChangeHelper(coins, amount, 0, result)

    if (result.isEmpty) {
      -1
    } else {
      result.min
    }
  }

  def coinChangeHelper(coins: Array[Int],
                       remaining: Int,
                       numberOfCoins: Int,
                       result: mutable.ListBuffer[Int]): Unit = {
    if (remaining == 0) {
      result.addOne(numberOfCoins)
    } else if (remaining > 0) {
      for (i <- 0 until coins.length) {
        val coin = coins(i)
        coinChangeHelper(coins, remaining - coin, numberOfCoins + 1, result)
      }
    }
  }
}
