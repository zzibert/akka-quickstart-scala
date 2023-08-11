package leetcode

object KClosestPointsToOrigin {
  def kClosest(points: Array[Array[Int]], k: Int): Array[Array[Int]] = {

    implicit val kClosestOrdering: Ordering[Array[Int]] = Ordering.by { array =>
      val sumOfSquares = array.map(Math.pow(_, 2)).sum

      sumOfSquares
    }
    points.sortInPlace()

    points.take(k)
  }
}
