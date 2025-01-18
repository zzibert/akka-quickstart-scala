package leetcode

import scala.collection.mutable

object Solution {
  def spiralOrder(matrix: Array[Array[Int]]): List[Int] = {
    var currentDirection: Direction = Right
    val result = mutable.ListBuffer[Int]()
    var counter = 0
    val x = matrix.length
    val y = matrix(0).length
    val grid: Array[Array[Int]] = Array.ofDim(x, y)

    var block = Block(0, 0)

    while (counter < (x * y)) {
      result.addOne(matrix(block.x)(block.y))
      grid(block.x)(block.y) = 1
      counter += 1
      if (!isFree(nextBlock(currentDirection, block), x, y, grid)) {
        currentDirection = currentDirection.next
      }
      block = nextBlock(currentDirection, block)
    }

    result.toList
  }

  case class Block(x: Int, y: Int)

  def isFree(
      block: Block,
      x: Int,
      y: Int,
      grid: Array[Array[Int]]
  ): Boolean = {
     block.x >= 0 && block.x < x && block.y >= 0 && block.y < y && grid(block.x)(block.y) != 1
  }

  def nextBlock(direction: Direction, block: Block): Block = {
    Block(direction.x + block.x, direction.y + block.y)
  }

  trait Direction {
    def x: Int = 0
    def y: Int = 0
    def next: Direction
  }

  object Up extends Direction {
    override def x: Int = -1

    override def next: Direction = Right
  }

  object Right extends Direction {
    override def y: Int = 1
    override def next: Direction = Down
  }

  object Down extends Direction {
    override def x: Int = 1
    override def next: Direction = Left
  }

  object Left extends Direction {
    override def y: Int = -1
    override def next: Direction = Up
  }

}
