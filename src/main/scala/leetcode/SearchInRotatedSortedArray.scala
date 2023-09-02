package leetcode
class SearchInRotatedSortedArray {
  def search(nums: Array[Int], target: Int): Int = {
    val length = nums.length
    val newNums = nums.zipWithIndex
    if (nums(0) > nums(length-1)) {
      val pivotIndex = findPivotIndex(newNums)
      val firstHalf = newNums.take(pivotIndex+1)
      val secondHalf = newNums.drop(pivotIndex)
      binarySearch(secondHalf ++ firstHalf, target)
    } else {
      binarySearch(newNums, target)
    }
  }

  def findPivotIndex(nums: Array[(Int, Int)]): Int = {
    val length = nums.length

    val middleIndex = length / 2

    val middle = nums(middleIndex)._1

    if (isInbound(middleIndex-1, length)) {
      if (nums(middleIndex-1)._1 > middle) {
        return nums(middleIndex-1)._2
      }
    }

    if (isInbound(middleIndex+1, length)) {
      if (nums(middleIndex+1)._1 < middle) {
        return  nums(middleIndex+1)._2
      }
    }

    if (middle > nums(0)._1) {
      findPivotIndex(nums.drop(middleIndex+1))
    } else {
      findPivotIndex(nums.take(middleIndex))
    }
  }

  def isInbound(index: Int, length: Int): Boolean = {
    length > index && index >= 0
  }

  def binarySearch(nums: Array[(Int, Int)], target: Int): Int = {
    val length = nums.length

    if (length < 3) {
      for (i <- 0 until length) {
        if (nums(i)._1 == target) {
          return nums(i)._2
        }
      }
      return -1
    }

    val middleIndex = length / 2

    val middle = nums(middleIndex)._1

    if (middle == target) {
       nums(middleIndex)._2
    } else if (middle < target) {
      binarySearch(nums.drop(middleIndex+1), target)
    } else {
      binarySearch(nums.take(middleIndex), target)
    }
  }
}
