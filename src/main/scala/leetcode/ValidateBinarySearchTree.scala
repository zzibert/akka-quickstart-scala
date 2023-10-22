//package leetcode
//
//import scala.collection.mutable
//
//class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
//  var value: Int = _value
//  var left: TreeNode = _left
//  var right: TreeNode = _right
//}
//
//object ValidateBinarySearchTree {
//  def isValidBST(root: TreeNode): Boolean = {
//
//    val seen = mutable.Map[Int, Boolean]()
//    isValidBSTHelper(root,maxValue = Int.MaxValue, minValue = Int.MinValue, seen)
//  }
//
//  def isValidBSTHelper(root: TreeNode, maxValue: Int, minValue: Int, seen: mutable.Map[Int, Boolean]): Boolean = {
//    if (root == null) {
//      return true
//    }
//
//    if (root.value < minValue || root.value > maxValue || seen.getOrElseUpdate(root.value, false)) {
//      return false
//    }
//
//    seen += (root.value -> true)
//
//    val leftMin = {
//      if (root.value == Int.MinValue) {
//        Int.MinValue
//      } else if (root.value < minValue) {
//        root.value - 1
//      } else {
//        minValue
//      }
//    }
//
//    val leftMax =
//      if (root.value == Int.MinValue) {
//        Int.MinValue
//      } else if (root.value < maxValue) {
//        root.value - 1
//      } else {
//        maxValue
//      }
//
//    val rightMin =
//      if (root.value == Int.MaxValue) {
//        Int.MaxValue
//      } else if (root.value > minValue) {
//        root.value + 1
//      } else {
//        minValue
//      }
//
//    val rightMax =
//      if (root.value == Int.MaxValue) {
//        Int.MaxValue
//      } else if (root.value > maxValue) {
//        root.value + 1
//      } else {
//        maxValue
//      }
//
//    isValidBSTHelper(root.left, leftMax, leftMin, seen) && isValidBSTHelper(root.right, rightMax, rightMin, seen)
//  }
//
//}
