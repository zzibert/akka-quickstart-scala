package leetcode

import scala.collection.mutable

class WordSearch {
  def exist(board: Array[Array[Char]], word: String): Boolean = {
    val m = board.length
    val n = board(0).length
    val candidates = mutable.ListBuffer[(Int, Int)]()
    val used = Set[(Int, Int)]()
    val result = mutable.ArrayBuffer[Int]()


      for {
        i <- 0 until m
        j <- 0 until n
      } yield {
        if (board(i)(j) == word.head) {
          candidates.addOne((i, j))
        }
      }

    candidates foreach {
      case (x, y) =>
        checkCandidate(x, y, m, n, board, used, word, result)
        if (result.nonEmpty) {
          return true
        }
    }

    result.nonEmpty
  }

  def checkCandidate(x: Int, y: Int, m: Int, n: Int, board: Array[Array[Char]], used: Set[(Int, Int)], word: String, result: mutable.ArrayBuffer[Int]): Unit = {
    if (word.isEmpty) {
      result.addOne(1)
    } else if (isInbound(x, y, m, n) && !used.contains((x, y)) && board(x)(y) == word.head) {
      checkCandidate(x, y+1, m, n, board, used + ((x, y)), word.tail, result) // right
      checkCandidate(x+1, y, m, n, board, used + ((x, y)), word.tail, result) // down
      checkCandidate(x, y-1, m, n, board, used + ((x, y)), word.tail, result) // left
      checkCandidate(x-1, y, m, n, board, used + ((x, y)), word.tail, result) // up
    }
  }

  def isInbound(x: Int, y: Int, m: Int, n: Int) = m > x && x >= 0 && n > y && y >= 0

}
