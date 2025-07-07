import scala.collection.mutable

object Solution {
  def combinationSum(candidates: Array[Int], target: Int): List[List[Int]] = {
    val solutions = mutable.Map[(Int, List[Int]), Boolean]()
    combinationSumHelper(candidates, target, List.empty, solutions).toList
  }

  def combinationSumHelper(
      candidates: Array[Int],
      target: Int,
      current: List[Int],
      solutions: mutable.Map[(Int, List[Int]), Boolean],
  ): Set[List[Int]] = {
    solutions.addOne((target, current), true)
    if (target == 0) {
      Set(current)
    } else if (target > 0) {
      val result =
        for {
          i <- 0 until candidates.length
        } yield {
          val number = candidates(i)
          val candidate = (number :: current).sorted
          if (solutions.get(target - number, candidate).isEmpty) {
            combinationSumHelper(
              candidates = candidates,
              target = target - number,
              current = candidate,
              solutions = solutions
            )
          } else {
            List.empty
          }
        }
      result.flatten.toSet
    } else {
      Set.empty
    }
  }
}
