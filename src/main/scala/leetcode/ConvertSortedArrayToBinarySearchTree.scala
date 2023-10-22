//package leetcode
//
//class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
//  var value: Int = _value
//  var left: TreeNode = _left
//  var right: TreeNode = _right
//}
//
//object ConvertSortedArrayToBinarySearchTree {
//  def sortedArrayToBST(nums: Array[Int]): TreeNode = {
//    if (nums.length == 0) {
//      null
//    } else if (nums.length == 1) {
//      new TreeNode(_value = nums(0))
//    } else if (nums.length == 2) {
//      val root = new TreeNode(nums(1))
//      val left = new TreeNode(nums(0))
//      root.left = left
//      root
//    } else {
//      val middle = nums.length / 2
//      val root = new TreeNode(nums(middle))
//      root.left = sortedArrayToBST(nums.take(middle-1))
//      root.right = sortedArrayToBST(nums.drop(middle+1))
//      root
//    }
//  }
//
//}
