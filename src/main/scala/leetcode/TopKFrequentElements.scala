import scala.collection.mutable

object Solution {
  class Node(var value: Int,
             var parentOption: Option[Node] = None,
             var leftOption: Option[Node] = None,
             var rightOption: Option[Node] = None) {
    var amount = 1

    def isLeaf = leftOption.isEmpty && rightOption.isEmpty
  }

  class Heap(k: Int) {
    var nodeOption: Option[Node] = None
    var nodesByKey = Map[Int, Node]()
    var nodesByCounter = Map[Int, Node]()
    var counter = 1

    def isEmpty = nodeOption.isEmpty

    def nonEmpty = !isEmpty

    def increment(key: Int): Unit = {
      nodesByKey.get(key) match {
        case Some(node) =>
          node.amount += 1
          checkForSwap(node)

        case None =>
          val node = new Node(key)
          nodesByKey += (key -> node)
          nodesByCounter += (counter -> node)
          addNode(node)
      }
    }

    def pop(): Option[Int] = {
      nodeOption map { head =>
        val pop = head.value
        counter -= 1

        if (head.isLeaf) {
          nodeOption = None
          nodesByKey -= head.value
          nodesByCounter -= counter
        } else {
          swapWithLast(head)
          bubbleDown(head)
        }

        pop
      }
    }

    def bubbleDown(node: Node): Unit = {
      if (!node.isLeaf) {
        val childOption =
          (node.leftOption, node.rightOption) match {
            case (Some(leftChild), Some(rightChild)) =>
              if (leftChild.amount > rightChild.amount) {
                Some(leftChild)
              } else {
                Some(rightChild)
              }

            case (Some(leftChild), None) =>
              Some(leftChild)

            case (None, Some(rightChild)) =>
              Some(rightChild)

            case _ => None
          }

        childOption foreach { child =>
          if (child.amount > node.amount) {
            nodesByKey += (child.value -> node)
            nodesByKey += (node.value -> child)

            println(s"bubble down parent: ${node.value} with child ${child.value}")

            val tempValue = child.value
            val tempAmount = child.amount
            child.value = node.value
            child.amount = node.amount
            node.value = tempValue
            node.amount = tempAmount

            bubbleDown(child)
          }
        }
      }
    }

    def swapWithLast(root: Node): Unit = {
      val parentCounter = counter / 2

      for {
        last <- nodesByCounter.get(counter)
        lastParent <- nodesByCounter.get(parentCounter)
      } {
        nodesByKey += (last.value -> root)
        nodesByKey -= root.value

        println(s"swapped with last: ${last.value} with root: ${root.value}")

        root.amount = last.amount
        root.value = last.value

        if (counter % 2 == 0) {
          lastParent.leftOption = None
        } else {
          lastParent.rightOption = None
        }
        nodesByCounter -= counter
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
          println(s"Swapping parent: ${parentNode.value} with child ${node.value}")
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
  }

  def topKFrequent(nums: Array[Int], k: Int): Array[Int] = {
    val heap = new Heap(k)
    val result = mutable.ArrayBuffer[Int]()

    for (number <- nums) {
      heap.increment(number)
    }

    while (heap.nonEmpty && result.length < k) {
      heap.pop().foreach(result.addOne)
    }

    result.toArray
  }
}
