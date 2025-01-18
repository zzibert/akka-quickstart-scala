package leetcode

object Solution {
  def uniquePaths(m: Int, n: Int): Int = {
    val grid: Array[Array[Int]] = Array.ofDim(m, n)
    grid(0)(0) = 1

    for {
      i <- 0 until m
      j <- 0 until n
      if !(i == 0 && i == j)
    } {
      val upBlock = getBlockPaths(i - 1, j, grid)
      val leftBlock = getBlockPaths(i, j - 1, grid)
      grid(i)(j) = upBlock + leftBlock
    }

    grid(m-1)(n-1)
  }

  def getBlockPaths(m: Int, n: Int, grid: Array[Array[Int]]): Int = {
    if (m >= 0 && n >= 0) {
      grid(m)(n)
    } else {
      0
    }
  }
}
