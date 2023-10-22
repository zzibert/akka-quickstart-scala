//package leetcode
//
//class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
//  var value: Int = _value
//  var left: TreeNode = _left
//  var right: TreeNode = _right
//}
//
//class SameTree {
//  def isSameTree(p: TreeNode, q: TreeNode): Boolean = {
//    if (p == null && q == null) {
//      true
//    } else if (p == null || q == null) {
//      false
//    } else if (p.value != q.value) {
//      false
//    } else {
//      isSameTree(p.right, q.right) && isSameTree(p.left, q.left)
//    }
//  }
//}
