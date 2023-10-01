package leetcode

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object NumberOfVisiblePeopleInaQueue {

  case class Person(height: Int, index: Int)
  def canSeePersonsCount(heights: Array[Int]): Array[Int] = {
    val buffer = mutable.ArrayBuffer[Person]()
    val result = ArrayBuffer.from(Array.fill(heights.length)(0))

    heights.zipWithIndex foreach {
      case (height, index) =>
        if (buffer.isEmpty) {
          buffer.addOne(Person(height, index))
        } else {
          val person = Person(height, index)
          increment(person, buffer, result)
          remove(person, buffer)
          buffer.addOne(person)
        }
    }

    result.toArray
  }

  def increment(person: Person, buffer: mutable.ArrayBuffer[Person], result: ArrayBuffer[Int]): Unit = {
    var i = buffer.length-1

    while (i >= 0) {
      val oldPerson = buffer(i)
      result(oldPerson.index) += 1
      if (oldPerson.height > person.height) {
        return
      }
      i -= 1
    }
  }

  def remove(person: Person, buffer: mutable.ArrayBuffer[Person]): Unit = {
    buffer.filterInPlace(candidate => candidate.height > person.height)
  }

}
