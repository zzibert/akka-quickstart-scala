package leetcode

import scala.collection.mutable
import scala.math

object CourseSchedule {
  def canFinish(numCourses: Int, prerequisites: Array[Array[Int]]): Boolean = {
    val prerequisitesByCourse = mutable.Map[Int, mutable.Set[Int]]()
    val finishedCourses = mutable.Set[Int]()
    var _changed = true
    var currentCourses = mutable.ListBuffer.from(prerequisites.map(_(0)))

    for (i <- 0 until numCourses) {
      finishedCourses.addOne(i)
    }

    prerequisites foreach { course =>
      val courses = prerequisitesByCourse.getOrElseUpdate(course(0), mutable.Set[Int]())
      courses.addOne(course(1))
      finishedCourses.remove(course(0))
    }

    while (_changed && currentCourses.length > 0) {
      _changed = false
      val newCourses = mutable.ListBuffer[Int]()
      currentCourses foreach { course =>
        val prereqs = prerequisitesByCourse.getOrElseUpdate(course, mutable.Set[Int]())
        if (prereqs.toSet.subsetOf(finishedCourses)) {
          finishedCourses.addOne(course)
          _changed = true
        } else {
          newCourses.addOne(course)
        }
      }
      currentCourses = newCourses
    }

    currentCourses.length == 0
  }
}
