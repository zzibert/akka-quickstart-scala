package leetcode

import scala.collection.mutable

object TopKFrequentElements {

  def topKFrequent(nums: Array[Int], k: Int): Array[Int] = {
    val values = mutable.Map[Int, Int]()

    nums foreach { number =>
      values.get(number) match {
        case Some(counter) =>
          values.update(number, counter + 1)

        case None =>
          values.update(number, 1)
      }
    }

    values.toArray.view
      .sortBy(-_._2)
      .take(k)
      .map(_._1)
      .toArray

  }
}
