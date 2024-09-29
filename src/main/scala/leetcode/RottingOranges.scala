package leetcode

import scala.collection.mutable

object Solution {
  def orangesRotting(grid: Array[Array[Int]]): Int = {
    val rotten = mutable.Queue[(Int, Int)]()
    var fresh = 0
    var result = 0

    val xDimension = grid.length
    val yDimension = grid(0).length

    for {
      i <- 0 until xDimension
      j <- 0 until yDimension
    } {
      if (grid(i)(j) == 2) {
        rotten.enqueue((i, j))
      } else if (grid(i)(j) == 1) {
        fresh += 1
      }
    }

    while (rotten.nonEmpty && fresh > 0) {
      val counter = rotten.length

      for (_ <- 0 until counter) {
        val (x, y) = rotten.dequeue()
        // left
        var newX = x
        var newY = y - 1
        if (isWithinBounds(xDimension, yDimension, newX, newY) && grid(newX)(
              newY) == 1) {
          fresh -= 1
          grid(newX)(newY) = 2
          rotten.enqueue((newX, newY))
        }
        // up
        newX = x - 1
        newY = y
        if (isWithinBounds(xDimension, yDimension, newX, newY) && grid(newX)(
              newY) == 1) {
          fresh -= 1
          grid(newX)(newY) = 2
          rotten.enqueue((newX, newY))
        }
        // right
        newX = x
        newY = y + 1
        if (isWithinBounds(xDimension, yDimension, newX, newY) && grid(newX)(
              newY) == 1) {
          fresh -= 1
          grid(newX)(newY) = 2
          rotten.enqueue((newX, newY))
        }
        // down
        newX = x + 1
        newY = y
        if (isWithinBounds(xDimension, yDimension, newX, newY) && grid(newX)(
              newY) == 1) {
          fresh -= 1
          grid(newX)(newY) = 2
          rotten.enqueue((newX, newY))
        }
      }

      result += 1
    }

    if (fresh != 0) {
      -1
    } else {
      result
    }
  }

  def isWithinBounds(xDimension: Int,
                     yDimension: Int,
                     x: Int,
                     y: Int): Boolean = {
    x >= 0 && x < xDimension && y >= 0 && y < yDimension
  }
}
