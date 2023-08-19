package leetcode


object ProductOfArrayExceptSelf {
  def productExceptSelf(nums: Array[Int]): Array[Int] = {
    val leftProduct = new Array[Int](nums.length)
    val rightProduct = new Array[Int](nums.length)


    for {
      i <- 0 until nums.length
    } {
      if (i == 0) {
        leftProduct(i) = nums(i)
      } else {
        leftProduct(i) = leftProduct(i - 1) * nums(i)
      }
    }

    var j = nums.length - 1
    while (j >= 0) {
      if (j == nums.length - 1) {
        rightProduct(j) = nums(j)
      } else {
        rightProduct(j) = rightProduct(j + 1) * nums(j)
      }
      j -= 1
    }

    for {
      i <- 0 until nums.length
    } {
      if (i == 0) {
        nums(i) = rightProduct(i + 1)
      } else if (i == (nums.length - 1)) {
        nums(i) = leftProduct(i - 1)
      } else {
        nums(i) = leftProduct(i - 1) * rightProduct(i + 1)
      }
    }

    nums
  }
}
