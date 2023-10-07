package leetcode

object MinimumPathSum {
  def minPathSum(grid: Array[Array[Int]]): Int = {
    val length = grid.length
    val width = grid(0).length

    for (i <- 0 until length) {
      for (j <- 0 until width) {
        grid(i)(j) = calculateSum(i, j, grid)
      }
    }

    grid(length-1)(width-1)
  }

  def calculateSum(x: Int, y: Int, grid: Array[Array[Int]]): Int = {
    val length = grid.length
    val width = grid(0).length

    val up =
      Option.when(isInbound(x-1, y, length, width)) {
        grid(x-1)(y)
      }


    val left =
      Option.when(isInbound(x, y-1, length, width)) {
        grid(x)(y-1)
      }

    List(up, left).flatten match {
      case Nil => grid(x)(y)
      case list => list.min + grid(x)(y)
    }
  }

  def isInbound(x: Int, y: Int, length: Int, width: Int): Boolean = {
    x >= 0 && x < length && y >= 0 && y < width
  }
}
