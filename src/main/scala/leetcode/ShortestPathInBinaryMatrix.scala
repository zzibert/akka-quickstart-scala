package leetcode

import scala.collection.mutable

object ShortestPathInBinaryMatrix {
  def shortestPathBinaryMatrix(grid: Array[Array[Int]]): Int = {
    val queue = mutable.Queue[(Int, Int, Int)]()
    val seen = mutable.Map[(Int, Int), Boolean]()

    val destination = grid.length-1

    if (grid(0)(0) == 0) {
      queue.enqueue((0, 0, 1))
      seen += (0,0) -> true
    }

    while (queue.nonEmpty) {
      val node = queue.dequeue()
      if (node._1 == destination && node._2 == destination) {
        return node._3
      } else {
        insertNeighbors(node._1, node._2, node._3+1, grid, queue, seen)
      }
    }

    -1
  }

  def insertNeighbors(x: Int, y: Int, level: Int, grid: Array[Array[Int]], queue: mutable.Queue[(Int, Int, Int)], seen: mutable.Map[(Int, Int), Boolean]): Unit = {
    // upper
    insertIntoQueue(x-1, y, level, queue, grid, seen)

    // upper-right
    insertIntoQueue(x-1, y+1, level, queue, grid, seen)

    // right
    insertIntoQueue(x, y+1, level, queue, grid, seen)

    // down-right
    insertIntoQueue(x+1, y+1, level, queue, grid, seen)

    // down
    insertIntoQueue(x+1, y, level, queue, grid, seen)

    // down-left
    insertIntoQueue(x+1, y-1, level, queue, grid, seen)

    // left
    insertIntoQueue(x, y-1, level, queue, grid, seen)

    // upper-left
    insertIntoQueue(x-1, y-1, level, queue, grid, seen)

  }

  def insertIntoQueue(x: Int, y: Int, level: Int, queue: mutable.Queue[(Int, Int, Int)], grid: Array[Array[Int]], seen: mutable.Map[(Int, Int), Boolean]): Unit = {
    if (isValid(x, y, grid.length) && grid(x)(y) == 0 && !seen.getOrElse((x, y), false)) {
      queue.enqueue((x, y, level))
      seen += (x, y) -> true
    }
  }

  def isValid(x: Int, y: Int, length: Int): Boolean = {
    (length > x && x >= 0 && length > y && y >= 0)
  }
}
