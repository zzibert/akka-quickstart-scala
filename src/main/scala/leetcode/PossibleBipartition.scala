package leetcode

import scala.collection.mutable

object Solution {
  def possibleBipartition(n: Int, dislikes: Array[Array[Int]]): Boolean = {
    val dislikesReversed = dislikes.map(_.reverse)
    val assignments = mutable.Map[Int, Int]()
    val queue = mutable.Queue[(Int, Int)]()
    val seen = mutable.Map[Int, Boolean]()
    var result = true

    val dislikeMap = (dislikes ++ dislikesReversed).groupBy(_(0)) collect {
      case (person, array) =>
        val disliked = array.map(_(1))
        (person, disliked)
    }

    for {
      person <- 1 to n
      if result
      if !seen.get(person).getOrElse(false)
    } {
      val assignment = assignments.get(person).getOrElse(0)
      seen += (person -> true)
      queue.enqueue((person, assignment))

      while (result && queue.nonEmpty) {
        val (current, currentAssignment) = queue.dequeue()
        assignments += (current -> currentAssignment)
        dislikeMap.get(current) match {
          case Some(disliked) =>
            val newAssignment = (currentAssignment + 1) % 2
            result = disliked.flatMap(assignments.get).forall(_ == newAssignment)
            disliked
              .filterNot(dislike => seen.get(dislike).getOrElse(false))
              .foreach { dislike =>
                seen.update(dislike, true)
                queue.enqueue((dislike, newAssignment))
              }
          case None =>
        }
      }
    }

    result
  }
}
