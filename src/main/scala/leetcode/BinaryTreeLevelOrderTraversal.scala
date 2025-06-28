import scala.collection.mutable

class TreeNode(
    _value: Int = 0,
    _left: TreeNode = null,
    _right: TreeNode = null
) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object Solution {
  def levelOrder(root: TreeNode): List[List[Int]] = {
    val queue = mutable.Queue[TreeNode]()
    var result = List[List[Int]]()

    if (root != null) {
      queue.enqueue(root)
    }

    while (queue.nonEmpty) {
      val length = queue.length
      var level = List[Int]()

      for (_ <- 0 until length) {
        val node = queue.dequeue()
        level = node.value :: level
        if (node.right != null) {
          queue.enqueue(node.right)
        }
        if (node.left != null) {
          queue.enqueue(node.left)
        }
      }
      result = result ::: List(level)
    }

    result
  }
}
