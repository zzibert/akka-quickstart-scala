object Solution {
  def isValidBST(root: TreeNode): Boolean = {
    isValidBSTHelper(root, None, None)
  }

  def isValidBSTHelper(root: TreeNode,
                       minOption: Option[Int],
                       maxOption: Option[Int]): Boolean = {
    if (root == null) {
      return true
    }

    if (minOption.exists(_ >= root.value)) {
      return false
    }

    if (maxOption.exists(_ <= root.value)) {
      return false
    }

    if (root.left != null) {
      if (root.left.value >= root.value) {
        return false
      }
    }

    if (root.right != null) {
      if (root.right.value <= root.value) {
        return false
      }
    }

    isValidBSTHelper(
      root.left,
      minOption = minOption,
      maxOption = Some(root.value)
    ) && isValidBSTHelper(root.right,
                          minOption = Some(root.value),
                          maxOption = maxOption)
  }
}
