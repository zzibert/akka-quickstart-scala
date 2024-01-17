package leetcode

import scala.collection.mutable

class Logger() {
  val messages = mutable.Map[String, Int]()
  def shouldPrintMessage(timestamp: Int, message: String): Boolean = {
    messages.get(message) match {
      case Some(last) =>
        if (last + 10 <= timestamp) {
          messages.update(message, timestamp)
          true
        } else {
          false
        }
      case None =>
        messages.update(message, timestamp)
        true
    }
  }

}
