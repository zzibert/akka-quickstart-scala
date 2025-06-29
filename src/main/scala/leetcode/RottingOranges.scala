import scala.collection.mutable

object Solution {
  def orangesRotting(grid: Array[Array[Int]]): Int = {
    var epoch = -1
    var numberOfFreshOranges = 0
    val rotten = mutable.Queue[(Int, Int)]()
    val length = grid.length
    val width = grid(0).length

    for {
      x <- 0 until length
      y <- 0 until width
    } {
      if (grid(x)(y) == 2) {
        rotten.enqueue((x, y))
      } else if (grid(x)(y) == 1) {
        numberOfFreshOranges += 1
      }
    }

    if (numberOfFreshOranges == 0) {
      return 0
    }

    while (rotten.nonEmpty) {
      val cycles = rotten.size
      for (_ <- 0 until cycles) {
        val (x, y) = rotten.dequeue()

        // up
        if (isFreshAndWithinBounds(x - 1, y, length, width, grid)) {
          numberOfFreshOranges -= 1
          grid(x - 1)(y) = 2
          rotten.enqueue((x - 1, y))
        }

        // right
        if (isFreshAndWithinBounds(x, y + 1, length, width, grid)) {
          numberOfFreshOranges -= 1
          grid(x)(y + 1) = 2
          rotten.enqueue((x, y + 1))
        }

        // down
        if (isFreshAndWithinBounds(x + 1, y, length, width, grid)) {
          numberOfFreshOranges -= 1
          grid(x + 1)(y) = 2
          rotten.enqueue((x + 1, y))
        }

        // left
        if (isFreshAndWithinBounds(x, y - 1, length, width, grid)) {
          numberOfFreshOranges -= 1
          grid(x)(y - 1) = 2
          rotten.enqueue((x, y - 1))
        }
      }
      epoch += 1
    }

    if (numberOfFreshOranges == 0) {
      epoch
    } else {
      -1
    }
  }

  def isFreshAndWithinBounds(
      x: Int,
      y: Int,
      length: Int,
      width: Int,
      grid: Array[Array[Int]],
  ): Boolean = {
    if (x >= 0 && x < length && y >= 0 && y < width) {
      grid(x)(y) == 1
    } else {
      false
    }
  }
}
