package leetcode

object NumberOfIslands {
  def numIslands(grid: Array[Array[Char]]): Int = {
    val m = grid.length
    val n = grid(0).length
    var sum = 0

    for {
      x <- 0 until m
      y <- 0 until n
    } {
      if (grid(x)(y) == '1') {
        sum += 1
        markAdjacentZero(x, y, m, n, grid)
      }
    }

    sum
  }

  def markAdjacentZero(x: Int, y: Int, m: Int, n: Int, grid: Array[Array[Char]]): Unit = {
    if (m > x && x >= 0 && n > y && y >= 0) {
      if (grid(x)(y) == '1') {
        grid(x)(y) = 0
        markAdjacentZero(x-1, y, m, n, grid)
        markAdjacentZero(x, y+1, m, n, grid)
        markAdjacentZero(x+1, y, m, n, grid)
        markAdjacentZero(x, y-1, m, n, grid)
      }
    }
  }
}
