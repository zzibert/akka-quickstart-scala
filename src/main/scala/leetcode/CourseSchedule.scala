import scala.collection.mutable

object Solution {
  def canFinish(numCourses: Int, prerequisites: Array[Array[Int]]): Boolean = {
    val availableCourses = mutable.Set[Int]()

    var requiredCourses =
      prerequisites
        .groupBy(x => x(0))
        .collect {
          case (key, value) =>
            val courses = value.map(x => x(1))
            key -> courses
        }

    for (i <- 0 until numCourses) {
      if (requiredCourses.get(i).isEmpty) {
        availableCourses.add(i)
      }
    }

    var proceed = true

    while (proceed) {
      proceed = false

      requiredCourses foreach {
        case (course, courses) =>
          if (courses.forall(availableCourses.contains)) {
            availableCourses.add(course)
            requiredCourses -=  course
            proceed = true
          }
      }
    }

    requiredCourses.isEmpty
  }
}
