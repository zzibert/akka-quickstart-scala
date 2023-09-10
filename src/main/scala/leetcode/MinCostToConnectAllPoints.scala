package leetcode

import scala.collection.mutable.ListBuffer

object MinCostToConnectAllPoints {
  def minCostConnectPoints(points: Array[Array[Int]]): Int = {
    val connected = Array.fill(points.length)(false)
    val distances = ListBuffer[(Int, Int, Int)]()
    val edges = points.length-1
    var numberOfEdges = 0
    var weight = 0

    if (points.length == 1) {
      return 0
    }

    for {
      i <- 0 until points.length
      j <- i+1 until points.length
    } {
      val result = distance(points(i), points(j))
      distances.addOne((result, i, j))
    }

    val sortedDistances = distances.sortBy(_._1)

    // connect first two edges
    val firstEdge = sortedDistances.head
    weight += firstEdge._1
    numberOfEdges += 1
    connected(firstEdge._2) = true
    connected(firstEdge._3) = true

    sortedDistances.dropInPlace(1)

    while (numberOfEdges < edges) {
      val edge = sortedDistances.head
      if (canBeConnected(edge, connected)) {
        weight += edge._1
        numberOfEdges += 1
        connected(edge._2) = true
        connected(edge._3) = true
      }

      sortedDistances.dropInPlace(1)
    }

    weight
  }

  def canBeConnected(point: (Int, Int, Int), connected: Array[Boolean]): Boolean = {
    if (connected(point._2) && !connected(point._3)) {
      true
    } else if (!connected(point._2) && connected(point._3)) {
      true
    } else {
      false
    }
  }

  def distance(point1: Array[Int], point2: Array[Int]): Int = {
    Math.abs(point1(0) - point2(0)) + Math.abs(point1(1) - point2(1))
  }
}
