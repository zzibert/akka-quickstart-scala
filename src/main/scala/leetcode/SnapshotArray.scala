package leetcode

class SnapshotArray(_length: Int) {
  var _snapId = -2
  var snapshots = Map[Int, Array[(Int, Int)]]()
  val current = Array.fill(_length)(0)
  val dirty = Array.fill(_length)(true)

  def set(index: Int, `val`: Int) {
    current(index) = `val`
    dirty(index) = true
  }

  snap()

  def snap(): Int = {
    _snapId += 1
    dirty
      .view
      .zipWithIndex
      .foreach {
      case (isDirty, index) =>
        if (isDirty) {
          var buffer = snapshots.getOrElse(index, Array[(Int, Int)]())
          buffer = buffer :+ (_snapId, current(index))
          snapshots += (index -> buffer)
          dirty(index) = false
        }
    }

    _snapId
  }

  def get(index: Int, snap_id: Int): Int = {
    val buffer = snapshots.getOrElse(index, Array[(Int, Int)]())

    binarySearch(snap_id, buffer)
  }

  def binarySearch(snapId: Int, buffer: Array[(Int, Int)]): Int = {
    if (buffer.length < 2) {
      buffer(0)._2
    } else if (buffer.length < 3) {
      val entry1 = buffer(0)
      val entry2 = buffer(1)
      if (entry2._1 <= snapId) {
        entry2._2
      } else {
        entry1._2
      }
    } else {
      val half = buffer.length / 2
      val middle = buffer(half)
      if (middle._1 == snapId) {
        middle._2
      } else if (middle._1 < snapId) {
        binarySearch(snapId, buffer.drop(half))
      } else {
        binarySearch(snapId, buffer.take(half))
      }
    }
  }

}
