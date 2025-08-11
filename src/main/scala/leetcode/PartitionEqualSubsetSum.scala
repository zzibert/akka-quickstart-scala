package leetcode

import scala.collection.mutable

object Solution {
  def canPartition(nums: Array[Int]): Boolean = {
    val total = nums.sum

    if (total % 2 == 1) {
      false
    } else {
      val half = total / 2
      val remainingNumbers = mutable.Map.empty[Int, Set[Array[Int]]]
      remainingNumbers.update(0, Set(nums))

      for {
        i <- 0 until half
        remainingList <- remainingNumbers.get(i)
        remaining <- remainingList
      } {
        for (j <- 0 until remaining.length) {
          val score = i + remaining(j)
          if (score > 0 && score <= half) {
            val newArray = remaining.take(j) ++ remaining.drop(j + 1)
            remainingNumbers.get(score) match {
              case Some(remainingSet) =>
                if (!remainingSet.exists(a => a.sameElements(newArray))) {
                  remainingNumbers.update(score, remainingSet + newArray)
                }
              case None =>
                remainingNumbers.update(score, Set(newArray))
            }
          }
        }

        remainingNumbers.drop(i)
      }

      for (i <- 3 until half) {
        val remaining = remainingNumbers(i)
        remaining foreach { a =>
          println(s"${i} a: ${a.mkString(" ")}")
        }
      }

      remainingNumbers.get(half).isDefined
    }
  }
}

