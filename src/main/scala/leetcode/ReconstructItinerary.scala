package leetcode

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object ReconstructItinerary {
  def findItinerary(tickets: List[List[String]]): List[String] = {
    val itineraries = mutable.Map[String, ArrayBuffer[String]]()
    val count = tickets.length

    tickets foreach { ticket =>
      val buffer = itineraries.getOrElseUpdate(ticket(0), ArrayBuffer[String]())
      buffer.addOne(ticket(1)).sortInPlace()
    }

    itineraryFinder(Array("JFK"), itineraries)
  }

  def itineraryFinder(path: Array[String], itineraries: mutable.Map[String, ArrayBuffer[String]]): List[String] = {
    val possibleFlights = itineraries.getOrElse(path.last, ArrayBuffer.empty)
    if (possibleFlights.isEmpty) {
      path.toList
    } else {
      val nextFlight = possibleFlights.head
      possibleFlights.dropInPlace(1)
      itineraryFinder(path.appended(nextFlight), itineraries)
    }
  }
}
