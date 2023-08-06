package leetcode

object RomanToInt {
  def romanToInt(s: String): Int = {
    var amount = 0
    val length = s.length

    s.zipWithIndex foreach {
      case (c, i) =>
        c match {
          case 'I' =>
            if (i+1 < length) {
              if (s(i+1) == 'V' || s(i+1) == 'X') {
                amount -= 1
              } else {
                amount += 1
              }
            } else {
              amount += 1
            }
          case 'V' => amount += 5
          case 'X' =>
            if (i+1 < length) {
              if (s(i+1) == 'L' || s(i+1) == 'C') {
                amount -= 10
              } else {
                amount += 10
              }
            } else {
              amount += 10
            }
          case 'L' => amount += 50
          case 'C' =>
            if (i+1 < length) {
              if (s(i+1) == 'D' || s(i+1) == 'M') {
                amount -= 100
              } else {
                amount += 100
              }
            } else {
              amount += 100
            }
          case 'D' => amount += 500
          case 'M' => amount += 1000
        }
    }

    amount
  }
}
