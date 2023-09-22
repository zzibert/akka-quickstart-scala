package leetcode

object GasStation {
  def canCompleteCircuit(gas: Array[Int], cost: Array[Int]): Int = {
    val numberOfStations = gas.length

    val possibleStartingPositions =
      Array.from(0 until numberOfStations) filter { station =>
        gas(station) >= cost(station)
      }

    possibleStartingPositions foreach { origin =>
      if (canCompleteCircuitHelper(origin, origin, gas(origin), numberOfStations, gas, cost)) {
        return origin
      }
    }

    -1
  }

  def canCompleteCircuitHelper(origin: Int, currentStation: Int, tank: Int, numberOfStations: Int, gas: Array[Int], cost: Array[Int]): Boolean = {
    if (tank < cost(currentStation)) {
      false
    } else {
      val newStation = (currentStation+1) % numberOfStations
      if (newStation == origin) {
        true
      } else {
        val newTank = tank - cost(currentStation) + gas(newStation)
        canCompleteCircuitHelper(origin, newStation, newTank, numberOfStations, gas, cost)
      }
    }
  }
}
