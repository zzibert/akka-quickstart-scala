package leetcode

import scala.collection.mutable.{ ArrayBuffer, ListBuffer }

object ReconstructItinerary {
  def findItinerary(tickets: List[List[String]]): List[String] = {
    var itineraries = Map[String, List[String]]()
    val count = tickets.length
    val result = ListBuffer[List[String]]()

    tickets foreach { ticket =>
      val buffer = itineraries.getOrElse(ticket(0), List[String]())
      itineraries += (ticket(0) -> (ticket(1) :: buffer).sorted)
    }

    itineraryFinder(Array("JFK"), itineraries, count, result)

    if (result.isEmpty) {
      List("")
    } else {
      result.head
    }
  }

  def itineraryFinder(path: Array[String], itineraries: Map[String, List[String]], count: Int, result: ListBuffer[List[String]]): Boolean = {
    val possibleFlights = itineraries.getOrElse(path.last, List.empty)

    if (count == 0) {
      result.addOne(path.toList)
      true
    } else if (possibleFlights.isEmpty) {
      false
    } else {
      possibleFlights.zipWithIndex foreach {
        case (flight, index) =>
          val newPossibleFlights = ArrayBuffer.from(possibleFlights)
          newPossibleFlights.remove(index)
          val newItenerary = itineraries + (path.last -> newPossibleFlights.toList)
          if (itineraryFinder(path.appended(flight), newItenerary, count - 1, result)) {
            return true
          }
      }
      false
    }
  }
}
