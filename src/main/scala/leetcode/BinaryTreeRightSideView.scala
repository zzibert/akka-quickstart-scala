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
  def rightSideView(root: TreeNode): List[Int] = {
    val queue = mutable.Queue[TreeNode]()
    val result = mutable.ListBuffer[Int]()

    queue.enqueue(root)

    while (queue.nonEmpty) {
      val size = queue.size
      var level = List[Int]()
      for (_ <- 0 until size) {
        val node = queue.dequeue()
        if (node != null) {
          level = node.value :: level
          queue.enqueue(node.left)
          queue.enqueue(node.right)
        }
      }
      level.headOption.foreach(result.addOne)
    }

    result.toList
  }
}
