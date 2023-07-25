package leetcode

import scala.collection.mutable


object FloodFill {
  def floodFill(image: Array[Array[Int]], sr: Int, sc: Int, color: Int): Array[Array[Int]] = {
    val target = image(sr)(sc)
    val queue = mutable.Queue[(Int, Int)]((sr, sc))
    val alreadyVisited = mutable.Map[(Int, Int), Boolean]()

    while(queue.nonEmpty) {
      val (row, column) = queue.dequeue()
      alreadyVisited += ((row, column) -> true)
      if (floodFillHelper(image, row, column, color, target, alreadyVisited)) {
        queue.enqueue((row-1, column))
        queue.enqueue((row, column+1))
        queue.enqueue((row+1, column))
        queue.enqueue((row, column-1))
      }
    }

    image
  }

  def floodFillHelper(image: Array[Array[Int]], sr: Int, sc: Int, color: Int, target: Int, visited: mutable.Map[(Int, Int), Boolean]): Boolean = {
    if (!visited.getOrElse((sr, sc), false) && image.length > sr && sr >= 0 && image(0).length > sc && sc >= 0) {
      if (image(sr)(sc) == target) {
        image(sr)(sc) = color
        visited += ((sr, sc) -> true)
        return true
      }
    }
    false
  }
}
