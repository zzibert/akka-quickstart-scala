package leetcode

object Solution {
  def longestSubarray(nums: Array[Int]): Int = {
    var result = 0
    val length = nums.length
    val numberOfOnes = Array.fill(length)(0)
    var firstOne: Option[Int] = None
    var counter = 0

    for {
      i <- 0 until length
      number = nums(i)
    } {
      number match {
        case 0 =>
          firstOne foreach { first =>
            numberOfOnes(first) = counter
            numberOfOnes(i - 1) = counter
          }
          firstOne = None
          counter = 0

        case 1 =>
          firstOne match {
            case Some(_) =>
            case None =>
              firstOne = Some(i)
          }
          counter += 1
      }
    }

    firstOne foreach { first =>
      numberOfOnes(first) = counter
    }
    numberOfOnes(length-1) = counter

    var foundZero = false

    for {
      i <- 0 until length
      number = nums(i) if number == 0
    } {
      foundZero = true
      var currentResult = 0
      if (i - 1 >= 0) {
        currentResult += numberOfOnes(i - 1)
      }
      if (i + 1 < length) {
        currentResult += numberOfOnes(i + 1)
      }

      if (currentResult > result) {
        result = currentResult
      }
    }

    if (foundZero) {
      result
    } else {
      length - 1
    }
  }
}
