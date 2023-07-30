package leetcode

object AddBinary {
  def addBinary(a: String, b: String): String = {
    val aReverse = a.reverse
    val bReverse = b.reverse

    var result = ""
    var increment = false

    aReverse.zipAll(bReverse, '0', '0') foreach { concat =>
      concat match {
        case ('1', '0') | ('0', '1') =>
          if (increment) {
            result += '0'
          } else {
            result += '1'
          }
        case ('1', '1') =>
          if (increment) {
            result += '1'
          } else {
            result += '0'
            increment = true
          }
        case ('0', '0') =>
          if (increment) {
            result += '1'
            increment = false
          } else {
            result += '0'
          }
      }
    }
    if (increment) {
      result += '1'
    }

    result.reverse
  }

}
