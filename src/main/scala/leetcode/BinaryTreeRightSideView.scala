import scala.collection.mutable

object Solution {
  def rightSideView(root: TreeNode): List[Int] = {
    val queue = mutable.Queue[TreeNode]()
    val result = mutable.ListBuffer[Int]()

    queue.enqueue(root)

    while (queue.nonEmpty) {
      val size = queue.size
      var level: Option[Int] = None
      for (_ <- 0 until size) {
        val node = queue.dequeue()
        if (node != null) {
          level = Some(node.value)
          queue.enqueue(node.left)
          queue.enqueue(node.right)
        }
      }
      level.foreach(result.addOne)
    }

    result.toList
  }
}
