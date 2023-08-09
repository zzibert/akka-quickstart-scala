package leetcode

import scala.collection.mutable

object ZeroOneMatrix {
  def updateMatrix(mat: Array[Array[Int]]): Array[Array[Int]] = {
    val queue = mutable.Queue[(Int, Int)]()
    val visited = mutable.Map[(Int, Int), Boolean]()

    // find neighbors of zeros
    val m = mat.length
    val n = mat(0).length

    for {
      i <- 0 until m
      j <- 0 until n
    } {
      if (mat(i)(j) == 0) {
        visited += ((i, j) -> true)
        addToQueue(i-1, j, m, n, mat, queue, visited)
        addToQueue(i, j+1, m, n, mat, queue, visited)
        addToQueue(i+1, j, m, n, mat, queue, visited)
        addToQueue(i, j-1, m, n, mat, queue, visited)
      }
    }

    while (queue.nonEmpty) {
      val (x, y) = queue.dequeue()
      mat(x)(y) = findMin(x, y, mat, visited)
      visited += ((x, y) -> true)
      addToQueue(x - 1, y, m, n, mat, queue, visited)
      addToQueue(x, y + 1, m, n, mat, queue, visited)
      addToQueue(x + 1, y, m, n, mat, queue, visited)
      addToQueue(x, y - 1, m, n, mat, queue, visited)
    }

    mat
  }

  def findMin(x: Int, y: Int, mat: Array[Array[Int]], visited: mutable.Map[(Int, Int), Boolean]): Int = {
    val minimum =
      List((x-1, y), (x, y+1), (x+1, y), (x, y-1)) flatMap {
        case (x, y) => findHelper(x, y, mat, visited)
      }

    minimum.min + 1
  }

  def findHelper(x: Int, y: Int, mat: Array[Array[Int]], visited: mutable.Map[(Int, Int), Boolean]): Option[Int] = {
    Option.when(visited.getOrElse((x, y), false)) {
      mat(x)(y)
    }
  }

  def addToQueue(x: Int, y: Int, m: Int, n: Int, mat: Array[Array[Int]], queue: mutable.Queue[(Int, Int)], visited: mutable.Map[(Int, Int), Boolean]): Unit = {
    if (!visited.getOrElse((x, y), false) && m > x && x >= 0 && n > y && y >= 0) {
      if (mat(x)(y) == 1) {
        queue.enqueue((x, y))
      }
    }
  }
}
