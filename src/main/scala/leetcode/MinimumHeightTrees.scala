import scala.collection.mutable

object Solution {
  def findMinHeightTrees(n: Int, edges: Array[Array[Int]]): List[Int] = {
    val numberOfEdgesByNode = Array.fill(n)(0)
    val roots = Array.fill(n)(1)
    val neighborsByNode = Array.fill(n)(List[Int]())
    val queue = mutable.Queue[Int]()

    edges foreach {
      case Array(left, right) =>
        numberOfEdgesByNode(left) += 1
        numberOfEdgesByNode(right) += 1

        neighborsByNode(left) = right :: neighborsByNode(left)
        neighborsByNode(right) = left :: neighborsByNode(right)
    }

    while (!isComplete(numberOfEdgesByNode)) {
      numberOfEdgesByNode.zipWithIndex foreach {
        case (edges, node) =>
          if (edges == 1) {
            queue.enqueue(node)
          }
      }

      while (queue.nonEmpty) {
        val node = queue.dequeue()
        neighborsByNode(node) foreach { neighbor =>
          numberOfEdgesByNode(neighbor) -= 1
          numberOfEdgesByNode(node) -= 1
        }

        neighborsByNode(node) = List.empty
        roots(node) = 0
      }
    }

    (roots.zipWithIndex collect {
      case (number, index) if number == 1 => index
    }).toList
  }

  def isComplete(numberOfEdgesByNode: Array[Int]): Boolean = {
    numberOfEdgesByNode.forall(_ < 2)
  }
}
