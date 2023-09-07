package leetcode

import scala.collection.mutable.ListBuffer

object AllPathsFromSourceToTarget {
  def allPathsSourceTarget(graph: Array[Array[Int]]): List[List[Int]] = {
    val result = ListBuffer[List[Int]]()

    pathFinder(Array(0), destination = graph.length-1, graph, result)

    result.toList
  }

  def pathFinder(path: Array[Int], destination: Int, graph: Array[Array[Int]], result: ListBuffer[List[Int]]): Unit = {
    if (path.last == destination) {
      result.addOne(path.toList)
    } else {
      val neighbors = graph(path.last)

      neighbors foreach { neighbor =>
        pathFinder(path.appended(neighbor), destination, graph, result)
      }
    }
  }
}
