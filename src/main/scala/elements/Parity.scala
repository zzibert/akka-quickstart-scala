package elements

import scala.collection.mutable


object Parity {
  def countBits(number: Int): Int = {
    var n = number
    var numBits = 0
    while (n != 0) {
      numBits += (n & 1)
      n = n >> 1
    }
    numBits
  }

  def parity(number: Int): Int = {
    var n = number
    var result = 0
    while (n != 0) {
      result ^= (n & 1)
      n = n >> 1
    }
    result
  }

  def bigParity(number: Long, lookupTable: mutable.Map[Long, Long]): Long = {
    var n = number
    val wordSize = 16
    var result: Long = 0
    val quarterMask: Long = (1 << wordSize) - 1
    var quarter = 0
    while (quarter < 4) {
      result ^= lookupTable(n & quarterMask)
      n = n >> wordSize
      quarter += 1
    }
    result
  }

  def main(args: Array[String]): Unit = {

    println(countBits(7))
    println(parity(7))

    val lookupTable = mutable.Map.empty[Long, Long]
    for (n <- 0 to (0xFFFF)) {
      lookupTable(n) = parity(n)
    }
    val quarterMask = (1 << 16) - 1
    println(s"this is the result for 254 : ${bigParity(254L, lookupTable)}")
    println(s"this is the result for 1742346774 : ${bigParity(1742346774L, lookupTable)}")
  }

}
