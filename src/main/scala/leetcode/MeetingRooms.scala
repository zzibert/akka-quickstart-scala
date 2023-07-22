package leetcode

import scala.collection.mutable

class MeetingRooms {
  def canAttendMeetings(intervals: Array[Array[Int]]): Boolean = {
    val attendance = mutable.Map[Int, Boolean]()

    for (interval <- intervals) {
      for (hour <- (interval(0) until interval(1))) {
        if (attendance.getOrElse(hour, false)) {
          return false
        }
        attendance(hour) = true
      }
    }
    true
  }
}
