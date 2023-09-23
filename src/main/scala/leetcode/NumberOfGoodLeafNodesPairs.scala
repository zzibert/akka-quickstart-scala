package leetcode

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * Definition for a binary tree node.
 * class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
 *   var value: Int = _value
 *   var left: TreeNode = _left
 *   var right: TreeNode = _right
 * }
 */

object NumberOfGoodLeafNodesPairs {
  def countPairs(root: TreeNode, distance: Int): Int = {
    val leafs = ArrayBuffer[TreeNode]()
    val pairs = mutable.Map[(TreeNode, TreeNode), Boolean]()

    findLeafs(root, leafs)

    countPairsHelper(root, leafs, pairs, distance)

    pairs.size
  }

  def countPairsHelper(root: TreeNode, leafs: ArrayBuffer[TreeNode], pairs: mutable.Map[(TreeNode, TreeNode), Boolean], maxDistance: Int): Array[(TreeNode, Int)] = {
    if (root == null) {
      Array.empty
    } else if (leafs.contains(root)) {
      Array((root, 1))
    } else {
      val leftChildren = countPairsHelper(root.left, leafs, pairs, maxDistance)
      val rightChildren = countPairsHelper(root.right, leafs, pairs, maxDistance)

      for {
        leftChild <- leftChildren
        rightChild <- rightChildren
      } {
        if (leftChild._2 + rightChild._2 <= maxDistance) {
          pairs += (leftChild._1, rightChild._1) -> true
        }
      }

      val result =
        (leftChildren ++ rightChildren) map {
        case (number, distance) => (number, distance+1)
      }

      result.filter(child => child._2 < maxDistance)
    }
  }

  def findLeafs(root: TreeNode, leafs: mutable.ArrayBuffer[TreeNode]): Unit = {
    if (root != null) {
      if (isLeaf(root)) {
        leafs.addOne(root)
      } else {
        findLeafs(root.left, leafs)
        findLeafs(root.right, leafs)
      }
    }
  }

  def isLeaf(root: TreeNode): Boolean = {
    root.left == null && root.right == null
  }
}
