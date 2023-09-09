package leetcode

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object AllPathsFromSourceLeadToDestination {
  def leadsToDestination(n: Int, edges: Array[Array[Int]], source: Int, destination: Int): Boolean = {
    val paths = mutable.Map[Int, Array[Int]]()

    edges foreach { edge =>
      val buffer = paths.getOrElseUpdate(edge(0), Array[Int]())
      paths += (edge(0) -> (buffer :+ edge(1)))
    }

    pathTraversal(Array(source), destination, paths)
  }

  def pathTraversal(path: Array[Int], destination: Int, paths: mutable.Map[Int, Array[Int]]): Boolean = {
    val potentials = paths.getOrElse(path.last, Array.empty)

    if (potentials.isEmpty) {
      if (path.last == destination) {
        true
      } else {
        false
      }
    } else {
      if (potentials.exists(path.contains)) {
         false
      } else {
        potentials foreach { next =>
          if (!pathTraversal(path :+ next, destination, paths)) {
            return false
          }
        }

        true
      }
    }
  }

}
