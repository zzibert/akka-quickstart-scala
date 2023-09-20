package leetcode

import scala.collection.mutable

object ValidSudoku {
  def isValidSudoku(board: Array[Array[Char]]): Boolean = {

    // check row
    for (i <- 0 until 9) {
      val row = mutable.Set[Char]()
      for (j <- 0 until 9) {
        if (board(i)(j) != '.') {
          if (row.contains(board(i)(j))) {
            return false
          } else {
            row.addOne(board(i)(j))
          }
        }
      }
    }

    // check column
    for (j <- 0 until 9) {
      val column = mutable.Set[Char]()
      for (i <- 0 until 9) {
        if (board(i)(j) != '.') {
          if (column.contains(board(i)(j))) {
            return false
          } else {
            column.addOne(board(i)(j))
          }
        }
      }
    }

    // check subgrid
    for (i <- 0 until 9 by 3) {
      for (j <- 0 until 9 by 3) {
        val subgrid = mutable.Set[Char]()
        for (k <- 0 until 3) {
          for (l <- 0 until 3) {
            if (board(i + k)(j + l) != '.'){
              if (subgrid.contains(board(i + k)(j + l))) {
                return false
              } else {
                subgrid.add(board(i + k)(j + l))
              }
            }
          }
        }
      }
    }

    true
  }
}
