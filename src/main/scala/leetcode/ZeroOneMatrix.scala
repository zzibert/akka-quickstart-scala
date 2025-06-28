import scala.collection.mutable

object Solution {
  def updateMatrix(mat: Array[Array[Int]]): Array[Array[Int]] = {
    val queue = mutable.Queue[(Int, Int, Int)]()
    val seen = mutable.Map[(Int, Int), Boolean]()
    val length = mat.length
    val width = mat(0).length

    for {
      x <- 0 until mat.length
      y <- 0 until mat(0).length
    } {
      if (mat(x)(y) == 0) {
        queue.enqueue((0, x, y))
      }
    }

    while (queue.nonEmpty) {
      val (degree, x, y) = queue.dequeue()
      if (isWithinBounds(x, y, mat.length, mat(0).length) && seen
            .get((x, y))
            .isEmpty) {
        mat(x)(y) = degree
        seen.update((x, y), true)
        // UP
        enqueue(degree + 1, x - 1, y, length, width, queue, seen)

        // RIGHT
        enqueue(degree + 1, x, y + 1, length, width, queue, seen)

        // DOWN
        enqueue(degree + 1, x + 1, y, length, width, queue, seen)

        // LEFT
        enqueue(degree + 1, x, y - 1, length, width, queue, seen)
      }
    }

    mat
  }

  def isWithinBounds(x: Int, y: Int, m: Int, n: Int): Boolean =
    x >= 0 && x < m && y >= 0 && y < n

  def enqueue(degree: Int,
              x: Int,
              y: Int,
              length: Int,
              width: Int,
              queue: mutable.Queue[(Int, Int, Int)],
              seen: mutable.Map[(Int, Int), Boolean]): Unit = {
    if (isWithinBounds(x, y, length, width) && seen.get((x, y)).isEmpty) {
      queue.enqueue((degree, x, y))
    }
  }

}
