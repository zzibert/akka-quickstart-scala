package leetcode

object NextPermutation {
  def nextPermutation(nums: Array[Int]): Unit = {
    val length = nums.length

    if (length > 1) {
      var i = length - 1
      while (i > 0) {
        if (nums(i) > nums(i-1) ) {
          val replaceIndex = i-1
          val replace = nums(i-1)
          var smallestBiggerIndex = i
          var smallestBigger = nums(i)
          for (j <- i until length) {
            if (nums(j) > replace && nums(j) < smallestBigger) {
              smallestBigger = nums(j)
              smallestBiggerIndex = j
            }
          }
          nums(replaceIndex) = smallestBigger
          nums(smallestBiggerIndex) = replace
          sortArray(nums, replaceIndex+1)
          return
        } else {
          i -= 1
        }
      }

      nums.sortInPlace()
    }
  }

  def sortArray(nums: Array[Int], start: Int): Unit = {
    val length = nums.length

    if (start < length) {
      val original = nums(start)

      var smallest = nums(start)
      var smallestIndex = start

      for (i <- (start + 1) until length) {
        if (nums(i) < smallest) {
          smallest = nums(i)
          smallestIndex = i
        }
      }

      nums(start) = smallest
      nums(smallestIndex) = original

      sortArray(nums, start + 1)
    }
  }


//  def nextPermutation(nums: Array[Int]): Unit = {
//    val length = nums.length
//
//    if (length > 1) {
//      var i = length - 1
//      while (i > 0) {
//        if (nums(i) > nums(i - 1)) {
//          swap(nums, i)
//          return
//        } else {
//          swap(nums, i)
//          i -= 1
//        }
//      }
//
//      nums.sortInPlace()
//    }
//  }
//
//  def swap(nums: Array[Int], index: Int): Unit = {
//    val temp = nums(index)
//    nums(index) = nums(index - 1)
//    nums(index - 1) = temp
//  }
}
