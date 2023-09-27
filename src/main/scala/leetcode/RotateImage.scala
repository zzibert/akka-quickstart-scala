package leetcode

object RotateImage {
  def rotate(matrix: Array[Array[Int]]): Unit = {
    swapAxis(matrix)
    reverseRowOrder(matrix)
  }

  def swapAxis(matrix: Array[Array[Int]]): Unit = {
    val length = matrix.length

    for (i <- 0 until length) {
      for (j <- i+1 until length) {
        val temp = matrix(j)(i)
        matrix(j)(i) = matrix(i)(j)
        matrix(i)(j) = temp
      }
    }
  }

  def reverseRowOrder(matrix: Array[Array[Int]]): Unit = {
    val length = matrix.length

    for (i <- 0 until length) {
      for (j <- 0 until (length / 2)) {
        val temp = matrix(i)(j)
        matrix(i)(j) = matrix(i)(length-1-j)
        matrix(i)(length-1-j) = temp
      }
    }
  }
}
