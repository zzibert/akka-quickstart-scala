package leetcode

import scala.collection.mutable

object DeleteAndEarn {
  def deleteAndEarn(nums: Array[Int]): Int = {
    val totalPoints = mutable.Map[Int, Int]()
    val cumulativePoints = mutable.Map[Int, Int]()

    nums foreach { number =>
      totalPoints += (number -> (totalPoints.getOrElse(number, 0)+number))
    }

    val numbers = nums.distinct.sorted

    for (number <- numbers.head to numbers.last) {
      val maxPoints = Math.max(cumulativePoints.getOrElse(number-2, 0), cumulativePoints.getOrElse(number-3, 0))
      cumulativePoints += (number -> (totalPoints.getOrElse(number, 0)+maxPoints))
    }

    Math.max(cumulativePoints.getOrElse(numbers.last, 0), cumulativePoints.getOrElse(numbers.last - 1, 0))
  }
}
