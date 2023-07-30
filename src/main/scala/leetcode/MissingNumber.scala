package leetcode

class MissingNumber {
  def missingNumber(nums: Array[Int]): Int = {
    nums.sortInPlace()

    nums.zipWithIndex foreach {
      case (num, index) =>
        if (num != index) {
          return index
        }
    }
    nums.last+1
  }

}
