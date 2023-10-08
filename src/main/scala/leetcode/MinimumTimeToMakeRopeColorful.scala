package leetcode

import scala.collection.mutable.ArrayBuffer

object MinimumTimeToMakeRopeColorful {

  case class Balloon(color: Char, index: Int)
  def minCost(colors: String, neededTime: Array[Int]): Int = {
    var result = 0
    val buffer = ArrayBuffer[Balloon]()

    colors.zipWithIndex foreach {
      case (color, index) =>
        buffer.addOne(Balloon(color, index))
    }

    var i = 0

    while (i < (buffer.length-1)) {
      if (sameColor(buffer(i), buffer(i+1))) {
        val index = whichToRemove(i, i+1, buffer, neededTime)
        result += neededTime(buffer(index).index)
        buffer.remove(index)
      } else {
        i += 1
      }
    }

    result
  }

  def sameColor(first: Balloon, second: Balloon): Boolean = {
    first.color == second.color
  }

  def whichToRemove(first: Int, second: Int, buffer: ArrayBuffer[Balloon], neededTime: Array[Int]): Int = {
    val firstTime = neededTime(buffer(first).index)
    val secondTime = neededTime(buffer(second).index)

    if (firstTime < secondTime) {
      first
    } else {
      second
    }
  }


}
