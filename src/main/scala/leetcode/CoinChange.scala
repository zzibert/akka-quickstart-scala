package leetcode

import scala.collection.mutable
import scala.util.control.Breaks._

object CoinChange {
  def coinChange(coins: Array[Int], amount: Int): Int = {
    if (amount == 0) {
      return 0
    }

    val sortedCoins = coins.sorted.reverse
    val combinations = mutable.Map[Int, Int]()

    sortedCoins foreach { coin =>
      combinations += (coin -> 1)
    }

    for {
      i <- 1 to amount
      if combinations.get(i).isEmpty
      coin <- sortedCoins
      result <- combinations.get(i - coin)
    } {
      combinations.get(i) match {
        case Some(current) =>
          if (result + 1 < current) {
            combinations += (i -> (result + 1))
          }
        case None =>
          combinations += (i -> (result + 1))
      }
    }

    combinations.get(amount) match {
      case Some(result) => result
      case _ => -1
    }
  }
}
