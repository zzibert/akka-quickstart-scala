package leetcode

import scala.collection.mutable

object LongestCommonSubsequence {
  def longestCommonSubsequence(text1: String, text2: String): Int = {
    val combinations = mutable.Map[String, Int]()

    val validChars = text2.toArray.intersect(text1).distinct

    val text1Filtered = String.valueOf(text1.toArray.filter(validChars.contains))

    val text2Filtered = String.valueOf(text2.toArray.filter(validChars.contains))

    traverseCombinations(text1Filtered, combinations)

    findCombination(text2Filtered, combinations)
  }

  def traverseCombinations(subsequence: String, combinations: mutable.Map[String, Int]): Unit = {
    if (combinations.get(subsequence).isEmpty) {
      combinations += (subsequence -> subsequence.length)
      for (i <- 0 until subsequence.length) {
        traverseCombinations(subsequence.patch(i, Nil, 1), combinations)
      }
    }
  }

  def findCombination(subsequence: String, combinations: mutable.Map[String, Int]): Int = {
    combinations.get(subsequence) match {
      case Some(length) => length
      case None =>
        var maxLength = 0
        for (i <- 0 until subsequence.length) {
          val localMax = findCombination(subsequence.patch(i, Nil, 1), combinations)
          if (localMax == (subsequence.length-1)) {
            combinations += (subsequence -> localMax)
            return localMax
          }
          if (localMax > maxLength) {
            maxLength = localMax
          }
        }
        combinations += (subsequence -> maxLength)
        maxLength
    }
  }

}
