package leetcode

import scala.collection.mutable.ArrayBuffer

object SpiralMatrix {
  def spiralOrder(matrix: Array[Array[Int]]): List[Int] = {
    val m = matrix.length
    val n = matrix(0).length
    val result = ArrayBuffer[Int]()
    var counter = 0
    val elements = m * n
    var x = 0
    var y = 0

    val visited = Array.fill(m, n)(0)

    val directions = Array(goRight, goDown, goLeft, goUp)

    var currentDirection = 0

    while (counter < elements) {
      visited(x)(y) = 1
      counter += 1
      result.addOne(matrix(x)(y))

      val direction = directions(currentDirection)

      val i = x + direction._1
      val j = y + direction._2

      if (isInbound(i, j, m, n) && !isVisited(i, j, visited)) {
        x = i
        y = j
      } else {
        currentDirection = (currentDirection + 1) % 4
        val newDirection = directions(currentDirection)
        x = x + newDirection._1
        y = y + newDirection._2
      }
    }


    result.toList
  }

  val goRight = (0, 1)

  val goDown = (1, 0)

  val goLeft = (0, -1)

  val goUp = (-1, 0)


  def isVisited(x: Int, y: Int, visited: Array[Array[Int]]): Boolean = visited(x)(y) == 1

  def isInbound(x: Int, y: Int, m: Int, n: Int): Boolean = m > x && x >= 0 && n > y && y >= 0
}
