package leetcode

import scala.collection.mutable

object SmallestStringWithSwaps {
  def smallestStringWithSwaps(s: String, pairs: List[List[Int]]): String = {
    val length = s.length
    val roots = Array.fill(length)(0)
    val result: Array[Char] = Array.fill(length)(' ')

    for (i <- 0 until length) {
      roots(i) = i
    }

    pairs foreach { pair =>
      val node0 = pair(0)
      val node1 = pair(1)

      if (roots(node0) != roots(node1)) {
        val newRoot = roots(node0)
        val oldRoot = roots(node1)

        for (i <- 0 until length) {
          if (roots(i) == oldRoot) {
            roots(i) = newRoot
          }
        }
      }
    }

    val groups = mutable.Map[Int, Array[Int]]()

    roots.zipWithIndex.view foreach {
      case (group, index) =>
        val buffer = groups.getOrElseUpdate(group, Array[Int]())
        groups(group) = buffer :+ index
    }

    groups.values.view foreach { indices =>
      val characters = indices.view.map(c => s(c)).sorted
      val sortedIndices = indices.sorted

      sortedIndices.view.zip(characters) foreach {
        case (i, c) =>
          result(i) = c
      }
    }

    String.valueOf(result)
  }
}
