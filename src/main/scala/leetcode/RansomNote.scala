package leetcode

import scala.collection.mutable

object RansomNote {
  def canConstruct(ransomNote: String, magazine: String): Boolean = {
    val ransomMap = mutable.Map[Char, Int]()
    val magazineMap = mutable.Map[Char, Int]()

    for (c <- ransomNote) {
      ransomMap += (c -> (ransomMap.getOrElse(c, 0)+1) )
    }

    for (c <- magazine) {
      magazineMap += (c -> (magazineMap.getOrElse(c, 0)+1))
    }

    ransomMap foreach {
      case (letter, count) =>
        if (magazineMap.getOrElse(letter, 0) < count) {
          return false
        }
    }

    true
  }
}
