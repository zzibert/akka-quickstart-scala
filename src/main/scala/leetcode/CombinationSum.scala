package leetcode

import scala.collection.mutable

object Solution {
  def combinationSum(candidates: Array[Int], target: Int): List[List[Int]] = {
    val result = mutable.ListBuffer[Array[Int]]()
    val seen = mutable.Map[Array[Int], Boolean]()

    combinationSumHelper(candidates, target, Array.empty, result, seen)

    result.map(_.toList).toList
  }

  def combinationSumHelper(
      candidates: Array[Int],
      target: Int,
      currentList: Array[Int],
      result: mutable.ListBuffer[Array[Int]],
      seen: mutable.Map[Array[Int], Boolean]
  ): Unit = {
    seen.get(currentList) match {
      case Some(_) =>
      case None =>
        seen.update(currentList, true)
        for (i <- 0 until candidates.length) {
          val newArray = (currentList :+ candidates(i)).sorted
          if (seen.get(newArray).isEmpty) {
            val summa = newArray.sum
            if (summa == target) {
              result.addOne(newArray)
            } else if (summa < target) {
              combinationSumHelper(candidates, target, newArray, result, seen)
            }
          }
        }
    }
  }
}
