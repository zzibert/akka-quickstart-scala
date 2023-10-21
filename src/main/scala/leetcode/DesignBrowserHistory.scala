package leetcode

import scala.collection.mutable

object DesignBrowserHistory {
  class BrowserHistory(_homepage: String) {
    private val backward = mutable.Stack[String]()
    private val forward = mutable.Stack[String]()
    private var current = _homepage

    def visit(url: String) {
      forward.clear()
      backward.push(current)
      current = url
    }

    def back(steps: Int): String = {
      var i = steps

      while (i > 0 && backward.nonEmpty) {
        forward.push(current)
        current = backward.pop()
        i -= 1
      }

      current
    }

    def forward(steps: Int): String = {
      var i = steps

      while (i > 0 && forward.nonEmpty) {
        backward.push(current)
        current = forward.pop()
        i -= 1
      }

      current
    }
  }
}
