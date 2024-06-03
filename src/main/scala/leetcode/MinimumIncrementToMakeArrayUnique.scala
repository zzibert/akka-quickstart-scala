package leetcode

import scala.collection.mutable

object Solution {
  def minIncrementForUnique(nums: Array[Int]): Int = {
    val sortedNums = nums.sorted
    var result = 0
    var highest = 0
    val used = mutable.Map[Int, Boolean]()

    var i = 0

    while (i < sortedNums.length) {
      used.get(sortedNums(i)) match {
        case Some(_) =>
          result += (highest + 1) - sortedNums(i)
          sortedNums(i) = (highest + 1)

        case None =>
          used += (sortedNums(i) -> true)
          highest = sortedNums(i)
          i += 1
      }
    }

    result
  }
}
