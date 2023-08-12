package leetcode

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Sum3 {
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    val sums = mutable.Map[Int, ListBuffer[(Int, Int)]]()
    val results = mutable.ArrayBuffer[List[Int]]()
    val possibleValues = mutable.Map[Int, Boolean]()
    val possibleResults = mutable.Set[(Int, Int)]()

    nums.distinct foreach { number =>
      possibleValues += (0 - number -> true)
    }

    for {
      i <- 0 until (nums.length-1)
      j <- (i+1) until nums.length
    } {
      if (possibleValues.getOrElse(nums(i)+nums(j), false) && !possibleResults.contains((nums(i), nums(j)))) {
        possibleResults.add((nums(i), nums(j)))
        val buffer = sums.getOrElseUpdate(nums(i) + nums(j), ListBuffer[(Int, Int)]())
        buffer.addOne((i, j))
      }
    }

    nums.zipWithIndex foreach {
      case (number, index) =>

        sums.getOrElse(0 - number, ListBuffer.empty) foreach {
          case (i, j) =>
            if (index != i && index != j) {
              val result = List(number, nums(i), nums(j)).sorted
              results.addOne(result)
            }
        }
    }

    results.distinct.toList
  }
}
