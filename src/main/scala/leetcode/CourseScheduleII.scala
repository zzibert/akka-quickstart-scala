package leetcode

import scala.collection.mutable

object Solution {
  def findOrder(numCourses: Int, prerequisites: Array[Array[Int]]): Array[Int] = {
    val result = mutable.ArrayBuffer[Int]()
    val allowedClasses = mutable.ArrayBuffer[Int]()

    var prerequisitesByClass =
      prerequisites
        .groupBy(_(0))
        .map { case (key, classes) =>
          val buffer = mutable.ArrayBuffer.from(classes.map(_(1)))
          (key, buffer)
        }

    for (i <- 0 until numCourses) {
      if (prerequisitesByClass.get(i).isEmpty) {
        allowedClasses.addOne(i)
      }
    }

    while (allowedClasses.nonEmpty) {
      val removedClass = allowedClasses.head
      result.addOne(removedClass)
      allowedClasses.dropInPlace(1)

      prerequisitesByClass foreach {
        case (key, value) =>
          value.filterInPlace(x => x != removedClass)
          if (value.isEmpty) {
            allowedClasses.addOne(key)
            prerequisitesByClass -= key
          }
      }
    }

    if (prerequisitesByClass.isEmpty) {
      result.toArray
    } else {
      Array()
    }
  }
}
