package leetcode

import scala.collection.mutable
import scala.util.Random

object InsertDeleteGetRandom {
  class RandomizedSet() {
    private val present = mutable.Map[Int, Boolean]()
    def insert(`val`: Int): Boolean = {
      present.get(`val`) match {
        case Some(_) => false
        case _ =>
          present += (`val` -> true)
          true
      }
    }

    def remove(`val`: Int): Boolean = {
      present.get(`val`) match {
        case Some(_) =>
          present -= `val`
          true
        case _ => false
      }
    }

    def getRandom(): Int = {
      val set = present.keys.toArray
      val i = Random.nextInt(set.size)
      set(i)
    }

  }
}
