package leetcode

def alert(a: Array[Int], b: Array[Int]): Unit = {
  val lengthA = a.length
  val lengthB = b.length
  var avgA = 0
  var avgB = 0
  val length = Math.min(lengthA, lengthB)

  var currentA = 0
  var currentB = 0

  val slidingWindowSizeA = Math.min(5, lengthA)
  val slidingWidnowSizeB = Math.min(5, lengthB)

  for {
    i <- 0 until slidingWindowSizeA
  } {
    currentA += a(i)
  }

  for {
    i <- 0 until slidingWidnowSizeB
  } {
    currentB += b(i)
  }

  for (i <- 0 until length-1) {
    avgA = currentA / slidingWindowSizeA
    avgB = currentB / slidingWidnowSizeB

    if (avgA > 80 && avgB > 80) {
      print("alert")
    }

    currentA -= a(i)
    currentA += a(i+slidingWindowSizeA)

    currentB -= b(i)
    currentB += b(i+slidingWidnowSizeB)
  }
}
