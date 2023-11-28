package leetcode

object RemoveColoredPiecesIfBothNeighborsAreTheSameColor {
  def winnerOfGame(colors: String): Boolean = {
    var resultA = 0
    var resultB = 0

    for (i <- 1 until (colors.length - 1)) {
      if (colors(i) == 'A') {
        if (colors(i - 1) == 'A' && colors(i + 1) == 'A') {
          resultA += 1
        }
      } else if (colors(i - 1) == 'B' && colors(i + 1) == 'B') {
        resultB += 1
      }
    }

    resultA > resultB
  }
}
