import scala.collection.mutable

object Solution {
  def uniquePaths(m: Int, n: Int): Int = {
    val paths = mutable.Map[(Int, Int), Int]()
    paths.addOne((-1, 0), 1)

    for {
      i <- 0 until m
      j <- 0 until n
    } {
      val down = paths.get((i - 1, j)).getOrElse(0)
      val right = paths.get((i, j - 1)).getOrElse(0)
      paths.addOne((i, j), down + right)
    }

    paths.get((m - 1, n - 1)).get
  }
}
