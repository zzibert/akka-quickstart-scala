package leetcode

import scala.collection.mutable

object Solution {
  def canFinish(numCourses: Int, prerequisites: Array[Array[Int]]): Boolean = {
    val classes = prerequisites.groupBy(_(0)) map {
      case (course, required) =>
        val requiredClasses = required.map(_(1))
        (course, requiredClasses)
    }

    val finishedClasses = mutable.Set[Int]()
    val queue = mutable.Queue[Int]()

    for (c <- 0 until numCourses) {
      queue.enqueue(c)
    }

    var foundNewClass = true

    while (queue.nonEmpty && foundNewClass) {
      val counter = queue.size
      foundNewClass = false
      for (_ <- 0 until counter) {
        val unfinished = queue.dequeue()
        classes.get(unfinished) match {
          case Some(required) =>
            if (required.forall(finishedClasses.contains)) {
              finishedClasses.add(unfinished)
              foundNewClass = true
            } else {
              queue.enqueue(unfinished)
            }

          case None =>
            foundNewClass = true
            finishedClasses.add(unfinished)

        }
      }
    }

    queue.isEmpty
  }
}
