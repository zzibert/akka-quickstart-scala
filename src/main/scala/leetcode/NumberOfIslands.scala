object Solution {
  def numIslands(grid: Array[Array[Char]]): Int = {
    var result = 0
    val length = grid.length
    val width = grid(0).length

    for {
      x <- 0 until length
      y <- 0 until width
    } {
      if (grid(x)(y) == '1') {
        result += 1
        sunk(x, y, grid)
      }
    }

    result
  }

  def isWithinBounds(x: Int, y: Int, length: Int, width: Int): Boolean =
    x >= 0 && x < length && y >= 0 && y < width

  def sunk(x: Int, y: Int, grid: Array[Array[Char]]): Unit = {
    val length = grid.length
    val width = grid(0).length
    val directions = List((-1, 0), (0, 1), (1, 0), (0, -1))

    grid(x)(y) = '0'

    for {
      direction <- directions
    } {
      val newX = x + direction._1
      val newY = y + direction._2
      if (isWithinBounds(newX, newY, length, width) && grid(newX)(newY) == '1') {
        sunk(newX, newY, grid)
      }
    }
  }
}
