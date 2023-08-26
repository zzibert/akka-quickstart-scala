package leetcode

import scala.collection.mutable

class HouseRobber {
  def rob(nums: Array[Int]): Int = {
    val stash = mutable.Map[Int, Int]()
    val length = nums.length

    if (length == 1) {
      return nums(0)
    }

    stash += 0 -> nums(0)
    stash += 1 -> nums(1)

    for (i <- 2 until length) {
      val maximum = List(stash.getOrElse(i-2, 0), stash.getOrElse(i-3, 0)).max
      stash += i -> (maximum + nums(i))
    }


    List(stash.getOrElse(length-1, 0), stash.getOrElse(length-2, 0)).max
  }


}
