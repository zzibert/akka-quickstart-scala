package leetcode

import scala.collection.mutable

object Solution {
  def pathSum(nums: Array[Int]): Int = {
    val result = mutable.ArrayBuffer[Int](0)
    val nodes = mutable.Map[(Int, Int), Int]()

    val intArray =
      nums
        .map(_.toString)
        .map(_.toCharArray)
        .map(array => array.map(_.toInt - 48))

    intArray foreach { array =>
      nodes += ((array(0), array(1)) -> array(2))
    }

    traverseAndCount(1, 1, 0, nodes, result)

    println(nodes)

    result(0)
  }

  def traverseAndCount(depth: Int, position: Int, sum: Int, nodes: mutable.Map[(Int, Int), Int], result: mutable.ArrayBuffer[Int]): Unit = {
    nodes.get((depth, position)) match {
      case Some(value) =>
        val newSum = sum + value
        val children = List(getLeftChildPosition(depth, position), getRightChildPosition(depth, position))
        List(getLeftChildPosition(depth, position), getRightChildPosition(depth, position)).flatMap(child => nodes.get(child)) match {
          case Nil =>
            result(0) += newSum

          case _ =>
            children.foreach(child => traverseAndCount(child._1, child._2, newSum, nodes, result))
        }
      case None =>
    }
  }

  def getLeftChildPosition(depth: Int, position: Int): (Int, Int) = {
    val newPosition =
      if (position == 1) {
        1
      } else {
        (position * 2) - 1
      }

    (depth + 1, newPosition)
  }

  def getRightChildPosition(depth: Int, position: Int): (Int, Int) = {
    (depth + 1, position * 2)
  }
}
