package leetcode
object DailyTemperatures {
  def dailyTemperatures(temperatures: Array[Int]): Array[Int] = {
    val result = Array.fill(temperatures.length)(0)

    for (i <- 0 until temperatures.length) {
      result(i) = traverseUntillBigger(i, temperatures)
    }

    result
  }

  def traverseUntillBigger(index: Int, nums: Array[Int]): Int = {
    val number = nums(index)
    var result = 0

    for (i <- index+1 until nums.length) {
      if (nums(i) > number) {
        return result
      } else {
        result += 1
      }
    }

    0
  }
}
