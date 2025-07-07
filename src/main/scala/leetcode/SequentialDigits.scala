package leetcode

import elements.Parity.{bigParity, countBits, parity}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

"12345678910"

object SequentialDigits {
  def sequentialDigits(low: Int, high: Int): List[Int] = {
    val length = low.toString.length
    val digits = low.toString.toCharArray.map(_.toInt)
    val results = ArrayBuffer[Int]()
    var candidate = createNewNumber(length-1)

    while (true) {
      if (candidate >= low && candidate <= high) {
        results.addOne(candidate)
      } else if (candidate > high) {
        return results.toList
      }

      createNewCombination(candidate) match {
        case Some(combination) =>
          candidate = combination

        case _ =>
          candidate = createNewNumber(candidate.toString.length)
      }
    }

    results.toList
  }

  def createNewNumber(length: Int): Int = {
    var number = ""
    var digit = 2
    for (i <- 1 to length) {
      number += digit.toString
      digit = (digit + 1) % 10
    }

    "1".concat(number).toInt
  }

  def createNewCombination(candidate: Int): Option[Int] = {
    val digits = candidate.toString.toCharArray
    val length = digits.length
    val last = digits(length-1).toInt
    if (last == 57) {
      None
    } else {
      val next = (last - 47).toString
      Some(digits.drop(1).mkString.concat(next).toInt)
    }
  }
}
