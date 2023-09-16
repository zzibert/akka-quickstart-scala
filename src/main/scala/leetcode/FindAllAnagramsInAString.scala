package leetcode

import scala.collection.mutable

object FindAllAnagramsInAString {
  def findAnagrams(s: String, p: String): List[Int] = {
    val anagramLenght = p.length
    val sortedAnagram = Array.from(p).sorted
    val length = s.length - anagramLenght
    val result = mutable.ListBuffer[Int]()

    for (i <- 0 to length) {
      if (sortedAnagram.contains(s(i))) {
        val candidate = Array.from(s.drop(i).take(anagramLenght)).sorted
        if (isAnagram(candidate, sortedAnagram)) {
          result.addOne(i)
        }
      }
    }

    result.toList
  }

  def isAnagram(candidate: Array[Char], p: Array[Char]): Boolean = {
    for (i <- 0 until candidate.length) {
      if (candidate(i) != p(i)) {
        return false
      }
    }

    true
  }
}
