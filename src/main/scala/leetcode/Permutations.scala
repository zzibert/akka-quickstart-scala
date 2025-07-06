

object Solution {
  def permute(nums: Array[Int]): List[List[Int]] = {

    createPermutation(nums, List.empty)
  }

  def createPermutation(nums: Array[Int],
                        permutation: List[Int]): List[List[Int]] = {
    if (nums.isEmpty) {
      List(permutation)
    } else {
      var result: List[List[Int]] = List.empty

      for (i <- 0 until nums.length) {
        val number = nums(i)
        val newNums = nums.take(i) ++ nums.drop(i + 1)
        val child = createPermutation(newNums, number :: permutation)
        result = child ++ result
      }

      result.distinct
    }
  }
}
