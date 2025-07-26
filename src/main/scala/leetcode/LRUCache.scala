import scala.collection.mutable

class LRUCache(_capacity: Int) {
  val entries = mutable.Map[Int, (Int, Int)]()
  val used = mutable.ArrayBuffer[(Int, Int)]()

  def get(key: Int): Int = {
    entries.get(key) match {
      case Some(tuple) =>
        val value = tuple._1
        val counter = tuple._2 + 1
        used.addOne((key, counter))
        entries.update(key, (value, counter))
        value

      case None => -1
    }
  }

  def put(key: Int, value: Int): Unit = {
    entries.get(key) match {
      case Some((_, currentCounter)) =>
        val newCounter = currentCounter + 1
        entries.update(key, (value, newCounter))
        used.addOne((key, newCounter))

      case None =>
        entries.addOne(key, (value, 0))
        used.addOne((key, 0))

        if (entries.size > _capacity) {
          var i = 0
          var condition = false
          while (!condition && i < entries.size) {
            val (storedKey, storedCounter) = used(i)
            if (entries
                  .get(storedKey)
                  .exists(tuple => tuple._2 == storedCounter)) {
              condition = true
            } else {
              i += 1
            }
          }
          val dropKey = used(i)._1
          entries.remove(dropKey)
          used.dropInPlace(i + 1)
        }
    }
  }
}
