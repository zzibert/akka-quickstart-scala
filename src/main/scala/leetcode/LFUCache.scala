package leetcode

import scala.collection.mutable

class LFUCache(_capacity: Int) {

  case class Record(value: Int, counter: Int)
  case class Used(key: Int, counter: Int)

  val values = mutable.Map[Int, Record]()
  val used = mutable.Stack[Used]()


  def get(key: Int): Int = {
    values.get(key) match {
      case Some(record) =>
        val newCounter = record.counter+1
        addLeastFrequent(key, newCounter)
        values += (key -> record.copy(counter = newCounter))
        record.value

      case _ => -1
    }
  }

  def put(key: Int, value: Int) {
    values.get(key) match {
      case Some(record) =>
        val newCounter = record.counter+1
        addLeastFrequent(key, newCounter)
        values += (key -> Record(value, newCounter))

      case None =>
        if (values.size < _capacity) {
          addElement(key, value)
        } else {
          removeLeastFrequent()
          addElement(key, value)
        }
    }
  }

  def addElement(key: Int, value: Int): Unit = {
    values += (key -> Record(value, 1))
    addLeastFrequent(key, 1)
  }

  def addLeastFrequent(key: Int, counter: Int): Unit = {
    if (used.isEmpty || counter <= used.head.counter) {
      used.push(Used(key, counter))
    }
  }

  def removeLeastFrequent(): Unit = {
    var removeLeastFrequent = false
    while (!removeLeastFrequent) {
      val leastFrequent = used.pop()
      values.get(leastFrequent.key) match {
        case Some(record) =>
          if (record.counter == leastFrequent.counter || used.isEmpty) {
            values -= leastFrequent.key
            removeLeastFrequent = true
          }
        case None =>
      }
    }
  }
}
