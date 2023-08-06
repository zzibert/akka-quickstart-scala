package leetcode

import scala.collection.mutable

object NumberOf1Bits {
  def hammingWeight(n: Int): Int = {
    n.toBinaryString.toArray
    calculateOnes(n.toBinaryString.toArray)
  }

  def calculateOnes(binary: Array[Char]): Int = {
    var amount = 0
    binary foreach { number =>
      if (number == '1') {
        amount += 1
      }
    }

    amount
  }
}
