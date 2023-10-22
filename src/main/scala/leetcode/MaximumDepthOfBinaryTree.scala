//package leetcode
//
//class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
//  var value: Int = _value
//  var left: TreeNode = _left
//  var right: TreeNode = _right
//}
//
//object MaximumDepthOfBinaryTree {
//  def maxDepth(root: TreeNode): Int = {
//    maxDepthHelper(root, 0)
//  }
//
//  def maxDepthHelper(root: TreeNode, depth: Int): Int = {
//    if (root == null) {
//      depth
//    } else {
//      maxDepthHelper(root.left, depth+1) max maxDepthHelper(root.right, depth+1)
//    }
//  }
//}
