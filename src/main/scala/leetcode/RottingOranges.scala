package leetcode

import scala.collection.mutable

object RottingOranges {
  def orangesRotting(grid: Array[Array[Int]]): Int = {
    val m = grid.length
    val n = grid(0).length
    var minutes = 0
    var _newRotten = true

    val rottenOranges = mutable.Map[(Int, Int), Boolean]()
    var oranges = List[(Int, Int)]()

    for {
      x <- 0 until m
      y <- 0 until n
    } {
      if (grid(x)(y) == 2) {
        rottenOranges += (x, y) -> true
      } else if (grid(x)(y) == 1) {
        oranges = (x, y) :: oranges
      }
    }

    if (oranges.length == 0) {
      return 0
    }

    if (rottenOranges.keys.size == 0) {
      return -1
    }

    while(_newRotten) {
      _newRotten = false
      minutes += 1
      val (rotten, fresh) = oranges.partition {
        case (x, y) => checkIfRottenAdjecent(x, y, m, n, rottenOranges)
      }
      if (fresh.length == 0) {
        return minutes
      } else if (rotten.length > 0) {
        _newRotten = true
      }
      rotten foreach {
        case (x, y) =>
          rottenOranges += (x, y) -> true
      }
      oranges = fresh
    }

    -1
  }

  def checkIfRottenAdjecent(x: Int, y: Int, m: Int, n: Int, rotten: mutable.Map[(Int, Int), Boolean]): Boolean = {
    if (m > x && x >= 0 && n > y && y >= 0) {
      if (
        rotten.getOrElseUpdate((x-1, y), false) ||
        rotten.getOrElseUpdate((x, y+1), false) ||
        rotten.getOrElseUpdate((x+1, y), false) ||
        rotten.getOrElseUpdate((x, y-1), false)
      ) {
        return true
      }
    }

    false
  }
}
