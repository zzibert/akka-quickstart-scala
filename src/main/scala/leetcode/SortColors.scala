package leetcode

object Solution {
  def sortColors(nums: Array[Int]): Unit = {
    var redPointer = 0
    var whitePointer = 0
    var i = 0

    while (i < nums.length) {
      nums(i) match {
        case 0 =>
          if (nums(redPointer) != 0) {
            val temp = nums(redPointer)
            nums(redPointer) = 0
            nums(i) = temp
            redPointer += 1
          } else {
            redPointer += 1
            i += 1
          }
        case 1 =>
          while (whitePointer < nums.length && nums(whitePointer) < 1) {
            whitePointer += 1
          }
          if (whitePointer < nums.length && nums(whitePointer) != 1) {
            val temp = nums(whitePointer)
            nums(whitePointer) = 1
            nums(i) = temp
            whitePointer += 1
          } else {
            whitePointer += 1
            i += 1
          }

        case 2 =>
          i += 1
      }
    }
  }
}
