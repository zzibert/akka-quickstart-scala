import scala.collection.mutable

object Solution {
  class Node(var value: Int,
             var parentOption: Option[Node] = None,
             var leftOption: Option[Node] = None,
             var rightOption: Option[Node] = None) {
    var amount = 1
  }

  class Heap(k: Int) {
    var nodeOption: Option[Node] = None
    var nodesByKey = Map[Int, Node]()
    var nodesByCounter = Map[Int, Node]()
    var counter = 1

    def increment(key: Int): Unit = {
      nodesByKey.get(key) match {
        case Some(node) =>
          node.amount += 1
          checkForSwap(node)
          checkForChildRotation(node)

        case None =>
          val node = new Node(key)
          nodesByKey += (key -> node)
          nodesByCounter += (counter -> node)
          addNode(node)
      }
    }

    def addNode(node: Node): Unit = {
      if (counter == 1) {
        nodeOption = Some(node)
      } else {
        val parent = counter / 2
        nodesByCounter.get(parent).foreach { parentNode =>
          if (counter % 2 == 0) {
            parentNode.leftOption = Some(node)
          } else {
            parentNode.rightOption = Some(node)
          }
          node.parentOption = Some(parentNode)
        }
      }

      counter += 1
    }

    def checkForSwap(node: Node): Unit = {
      node.parentOption foreach { parentNode =>
        if (parentNode.amount < node.amount) {
          nodesByKey += (node.value -> parentNode)
          nodesByKey += (parentNode.value -> node)

          val tempAmount = parentNode.amount
          val tempValue = parentNode.value

          parentNode.amount = node.amount
          parentNode.value = node.value

          node.amount = tempAmount
          node.value = tempValue

          checkForSwap(parentNode)
        }
      }
    }

    def checkForChildRotation(node: Node): Unit = {
      node.parentOption foreach { parentNode =>
        (parentNode.leftOption, parentNode.rightOption) match {
          case (Some(leftChild), Some(rightChild)) =>
            if (rightChild.amount > leftChild.amount) {
              parentNode.rightOption = Some(leftChild)
              parentNode.leftOption = Some(rightChild)
            }

          case _ =>
        }

        checkForChildRotation(parentNode)
      }
    }

    def getTopKElements(): Array[Int] = {
      val result = mutable.ArrayBuffer[Int]()
      val queue = mutable.Queue[Node]()

      nodeOption foreach { node =>
        queue.enqueue(node)
      }

      while (queue.nonEmpty && result.length < k) {
        val node = queue.dequeue()
        result.addOne(node.value)
        node.leftOption.foreach(queue.enqueue)
        node.rightOption.foreach(queue.enqueue)
      }

      result.toArray
    }
  }

  def topKFrequent(nums: Array[Int], k: Int): Array[Int] = {
    val heap = new Heap(k)

    for (number <- nums) {
      heap.increment(number)
    }

    heap.getTopKElements()
  }

}
