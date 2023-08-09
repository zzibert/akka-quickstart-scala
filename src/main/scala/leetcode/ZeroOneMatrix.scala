package leetcode

import scala.collection.mutable

object ZeroOneMatrix {
  def updateMatrix(mat: Array[Array[Int]]): Array[Array[Int]] = {
    val queue = mutable.Queue[(Int, Int, Int)]()
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
        addToQueue(i-1, j, m, n, mat, queue, 1, visited)
        addToQueue(i, j+1, m, n, mat, queue, 1, visited)
        addToQueue(i+1, j, m, n, mat, queue, 1, visited)
        addToQueue(i, j-1, m, n, mat, queue, 1, visited)
      }
    }

    while (queue.nonEmpty) {
      val (x, y, value) = queue.dequeue()
      mat(x)(y) = value
      addToQueue(x - 1, y, m, n, mat, queue, value+1, visited)
      addToQueue(x, y + 1, m, n, mat, queue, value+1, visited)
      addToQueue(x + 1, y, m, n, mat, queue, value+1, visited)
      addToQueue(x, y - 1, m, n, mat, queue, value+1, visited)
    }

    mat
  }

  def addToQueue(x: Int, y: Int, m: Int, n: Int, mat: Array[Array[Int]], queue: mutable.Queue[(Int, Int, Int)], value: Int, visited: mutable.Map[(Int, Int), Boolean]): Unit = {
    if (!visited.getOrElse((x, y), false) && m > x && x >= 0 && n > y && y >= 0) {
      visited += ((x, y) -> true)
      if (mat(x)(y) == 1) {
        queue.enqueue((x, y, value))
      }
    }
  }
}
