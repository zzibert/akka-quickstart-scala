package leetcode

object Solution {

  def kClosest(points: Array[Array[Int]], k: Int): Array[Array[Int]] = {
    val distances =
      points
        .map(point => (distance(point(0), point(1)), point))
        .sortBy(point => point._1)
        .take(k)

    distances.map(_._2)
  }

  def distance(x: Int, y: Int): Double = {
    val result = Math.pow(x, 2) + Math.pow(y, 2)
    Math.sqrt(result)
  }
}
