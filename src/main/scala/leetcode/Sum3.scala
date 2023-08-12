package leetcode

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Sum3 {
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    val sums = mutable.Map[Int, ListBuffer[(Int, Int)]]()
    val results = mutable.ArrayBuffer[List[Int]]()
    val indexes = mutable.ArrayBuffer[List[Int]]()

    for {
      i <- 0 until (nums.length-1)
      j <- (i+1) until nums.length
    } {
      val buffer = sums.getOrElseUpdate(nums(i) + nums(j), ListBuffer[(Int, Int)]())
      buffer.addOne((i, j))
    }

    nums.zipWithIndex foreach {
      case (number, index) =>

        sums.getOrElse(0 - number, ListBuffer.empty) foreach {
          case (i, j) =>
            if (index != i && index != j) {
              val indexResult = List(index, i, j).sorted
              if (!indexes.exists(_ == indexResult)) {
                indexes.addOne(indexResult)
                val result = List(number, nums(i), nums(j)).sorted
                if (!results.exists(_ == result)) {
                  results.addOne(result)
                }
              }
            }
        }
    }

    results.toList.distinct
  }
}
