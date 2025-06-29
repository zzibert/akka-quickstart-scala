import scala.collection.mutable

class MinStack() {
  var currentMinOption: Option[Int] = None
  val stack = mutable.Stack[(Int, Int)]()

  def push(`val`: Int): Unit = {
    currentMinOption match {
      case Some(currentMin) =>
        if (currentMin > `val`) {
          currentMinOption = Some(`val`)
          stack.push((`val`, `val`))
        } else {
          stack.push((`val`, currentMin))
        }

      case None =>
        currentMinOption = Some(`val`)
        stack.push((`val`, `val`))
    }
  }

  def pop(): Unit = {
    stack.pop()
    if (stack.nonEmpty) {
      val (_, currentMin) = stack.top
      currentMinOption = Some(currentMin)
    } else {
      currentMinOption = None
    }
  }

  def top(): Int = {
    val (value, _) = stack.top
    value
  }

  def getMin(): Int = {
    currentMinOption.get
  }
}
