import scala.collection.mutable

object Solution {
  def maxArea(height: Array[Int]): Int = {
    val candidates = mutable.ArrayBuffer[(Int, Int)]()
    var maxWater = 0
    var leftPointer = 0
    var leftValue = 0

    for (i <- 1 until height.length) {
      candidates.addOne((height(i), i))
    }

    while (leftPointer < height.length) {
      if (leftValue < height(leftPointer)) {
        leftValue = height(leftPointer)
        var i = 0
        while (i < candidates.length) {
          val (rightValue, rightPointer) = candidates(i)
          val water = calculateWater(leftPointer, rightPointer, leftValue, rightValue)
          if (maxWater <= water) {
            i += 1
            maxWater = water
          } else {
            candidates.remove(i)
          }
        }
      }
      leftPointer += 1
    }

    maxWater
  }

  def calculateWater(
      leftIndex: Int,
      rightIndex: Int,
      leftValue: Int,
      rightValue: Int
  ): Int =
    (rightIndex - leftIndex) * Math.min(leftValue, rightValue)
}
