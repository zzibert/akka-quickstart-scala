import scala.collection.mutable

class Node(var _value: Int) {
  var value: Int = _value
  var neighbors: List[Node] = List()
}

object Solution {
  def cloneGraph(graph: Node): Node = {
    val nodes = mutable.Map[Int, Node]()

    if (graph == null) {
      return null
    }

    cloneGraphHelper(graph, nodes)
  }

  def cloneGraphHelper(root: Node, nodes: mutable.Map[Int, Node]): Node = {
    nodes.get(root.value) match {
      case Some(node) =>
        node

      case None =>
        val newNode = new Node(root.value)
        nodes.update(root.value, newNode)
        val neighbors = root.neighbors.map(n => cloneGraphHelper(n, nodes))
        newNode.neighbors = neighbors
        newNode
    }
  }
}
