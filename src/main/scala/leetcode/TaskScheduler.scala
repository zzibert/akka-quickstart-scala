package leetcode

object TaskScheduler {
  def leastInterval(tasks: Array[Char], n: Int): Int = {
    leastIntervalHelper(tasks, List[Char](), n, 0)
  }

  def leastIntervalHelper(tasks: Array[Char], used: List[Char], cooldown: Int, unit: Int): Int = {
    if (tasks.length == 0) {
      return unit
    }

    val result = tasks.fi
  }
}
