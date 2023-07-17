package leetcode

import scala.collection.mutable

object twoSum {

  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val result = Array.fill(2)(0)

    val presence = mutable.Map[Int, Int]()

    nums.zipWithIndex foreach {
      case (num, j) =>
        val diff = target - num
        presence.get(diff) match {
          case Some(i) =>
            result(0) = i
            result(1) = j
            return result

          case None =>
            presence(num) = j
        }
    }

    result
  }


  def main(args: Array[String]): Unit = {
    val result = twoSum(Array(2,7,11,15), 9)
    result.foreach(println)
  }

}
