package leetcode

import scala.collection.mutable

class UndergroundSystem() {
  val travelTimes = mutable.Map[String, mutable.ArrayBuffer[Int]]()
  val customers = mutable.Map[Int, (String, Int)]()


  def checkIn(id: Int, stationName: String, t: Int) {
    customers.update(id, (stationName, t))
  }

  def checkOut(id: Int, stationName: String, t: Int) {
    customers.get(id) match {
      case Some((startStation, startTime)) =>
        val diffTime = t - startTime
        val buffer = travelTimes.getOrElseUpdate(s"${startStation}-${stationName}", mutable.ArrayBuffer[Int]())
        buffer.addOne(diffTime)

      case None =>
    }
  }

  def getAverageTime(startStation: String, endStation: String): Double = {
    travelTimes.get(s"${startStation}-${endStation}") match {
      case Some(buffer) =>
        buffer.sum / buffer.length.toDouble

      case None => 0.0
    }
  }

}
