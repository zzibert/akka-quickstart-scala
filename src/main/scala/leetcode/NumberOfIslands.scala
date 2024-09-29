package leetcode

import scala.collection.mutable

object Solution {
  def numIslands(grid: Array[Array[Char]]): Int = {
    var numberOfIslands = 0

    val xDimension = grid.length
    val yDimension = grid(0).length

    for {
      i <- 0 until xDimension
      j <- 0 until yDimension
    } {
      if (grid(i)(j) == '1') {
        numberOfIslands += 1
        sinkIsland(i, j, grid)
      }
    }

    numberOfIslands
  }

  def sinkIsland(x: Int, y: Int, grid: Array[Array[Char]]): Unit = {
    val queue = mutable.Queue[(Int, Int)]()

    val xDimension = grid.length
    val yDimension = grid(0).length

    queue.enqueue((x, y))

    while (queue.nonEmpty) {
      val block = queue.dequeue()
      if (grid(block._1)(block._2) == '1') {
        grid(block._1)(block._2) = 0
        // left
        val leftX = block._1
        val leftY = block._2 - 1
        if (isWithinBounds(xDimension, yDimension, leftX, leftY)) {
          queue.enqueue((leftX, leftY))
        }
        // up
        val upX = block._1 - 1
        val upY = block._2
        if (isWithinBounds(xDimension, yDimension, upX, upY)) {
          queue.enqueue((upX, upY))
        }
        // right
        val rightX = block._1
        val rightY = block._2 + 1
        if (isWithinBounds(xDimension, yDimension, rightX, rightY)) {
          queue.enqueue((rightX, rightY))
        }
        // down
        val downX = block._1 + 1
        val downY = block._2
        if (isWithinBounds(xDimension, yDimension, downX, downY)) {
          queue.enqueue((downX, downY))
        }
      }
    }

  }

  def isWithinBounds(xDimension: Int,
                     yDimension: Int,
                     x: Int,
                     y: Int): Boolean = {
    x >= 0 && x < xDimension && y >= 0 && y < yDimension
  }
}
