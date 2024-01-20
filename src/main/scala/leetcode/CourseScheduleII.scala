package leetcode

import scala.collection.mutable

object CourseScheduleII {
  def findOrder(numCourses: Int,
                prerequisites: Array[Array[Int]]): Array[Int] = {
    val requirements = mutable.Map[Int, mutable.ListBuffer[Int]]()
    val unlocked = mutable.Map[Int, mutable.ListBuffer[Int]]()
    val visited = mutable.Map[Int, Boolean]()
    val finished = mutable.ArrayBuffer[Int]()
    val queue = mutable.Queue[Int]()

    prerequisites foreach {
      case Array(a, b) =>
        val rBuffer = requirements.getOrElseUpdate(a, mutable.ListBuffer[Int]())
        rBuffer.addOne(b)

        val uBuffer = unlocked.getOrElseUpdate(b, mutable.ListBuffer[Int]())
        uBuffer.addOne(a)
    }

    val coursesWithPrerequisites = prerequisites.map(_.head).toSet

    for (i <- 0 until numCourses) {
      if (!coursesWithPrerequisites.contains(i) && !visited.getOrElse(i, false)) {
        queue.enqueue(i)
        visited.update(i, true)
      }
    }

    while (queue.length > 0) {
      val next = queue.dequeue()
      finished.addOne(next)
      unlocked.get(next) match {
        case Some(unlockedClasses) =>
          unlockedClasses foreach { c =>
            if (!visited.getOrElse(c, false) && allPrerequisitesMet(
                  requirements.getOrElse(c, mutable.ListBuffer.empty),
                  finished)) {
              queue.enqueue(c)
              visited.update(c, true)
            }
          }

        case _ =>
      }
    }

    if (finished.length == numCourses) {
      finished.toArray
    } else {
      Array.empty
    }

  }

  def allPrerequisitesMet(prerequisites: mutable.ListBuffer[Int],
                          finished: mutable.ArrayBuffer[Int]): Boolean = {
    prerequisites.forall(p => finished.contains(p))
  }
}
