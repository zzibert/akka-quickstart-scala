package leetcode

import scala.collection.mutable


object FloodFill {
  def floodFill(image: Array[Array[Int]], sr: Int, sc: Int, color: Int): Array[Array[Int]] = {
    val target = image(sr)(sc)
    val alreadyVisited = mutable.Map[(Int, Int), Boolean]()

    floodFillHelper(image, sr, sc, color, target, alreadyVisited)

    image
  }

  def floodFillHelper(image: Array[Array[Int]], sr: Int, sc: Int, color: Int, target: Int, visited: mutable.Map[(Int, Int), Boolean]): Unit = {
    if (!visited.getOrElse((sr, sc), false) && image.length > sr && sr >= 0 && image(0).length > sc && sc >= 0) {
      if (image(sr)(sc) == target) {
        image(sr)(sc) = color
        visited += ((sr, sc) -> true)
        floodFillHelper(image, sr-1, sc, color, target, visited)
        floodFillHelper(image, sr, sc+1, color, target, visited)
        floodFillHelper(image, sr+1, sc, color, target, visited)
        floodFillHelper(image, sr, sc-1, color, target, visited)
      }
    }
  }
}
