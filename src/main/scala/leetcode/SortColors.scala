object Solution {
  def sortColors(nums: Array[Int]): Unit = {
    var redIndex = 0
    var whiteIndex = 0
    var blueIndex = 0

    var i = 0

    while (i < nums.length) {
      val number = nums(i)
      number match {
        case 0 =>
          if (i == redIndex) {
            redIndex += 1
            whiteIndex = redIndex + 1
            i += 1
          } else {
            val temp = nums(redIndex)
            nums(redIndex) = 0
            nums(i) = temp
            redIndex += 1
          }

        case 1 =>
          if (i == whiteIndex) {
            whiteIndex
          }

        case 2 =>
          i += 1

      }
    }
  }
}
