

object Solution {
  def threeSum(nums: Array[Int]): List[List[Int]] = {

    val length = nums.length
    var result = Set[List[Int]]()
    (for {
      i <- 0 until length
      j <- i + 1 until length
      k <- j + 1 until length
      if (nums(i) + nums(j) + nums(k)) == 0
    } yield {
      val list = List(nums(i), nums(j), nums(k)).sorted
      if (!result.contains(list)) {
        result += list
      }
    }).toList

    result.toList
  }
}
