import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Solution {
  def coinChange(coins: Array[Int], amount: Int): Int = {
    val currentBest: mutable.ListBuffer[Int] = ListBuffer.empty

  }

  def coinChangeHelper(
      amount: Int,
      iterations: Int,
      coins: Array[Int],
      currentBest: ListBuffer[Int],
  ): Int = {
    if (amount == 0) {
      currentBest.headOption match {
        case Some(current) =>
          if (current > iterations) {
            currentBest = ListBuffer(iterations)
          }
      }
    }
  }
}
