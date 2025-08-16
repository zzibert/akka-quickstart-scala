package leetcode

import scala.collection.mutable

object Solution {
  def findOrder(numCourses: Int,
                prerequisites: Array[Array[Int]]): Array[Int] = {
    val result = mutable.ArrayBuffer[Int]()

    var prerequisitesByClass =
      prerequisites
        .groupBy(_(0))
        .map {
          case (key, value) =>
            val classes = value.map(_(1))
            (key, classes)
        }

    // classes without prerequisites
    for (number <- 0 until numCourses) {
      if (prerequisitesByClass.get(number).isEmpty) {
        result.addOne(number)
      }
    }

    var newClass = true

    while (newClass) {
      newClass = false
      prerequisitesByClass foreach {
        case (key, classes) =>
          if (classes.forall(result.contains)) {
            prerequisitesByClass -= key
            result.addOne(key)
            newClass = true
          }
      }
    }

    if (result.length == numCourses) {
      result.toArray
    } else {
      Array.empty
    }
  }
}
