package leetcode

import scala.collection.mutable

class FileSystem() {
    val zeroNode = new FileNode(_path = "")
  class FileNode(
      _path: String,
      _content: String = "",
      _children: mutable.ArrayBuffer[FileNode] = mutable.ArrayBuffer[FileNode]()
      ) {
    private var path: String = _path
    private var content: String = _content
    private val children: mutable.ArrayBuffer[FileNode] = _children

    def isDirectory = content.isEmpty
    def isFile = !isDirectory

    def concatenate(newContent: String): Unit = {
      content += newContent
    }

    def ls(path: Array[String]): List[String] = {
      if (path.length > 0) {
        children.find(_.path == path.head) match {
          case Some(child) =>
            child.ls(path.tail)
          case None =>
            val node = new FileNode(_path = path.head)
            children.addOne(node)
            node.ls(path.tail)
        }
      } else {
        if (isDirectory) {
          children.view.map(_.path).sorted.toList
        } else {
          List(_path)
        }
      }
    }

    def readContentFromFile(path: Array[String]): String = {
      if (path.length > 0) {
        children.find(_.path == path.head) match {
          case Some(child) =>
            child.readContentFromFile(path.tail)
          case None =>
            ""
        }
      } else {
        content
      }
    }

    def addChild(path: Array[String], content: String = ""): Unit = {
      if (path.length > 1) {
        children.find(_.path == path.head) match {
          case Some(child) =>
            child.addChild(path.tail, content)

          case None =>
            val node = new FileNode(_path = path.head)
            children.addOne(node)
            node.addChild(path.tail, content)

        }
      } else {
        children.find(_.path == path.head) match {
          case Some(child) =>
            if (child.isFile) {
              child.concatenate(content)
            }
          case None =>
            val node = new FileNode(_path = path.head, _content = content)
            children.addOne(node)
        }
      }
    }
  }

  def ls(path: String): List[String] = {
    val chunks = path.split("/")
    if (chunks.length > 0) {
      zeroNode.ls(chunks.tail)
    } else {
      zeroNode.ls(chunks)
    }
  }

  def mkdir(path: String): Unit = {
    val chunks = path.split("/")
    if (chunks.length > 0) {
      zeroNode.addChild(chunks.tail)
    }
  }

  def addContentToFile(filePath: String, content: String): Unit = {
    val chunks = filePath.split("/")
    if (chunks.length > 0) {
      zeroNode.addChild(chunks.tail, content)
    }
  }

  def readContentFromFile(filePath: String): String = {
    val chunks = filePath.split("/")
    if (chunks.length > 0) {
      zeroNode.readContentFromFile(chunks.tail)
    } else {
      ""
    }
  }

}
