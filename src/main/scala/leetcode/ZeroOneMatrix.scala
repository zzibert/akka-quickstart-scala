package leetcode

import scala.collection.mutable

object Solution {
  def updateMatrix(mat: Array[Array[Int]]): Array[Array[Int]] = {
    val distanceGrid = Array.fill(mat.length, mat(0).length)(-1)
    val queue = mutable.Queue[(Int, Int, Int)]()

    val xDimension = mat.length
    val yDimension = mat(0).length

    for {
      x <- 0 until xDimension
      y <- 0 until yDimension
      if mat(x)(y) == 0
    } {
      queue.enqueue((x, y, 0))
      distanceGrid(x)(y) = 0
    }

    while (queue.nonEmpty) {
      val (x, y, distance) = queue.dequeue()
      // left
      if (shouldInsertBlock(xDimension, yDimension, distanceGrid, x, y-1)) {
        insertBlock(distanceGrid, queue, x, y-1, distance+1)
      }
      // up
      if (shouldInsertBlock(xDimension, yDimension, distanceGrid, x-1, y)) {
        insertBlock(distanceGrid, queue, x-1, y, distance+1)
      }
      // right
      if (shouldInsertBlock(xDimension, yDimension, distanceGrid, x, y+1)) {
        insertBlock(distanceGrid, queue, x, y+1, distance+1)
      }
      //down
      if (shouldInsertBlock(xDimension, yDimension, distanceGrid, x+1, y)) {
        insertBlock(distanceGrid, queue, x+1, y, distance+1)
      }
    }


    distanceGrid
  }

  def insertBlock(distanceGrid: Array[Array[Int]], queue: mutable.Queue[(Int, Int, Int)], x: Int, y: Int, distance: Int): Unit = {
    distanceGrid(x)(y) = distance
    queue.enqueue((x, y, distance))
  }

  def shouldInsertBlock(xDimension: Int, yDimension: Int, distanceGrid: Array[Array[Int]], x: Int, y: Int): Boolean = {
    isWithinBounds(xDimension, yDimension, x, y) && distanceGrid(x)(y) == -1
  }

  def isWithinBounds(xDimension: Int, yDimension: Int, x: Int, y: Int): Boolean = {
    x >= 0 && x < xDimension && y >= 0 && y < yDimension
  }
}
