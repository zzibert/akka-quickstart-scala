package leetcode

import scala.collection.mutable

object Solution {
  case class StationAndGasLeft(station: Int, gasLeft: Int)

  def canCompleteCircuit(gas: Array[Int], cost: Array[Int]): Int = {
    val length = gas.length
    var result = -1

    val portal = mutable.Map[Int, StationAndGasLeft]()

    val potentialStarts =
      (for {
        i <- 0 until length
        if gas(i) >= cost(i)
      } yield i).sorted.reverse

    var found = false

    for {
      start <- potentialStarts
      if !found
    } {
      val end = start
      var currentGas = gas(start) - cost(start)
      var current = (start + 1) % gas.length
      currentGas += gas(current)

      while (current != end && currentGas >= cost(current)) {
        currentGas -= cost(current)
        current = (current + 1) % length
        currentGas += gas(current)
      }

      if (current == end) {
        found = true
        result = current
      } else {

      }
    }

    result
  }
}
