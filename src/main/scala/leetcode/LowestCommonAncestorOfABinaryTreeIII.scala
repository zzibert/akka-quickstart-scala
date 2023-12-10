package leetcode

import scala.collection.mutable

object LowestCommonAncestorOfABinaryTreeIII {

  class Node(var _value: Int) {
    var value: Int = _value
    var left: Node = null
    var right: Node = null
    var parent: Node = null
  }
  def lowestCommonAncestor(p: Node, q: Node): Node = {
    var pParent = p
    var qParent = q

    val visited = mutable.Map[Int, Boolean]()

    while (pParent != null) {
      visited.update(pParent.value, true)
      pParent = pParent.parent
    }

    while (qParent.parent != null) {
      if (visited.getOrElse(qParent.value, false)) {
        return qParent
      }

      qParent = qParent.parent
    }

    qParent
  }
}
