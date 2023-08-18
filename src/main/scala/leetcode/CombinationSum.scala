package leetcode

import scala.collection.mutable
import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer

object CombinationSum {
  def combinationSum(candidates: Array[Int], target: Int): List[List[Int]] = {
    val combinations = mutable.Map[Int, Set[List[Int]]]()

    candidates foreach { candidate =>
      val combination = ListBuffer[Int]()
      var combinationSum = 0
      while (combinationSum <= target) {
        combination.addOne(candidate)
        combinationSum = combination.sum
        val buffer = combinations.getOrElseUpdate(combinationSum, Set[List[Int]]())
        buffer.addOne(combination.toList)

        val subtraction = target - combinationSum
        for {
          i <- 2 to subtraction
        } {
          val buffer2 = combinations.getOrElseUpdate(i, Set[List[Int]]())
          buffer2 foreach { combination2 =>
            val newCombination = combination ++ combination2
            val buffer3 = combinations.getOrElseUpdate(newCombination.sum, Set[List[Int]]())
            buffer3.addOne(newCombination.toList.sorted)
          }
        }
      }
    }

    combinations.getOrElse(target, ListBuffer[List[Int]]()).toList
  }
}
