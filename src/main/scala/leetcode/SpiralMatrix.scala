import scala.collection.mutable

object Solution {
  def spiralOrder(matrix: Array[Array[Int]]): List[Int] = {
    val directions = Array((0, 1),(1, 0),(0, -1),(-1, 0))
    val seen = mutable.Map[(Int, Int), Boolean]()
    var result = mutable.ArrayBuffer[Int]()
    var counters = 3
    var x = 0
    var y = 0
    var length = matrix.length
    var width = matrix(0).length
    var index = 0

    while (counters > 0) {
      result.addOne(matrix(x)(y))

    }

  }

  def isWithinBounds(x: Int, y: Int, length: Int, width: Int): Boolean = {
    x >= 0 && x < length && y >= 0 && y < width
  }
}