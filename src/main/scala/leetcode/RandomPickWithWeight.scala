package leetcode

import scala.util.Random

class Solution(_w: Array[Int]) {
  val sum = _w.sum

  def pickIndex(): Int = {
    val current = Random.nextInt(sum)
    var currentSum = 0
    var i = -1

    while (currentSum < current) {
      i += 1
      currentSum += _w(i)
    }

    Math.max(0, i)
  }
}
