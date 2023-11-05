package leetcode

object FindKClosestElements {
  def findClosestElements(arr: Array[Int], k: Int, x: Int): List[Int] = {

    val elements = arr.map(getElement(_, x))

    elements.sortInPlaceWith(compare)

    elements.view.take(k).map(_.a).toList.sorted
  }

  case class Element(a: Int, value: Int)

  implicit def compare(first: Element, second: Element): Boolean = {
    if (first.value == second.value) {
      first.a < second.a
    } else {
      first.value < second.value
    }
  }

  def getElement(a: Int, x: Int): Element = {
    Element(a, Math.abs(a - x))
  }
}
