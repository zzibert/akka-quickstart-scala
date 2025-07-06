object Solution {
  def maxArea(height: Array[Int]): Int = {
    var maxWater = 0
    var currentHeight = 0
    var i = 0
    while (i < height.length) {
      val leftIndex = i
      val leftHeight = height(i)
      var rightIndex = i + 1

      if (leftHeight > currentHeight) {
        currentHeight = leftHeight
        while (rightIndex < height.length) {
          val rightHeight = height(rightIndex)
          val yAxis = Math.min(leftHeight, rightHeight)
          val waterAmount = yAxis * (rightIndex - leftIndex)
          if (waterAmount > maxWater) {
            maxWater = waterAmount
          }
          rightIndex += 1
        }
      } else {
        i += 1
      }
    }

    maxWater
  }
}
