package leetcode

import scala.collection.mutable

object DeleteAndEarn {
  def deleteAndEarn(nums: Array[Int]): Int = {
    val totalPoints = mutable.Map[Int, Int]()
    var result = 0

    nums foreach { number =>
      totalPoints += (number -> (totalPoints.getOrElse(number, 0)+number))
    }

    val numbers = totalPoints.toList.view.sortBy(_._2).map(_._1).toList.reverse

    numbers foreach { number =>
      if (totalPoints.getOrElse(number, 0) >= (totalPoints.getOrElse(number-1, 0) + totalPoints.getOrElse(number+1, 0))) {
        result += totalPoints.getOrElse(number, 0)
        totalPoints -= (number)
        totalPoints -= (number-1)
        totalPoints -= (number+1)
      }
    }

    result
  }
}
