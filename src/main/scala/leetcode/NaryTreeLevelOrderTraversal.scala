//package leetcode
//
//import scala.collection.mutable
//import scala.collection.mutable.ListBuffer
//
//
//class Node(var _value: Int) {
//  var value: Int = _value
//  var children: List[Node] = List()
//}
//
//
//class NaryTreeLevelOrderTraversal {
//  def levelOrder(root: Node): List[List[Int]] = {
//    val result = mutable.Map[Int, ListBuffer[Int]]()
//    val queue = mutable.Queue[(Int, Node)]()
//
//    if (root == null) {
//      return Nil
//    }
//
//    queue.enqueue((0, root))
//
//    while (queue.nonEmpty) {
//      val node = queue.dequeue()
//      val level = node._1
//      val buffer = result.getOrElseUpdate(node._1, ListBuffer[Int]())
//      buffer.addOne(node._2.value)
//
//      node._2.children foreach { child =>
//        queue.enqueue((level+1, child))
//      }
//    }
//
//    result.toList.sortBy(_._1) map { keyValue =>
//      keyValue._2.toList
//    }
//  }
//}
