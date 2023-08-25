package leetcode

import scala.collection.mutable

object UniquePaths {
  def uniquePaths(m: Int, n: Int): Int = {
    val paths = mutable.Map[(Int, Int), Int]()

    paths += (-1, 0) -> 1

    for {
      x <- 0 until m
      y <- 0 until n
    } {
      paths += (x, y) -> (paths.getOrElse((x-1,y), 0) + paths.getOrElse((x, y-1), 0))
    }

    paths.getOrElse((m-1, n-1), 0)
  }
}
