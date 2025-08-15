package leetcode

object Solution {
  def groupAnagrams(strs: Array[String]): List[List[String]] = {
    var wordMap = Map[String, List[String]]()
    for (word <- strs) {
      val ordered = word.sorted
      val words = word :: wordMap.get(ordered).getOrElse(List.empty)
      wordMap += (ordered -> words)
    }

    wordMap.values.toList
  }
}
