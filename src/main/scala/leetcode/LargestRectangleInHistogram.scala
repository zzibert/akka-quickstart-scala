package leetcode

object Solution {
  def largestRectangleArea(heights: Array[Int]): Int = {
    var result = 0
    heights.zipWithIndex foreach { case (height, index) =>
      var currentValue = height
      // Left Side
      var leftIndex = index - 1
      while (leftIndex >= 0 && heights(leftIndex) >= height) {
        currentValue += height
        leftIndex -= 1
      }

      // Right Side
      var rightIndex = index + 1
      while (rightIndex < heights.length && heights(rightIndex) >= height) {
        currentValue += height
        rightIndex += 1
      }

      if (currentValue > result) {
        result = currentValue
      }
    }

    result
  }
}
