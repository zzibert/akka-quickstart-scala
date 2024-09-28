package leetcode

import scala.collection.mutable

class Node(var _value: Int) {
  var value: Int = _value
  var neighbors: List[Node] = List()
}

object Solution {
  def cloneGraph(graph: Node): Node = {
    val nodes = mutable.Map[Int, Node]()

    if (graph != null) {
      cloneGraphHelper(graph, nodes)
    } else {
      null
    }
  }

  def cloneGraphHelper(node: Node, nodes: mutable.Map[Int, Node]): Node = {
    nodes.get(node.value) match {
      case Some(found) => found

      case None =>
        val newNode = new Node(node.value)
        nodes.update(node.value, newNode)
        newNode.neighbors = node.neighbors.map(cloneGraphHelper(_, nodes))
        newNode
    }
  }
}
