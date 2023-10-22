package leetcode

class TrappingRainWater {
  def trap(height: Array[Int]): Int = {
    val length = height.length
    var result = 0
    var left = 0

    while (left < length) {
      if (height(left) > 0) {
        val right = findRight(left, height)
        if (right != -1) {
          result += calculateWater(left, right, height)
          left = right
        } else {
          height(left) -= 1
        }
      } else {
        left += 1
      }
    }

    result
  }

  def findRight(left: Int, heights: Array[Int]): Int = {
    var index = -1
    var biggest = 0
    val leftHeight = heights(left)
    for (i <- left+1 until heights.length) {
      if (leftHeight <= heights(i)) {
        return i
      } else if (biggest < heights(i)) {
        biggest = heights(i)
        index = i
      }
    }

    index
  }

  def calculateWater(left: Int, right: Int, heights: Array[Int]): Int = {
    var result = 0
    val height = Math.min(heights(left), heights(right))

    for (i <- left+1 to right-1) {
      result += height - heights(i)
    }

    result
  }


}
