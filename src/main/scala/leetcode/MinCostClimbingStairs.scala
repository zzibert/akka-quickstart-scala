package leetcode

import scala.collection.mutable

object MinCostClimbingStairs {
  def minCostClimbingStairs(cost: Array[Int]): Int = {
    val combinedCost = mutable.Map[Int, Int]()
    val length = cost.length

    combinedCost += (0 -> cost(0))
    combinedCost += (1 -> cost(1))

    for (i <- 2 until length) {
      val result = cost(i) + Math.min(combinedCost.getOrElse(i-1, 0), combinedCost.getOrElse(i-2, 0))
      combinedCost += (i -> result)
    }

    Math.min(combinedCost(length-1), combinedCost(length-2))
  }
}
