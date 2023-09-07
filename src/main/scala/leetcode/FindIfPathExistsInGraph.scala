package leetcode

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object FindIfPathExistsInGraph {
  def validPath(n: Int, edges: Array[Array[Int]], source: Int, destination: Int): Boolean = {
    if (source == destination) {
      return true
    }

    val paths = mutable.Map[Int, ArrayBuffer[Int]]()
    val visited = mutable.Map[Int, Boolean]()

    edges foreach { edge =>
      val buffer1 = paths.getOrElseUpdate(edge(0), ArrayBuffer[Int]())
      buffer1.addOne(edge(1))

      val buffer2 = paths.getOrElseUpdate(edge(1), ArrayBuffer[Int]())
      buffer2.addOne(edge(0))
    }

    pathFinder(source, destination, paths, visited)
  }

  def pathFinder(node: Int, destination: Int, paths: mutable.Map[Int, ArrayBuffer[Int]], visited: mutable.Map[Int, Boolean]): Boolean = {
    if (node == destination) {
      true
    } else {
      visited += (node -> true)
      val potentials = paths.getOrElse(node, ArrayBuffer.empty).filterNot(visited.getOrElse(_, false))

      potentials foreach { potential =>
        if (pathFinder(potential, destination, paths, visited)) {
          return true
        }
      }

      false
    }
  }
}
