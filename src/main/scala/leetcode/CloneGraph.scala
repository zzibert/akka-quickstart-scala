package leetcode


import scala.collection.mutable

class Node(var _value: Int) {

  var value: Int = _value

  var neighbors: List[Node] = List()
}

object CloneGraph {
  def cloneGraph(graph: Node): Node = {
    if (graph == null) {
      return graph
    }

    val seen = mutable.Map[Int, Node]()

    cloneGraphHelper(graph, seen)
  }

  def cloneGraphHelper(graph: Node, seen: mutable.Map[Int, Node]): Node = {
    seen.get(graph.value) match {
      case Some(node) =>
        node
      case None =>
        val newGraph = new Node(graph.value)
        seen += (graph.value -> newGraph)

        newGraph.neighbors = graph.neighbors
          .map(node => cloneGraphHelper(node, seen))

        newGraph
    }
  }
}
