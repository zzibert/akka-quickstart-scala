package leetcode

import scala.collection.mutable

object DesignFileSystem {
  class FileSystem() {
    val directory = mutable.Map[String, Int]()
    directory.addOne("", -1)

    def createPath(path: String, value: Int): Boolean = {
      if (directory.isDefinedAt(path)) {
        false
      } else if (isParentPresent(path)) {
        directory.addOne(path, value)
        true
      } else {
        false
      }
    }

    def get(path: String): Int = {
      directory.getOrElse(path, -1)
    }

    def isParentPresent(path: String): Boolean = {
      val chunks = path.split("/")
      val parent = chunks.take(chunks.length - 1).mkString("/")
      directory.isDefinedAt(parent)
    }

  }
}
