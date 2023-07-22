package leetcode

import scala.collection.mutable

class ContainsDuplicate {
  def containsDuplicate(nums: Array[Int]): Boolean = {
    val appearances = mutable.Map[Int, Boolean]()

    for (num <- nums) {
      if (appearances.getOrElse(num, false)) {
        return true
      }
      appearances += (num -> true)
    }

    false
  }
}
