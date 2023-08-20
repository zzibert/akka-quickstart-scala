package leetcode

object SortColors {
  def sortColors(nums: Array[Int]): Unit = {
    val occurrence = Array.fill(3)(0)

    nums foreach { color =>
      occurrence(color) += 1
    }

    var j = 0

    for {
      i <- 0 to 2
    } {
      val number = occurrence(i)

      for (_ <- 0 until number) {
        nums(j) = i
        j += 1
      }
    }
  }
}
