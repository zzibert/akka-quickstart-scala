package leetcode

import scala.collection.mutable

object Solution {
  def findBuildings(heights: Array[Int]): Array[Int] = {
    var threshold = 0
    val result = mutable.ListBuffer[Int]()

    heights.zipWithIndex.reverse foreach {
      case (height, index) =>
        if (height > threshold) {
          result.prepend(index)
          threshold = height
        }
    }

    result.toArray
  }
}
