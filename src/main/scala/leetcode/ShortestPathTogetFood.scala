package leetcode

import scala.collection.mutable

object ShortestPathTogetFood {
  def getFood(grid: Array[Array[Char]]): Int = {
    val queue = mutable.Queue[(Int, Int, Int)]()
    val seen = mutable.Map[(Int, Int), Boolean]()
    val x = grid.length
    val y = grid(0).length

    queue.enqueue(findStart(grid, x, y))

    while (queue.nonEmpty) {
      val block = queue.dequeue()
      if (grid(block._1)(block._2) == '#') {
        return block._3
      }
      addBlocks(block, seen, queue, grid)
      seen += (block._1, block._2) -> true
    }

    -1
  }

  def findStart(grid: Array[Array[Char]], x: Int, y: Int): (Int, Int, Int) = {
    for {
      i <- 0 until x
      j <- 0 until y
    } {
      if (grid(i)(j) == '*') {
        return (i, j, 0)
      }
    }
    (0, 0, 0)
  }

  def addBlocks(block: (Int, Int, Int), seen: mutable.Map[(Int, Int), Boolean], queue: mutable.Queue[(Int, Int, Int)], grid: Array[Array[Char]]): Unit = {
      val directions = List((block._1-1, block._2), (block._1, block._2+1), (block._1+1, block._2), (block._1, block._2-1))

      directions foreach { direction =>
        val x = direction._1
        val y = direction._2
        if (!seen.getOrElse((direction._1, direction._2), false) && isFreeSpace(grid, x, y)) {
          queue.enqueue((x, y, block._3+1))
          seen += (direction._1, direction._2) -> true
        }
      }
  }

  def isFreeSpace(grid: Array[Array[Char]], x: Int, y: Int): Boolean = {
    grid.length > x && x >= 0 && grid(0).length > y && y >= 0 && grid(x)(y) != 'X'
  }
}
