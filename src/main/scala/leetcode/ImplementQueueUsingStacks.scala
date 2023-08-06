package leetcode

import scala.collection.mutable

class ImplementQueueUsingStacks {
  class MyQueue() {
    val pushStack = mutable.Stack[Int]()
    val popStack = mutable.Stack[Int]()

    def push(x: Int) {
      pushStack.push(x)
    }

    def pop(): Int = {
      if (popStack.isEmpty) {
        while (pushStack.nonEmpty) {
          val element = pushStack.pop()
          popStack.push(element)
        }
        popStack.pop()
      } else {
        popStack.pop()
      }
    }

    def peek(): Int = {
      if (popStack.isEmpty) {
        while (pushStack.nonEmpty) {
          val element = pushStack.pop()
          popStack.push(element)
        }
        popStack.head
      } else {
        popStack.head
      }
    }

    def empty(): Boolean = {
      pushStack.isEmpty && popStack.isEmpty
    }

  }
}
