package leetcode

import scala.collection.mutable

object CountingBits {
  def countBits(n: Int): Array[Int] = {
    val binaries = mutable.ArrayBuffer[Int]()
    val numbers = mutable.Map[Int, Int]()

    var divider = 1
    numbers += (0 -> 0)
    numbers += (1 -> 1)

    for (num <- 0 to n) {
      if (num < 2) {
        binaries.addOne(numbers(num))
        divider = 2
      } else {
        if (num == divider) {
          numbers += (num -> 1)
          binaries.addOne(1)
          divider *= 2
        } else {
          val former = divider / 2
          val result = numbers(former) + numbers(num - former)
          numbers += (num -> result)
          binaries.addOne(result)
        }
      }
    }

    binaries.toArray
  }

  def calculateBinary(n: Int): Array[Int] = {
    if (n == 0) {
      Array(0)
    } else {
      var number = n
      var binary = mutable.ArrayBuffer[Int]()
      var divider = 1
      while (number > divider) {
        divider *= 2
      }

      if (divider > number) {
        divider /= 2
      }

      while (number >= 0 && divider >= 1) {
        if (number >= divider) {
          binary.addOne(1)
          number -= divider
        } else {
          binary.addOne(0)
        }
        divider /= 2
      }

      binary.toArray
    }
  }

  def calculateOnes(binary: Array[Int]): Int = {
    var amount = 0
    binary foreach { number =>
      if (number == 1) {
        amount += 1
      }
    }

    amount
  }
}
