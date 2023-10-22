//package leetcode
//
//
//
//class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
//  var value: Int = _value
//  var left: TreeNode = _left
//  var right: TreeNode = _right
//}
//
//object DiameterOfBinaryTree {
//  def diameterOfBinaryTree(root: TreeNode): Int = {
//    markDepth(root)
//    findMaxValue(root)
//  }
//
//  def findMaxValue(root: TreeNode): Int = {
//    if (root == null) {
//      0
//    } else {
//      root.value max findMaxValue(root.left) max findMaxValue(root.right)
//    }
//  }
//
//  def markDepth(root: TreeNode): Int = {
//    if (root == null) {
//      0
//    } else {
//      val depthLeft = markDepth(root.left)
//      val depthRight = markDepth(root.right)
//
//      root.value = depthLeft + depthRight
//      (depthLeft max depthRight) + 1
//    }
//  }
//
//}
