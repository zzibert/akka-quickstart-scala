package leetcode

class SparseVector(nums: Array[Int]) {
  // Return the dotProduct of two sparse vectors
  val nonZeroIndexes =
    nums.zipWithIndex collect {
      case (number, index) if number > 0 => index
    }

  val numbers = nums
  def dotProduct(vec: SparseVector): Int = {
    var result = 0

    nonZeroIndexes.intersect(vec.nonZeroIndexes) foreach { index =>
      val var1 = nums(index)
      val var2 = vec.numbers(index)
      result += (var1 * var2)
    }

    result
  }
}
