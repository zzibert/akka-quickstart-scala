package leetcode

import scala.collection.mutable

object ClimbingStairs {
  def climbStairs(n: Int): Int = {
    val distinctWays = mutable.Map[Int, Int]()
    distinctWays += (0 -> 0)
    distinctWays += (1 -> 1)

    for (i <- 2 to n) {
      distinctWays += (i -> (distinctWays(i-1) + distinctWays(i-2)))
    }

    distinctWays(n-1) + distinctWays(n-2)

  }
}
