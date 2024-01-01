package leetcode

import scala.collection.mutable

object AllNodesDistanceKinBinaryTree {
  def distanceK(root: TreeNode, target: TreeNode, k: Int): List[Int] = {
    val result = mutable.ListBuffer[Int]()
    val checked = mutable.Map[Int, Boolean]()
    val parentMap = mutable.Map[Int, Option[Int]]()
    val nodesMap = mutable.Map[Int, TreeNode]()

    fillMaps(root, None, parentMap, nodesMap)

    distanceKHelper(target, k, result, checked, parentMap, nodesMap)


    result.toList
  }

  def distanceKHelper(root: TreeNode, k: Int, result: mutable.ListBuffer[Int], checked: mutable.Map[Int, Boolean], parentMap: mutable.Map[Int, Option[Int]], nodes: mutable.Map[Int, TreeNode]): Unit = {
    if (root != null && !checked.getOrElse(root.value, false)) {
      checked.update(root.value, true)
      if (k == 0) {
        result.addOne(root.value)
      } else {
        for {
          parentOption <- parentMap.get(root.value)
          parent <- parentOption
          parentNode <- nodes.get(parent)
        } {
          distanceKHelper(parentNode, k-1, result, checked, parentMap, nodes)
        }

        distanceKHelper(root.left, k-1, result, checked, parentMap, nodes)
        distanceKHelper(root.right, k-1, result, checked, parentMap, nodes)
      }
    }
  }

  def fillMaps(root: TreeNode, parent: Option[Int], parentMap: mutable.Map[Int, Option[Int]], nodesMap: mutable.Map[Int, TreeNode]): Unit = {
    if (root != null) {
      parentMap.update(root.value, parent)
      nodesMap.update(root.value, root)

      fillMaps(root.left, Some(root.value), parentMap, nodesMap)
      fillMaps(root.right, Some(root.value), parentMap, nodesMap)
    }
  }
}
