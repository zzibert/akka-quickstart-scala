package leetcode

object ContainerWithMostWater {
  def maxArea(height: Array[Int]): Int = {
    var first = 0
    var second = 1

    var tallestFirst = 0
    var maxAmount = 0

    for {
      i <- 0 until (height.length-1)
    } {
      if (height(i) > tallestFirst) {
        tallestFirst = height(i)
        for (j <- i+1 until height.length) {
          val localHight = Math.min(tallestFirst, height(j))
          val localSum = localHight * (j - i)
          if (localSum > maxAmount) {
            maxAmount = localSum
          }
        }
      }
    }

    maxAmount
  }
}
