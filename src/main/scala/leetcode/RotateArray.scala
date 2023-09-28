package leetcode

object RotateArray {
  def rotate(nums: Array[Int], k: Int): Unit = {
    val length = nums.length
    val rotation = k % length

    val first = nums.drop(length-rotation)
    val second = nums.take(length-rotation)

    val newNums = first ++ second

    for (i <- 0 until length) {
      nums(i) = newNums(i)
    }
  }
}
