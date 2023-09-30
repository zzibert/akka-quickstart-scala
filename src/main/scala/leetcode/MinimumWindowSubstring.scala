package leetcode

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object MinimumWindowSubstring {
  def minWindow(s: String, t: String): String = {
    for (length <- t.length to s.length) {
      val example = ArrayBuffer[Char]()

      for (i <- 0 until length) {
        example.addOne(s(i))
      }

      for (i <- length until s.length) {
        if (isSubSequence(example, t)) {
          return example.mkString("")
        } else {
          example.dropInPlace(1).addOne(s(i))
        }
      }
      if (isSubSequence(example, t)) {

        return example.mkString("")
      }
    }
    ""
  }

  def isSubSequence(example: ArrayBuffer[Char], target: String): Boolean = {
    val chars = mutable.Map[Char, Int]()

    for (c <- example) {
      val current = chars.getOrElse(c, 0)
      chars += (c -> (current+1))
    }

    for (c <- target) {
      chars.get(c) match {
        case None =>
          return false

        case Some(value) =>
          if (value < 1) {
            return false
          } else {
            chars += (c -> (value-1))
          }
      }
    }

    true
  }
}
