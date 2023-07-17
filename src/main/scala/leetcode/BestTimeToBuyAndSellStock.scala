package leetcode

import leetcode.twoSum.twoSum

object BestTimeToBuyAndSellStock {

  def maxProfit(prices: Array[Int]): Int = {
    var profit = 0
    var sell = 0
    var buy = 10000

    prices foreach { price =>
      if (price < buy) {
        buy = price
        sell = 0
      }
      if (price > sell) {
        sell = price
      }
      if (sell - buy > profit) {
        profit = sell - buy
      }
    }

    profit
  }

  def main(args: Array[String]): Unit = {
    println(maxProfit(Array(7,6,4,3,1)))
  }

}
