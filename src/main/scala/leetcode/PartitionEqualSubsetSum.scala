package leetcode

import scala.collection.mutable

object Solution {
  def canPartition(nums: Array[Int]): Boolean = {
    val total = nums.sum

    if (total % 2 == 1) {
      false
    } else {
      var half = total / 2
      var start = 0
      var startArray = Array.empty[Int]
      val tabulationStore = mutable.Map[Int, mutable.Set[Array[Int]]]()

      val numbersMap = mutable.Map[Int, Int]()
      for (number <- nums) {
        numbersMap.get(number) match {
          case Some(amount) => numbersMap.update(number, amount+1)
          case None => numbersMap.update(number, 1)
        }
      }

      numbersMap foreach {
        case (number, amount) =>
          if (amount % 2 == 0) {

            half -= number * amount
          } else {
            half -= number * (amount -1)
            startArray = startArray.appended(number)
          }
      }

      var found = false

      if (start / 2 == half) {
        found = true
      }

      tabulationStore.update(start, mutable.Set[Array[Int]](startArray.sorted))

      var i = 0

      while (!found && i < half) {
        tabulationStore.remove(i - 1)

        tabulationStore.get(i) foreach { numberSets =>
          for (numbers <- numberSets) {
            for (j <- 0 until numbers.length) {
              val k = numbers(j)

              val result = i + k
              if (result == half) {
                found = true
              } else if (result < half) {
                val newSet = numbers.take(j) ++ numbers.drop(j + 1)
                tabulationStore.get(result) match {
                  case Some(existingSet) =>
                    tabulationStore.update(result, existingSet.addOne(newSet))

                  case None =>
                    tabulationStore.update(result, mutable.Set(newSet))
                }
              }
            }
          }
        }

        i += 1
      }

      found
    }
  }
}
