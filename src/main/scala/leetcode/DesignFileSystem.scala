package leetcode

import scala.collection.mutable

object DesignFileSystem {
  class FileSystem() {

    val zeroNode = new PathNode("", -1)

    class PathNode(_path: String, _value: Int) {
      val children = mutable.ArrayBuffer[PathNode]()

      def path: String = _path

      def value: Int = _value

      def insert(path: Array[String], value: Int): Boolean = {
        if (path.length == 1) {
          if (!children.exists(_.path == path.head)) {
            val newNode = new PathNode(path.head, value)
            children.addOne(newNode)
            true
          } else {
            false
          }
        } else {
          val head = path.head
          children.find(_.path == head) match {
            case Some(next) =>
              next.insert(path.tail, value)
            case None =>
              false
          }
        }
      }

      def find(path: Array[String]): Int = {
        if (path.length == 0) {
          _value
        } else {
          val head = path.head
          children.find(_.path == head) match {
            case Some(next) =>
              next.find(path.tail)
            case None =>
              -1
          }
        }
      }

    }

    def createPath(path: String, value: Int): Boolean = {
      val chunks = path.split('/').tail
      zeroNode.insert(chunks, value)
    }

    def get(path: String): Int = {
      val chunks = path.split('/').tail
      zeroNode.find(chunks)
    }

  }
}
