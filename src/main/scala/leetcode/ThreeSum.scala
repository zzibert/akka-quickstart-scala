object Solution {
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    var result = Set[List[Int]]()

    for {
      i <- 0 until nums.length
      j <- i + 1 until nums.length
      k <- j + 1 until nums.length
    } {
      if (nums(i) + nums(j) + nums(k) == 0) {
        result += List(nums(i), nums(j), nums(k)).sorted
      }
    }

    result.toList
  }
}
