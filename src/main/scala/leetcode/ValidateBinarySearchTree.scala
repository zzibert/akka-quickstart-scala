package leetcode

object Solution {
  def isValidBST(root: TreeNode): Boolean = {

    isValidBSTHelper(root, None, None)
  }

  def isValidBSTHelper(
      root: TreeNode,
     minOption: Option[Int],
     maxOption: Option[Int]
                      ): Boolean = {
    if (root == null) {
      true
    } else {
      if ((minOption.nonEmpty && root.value <= minOption.get) || (maxOption.nonEmpty && root.value >= maxOption.get)) {
        false
      } else {
        isValidBSTHelper(root.left, minOption, Some(root.value)) && isValidBSTHelper(
          root.right,
          Some(root.value),
          maxOption)
      }
    }
  }
}
