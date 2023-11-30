package leetcode

object DesignTicTacToe {
  class TicTacToe(_n: Int) {
    val board = Array.fill(_n)(Array.fill(_n)(0))

    def move(row: Int, col: Int, player: Int): Int = {
      board(row)(col) = player

      if (checkHorizontaly(row, col, player)) {
        return player
      }

      if (checkVertically(row, col, player)) {
        return player
      }

      if (checkDiagonally(row, col, player)) {
        return player
      }

      0
    }

    def isRightPlayer(row: Int, col: Int, player: Int): Boolean = {
      board(row)(col) == player
    }

    def checkHorizontaly(row: Int, col: Int, player: Int): Boolean = {
      var count = 1
      // go left
      var c = col - 1
      while (c >= 0 && isRightPlayer(row, c, player)) {
        count += 1
        c -= 1
      }

      // go right
      c = col + 1
      while (c < _n && isRightPlayer(row, c, player)) {
        count += 1
        c += 1
      }

      count >= _n
    }

    def checkVertically(row: Int, col: Int, player: Int): Boolean = {
      var count = 1

      // go up
      var r = row - 1
      while (r >= 0 && isRightPlayer(r, col, player)) {
        count += 1
        r -= 1
      }
      // go down
      r = row + 1
      while (r < _n && isRightPlayer(r, col, player)) {
        count += 1
        r += 1
      }

      count >= _n
    }

    def checkDiagonally(row: Int, col: Int, player: Int): Boolean = {
      checkLeftDiagonally(row, col, player) || checkRightDiagonally(row, col, player)
    }

    def checkLeftDiagonally(row: Int, col: Int, player: Int): Boolean = {
      var count = 1

      // go up
      var r = row-1
      var c = col-1
      while (r >= 0 && c >= 0 && isRightPlayer(r, c, player)) {
        count += 1
        r -= 1
        c -= 1
      }

      // go down
      r = row + 1
      c = col + 1
      while (r < _n && c < _n && isRightPlayer(r, c, player)) {
        count += 1
        r += 1
        c += 1
      }

      count >= _n
    }

    def checkRightDiagonally(row: Int, col: Int, player: Int): Boolean = {
      var count = 1

      // go up
      var r = row - 1
      var c = col + 1
      while (r >= 0 && c < _n && isRightPlayer(r, c, player)) {
        count += 1
        r -= 1
        c += 1
      }

      // go down
      r = row + 1
      c = col - 1
      while (r < _n && c >= 0 && isRightPlayer(r, c, player)) {
        count += 1
        r += 1
        c -= 1
      }

      count >= _n
    }
  }
}
