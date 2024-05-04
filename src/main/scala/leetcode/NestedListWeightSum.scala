package leetcode

/**
  * // This is the interface that allows for creating nested lists.
  * // You should not implement it, or speculate about its implementation
  * trait NestedInteger {
  *
  *   // Return true if this NestedInteger holds a single integer, rather than a nested list.
  *   def isInteger: Boolean
  *
  *   // Return the single integer that this NestedInteger holds, if it holds a single integer.
  *   def getInteger: Int
  *
  *   // Set this NestedInteger to hold a single integer.
  *   def setInteger(i: Int): Unit
  *
  *   // Return the nested list that this NestedInteger holds, if it holds a nested list.
  *   def getList: Array[NestedInteger]
  *
  *   // Set this NestedInteger to hold a nested list and adds a nested integer to it.
  *   def add(ni: NestedInteger): Unit
  * }
  */
object Solution {
  def depthSum(nestedList: List[NestedInteger]): Int = {
    nestedList
      .map(depthSumHelper(_, 1))
      .sum
  }

  def depthSumHelper(nestedInteger: NestedInteger, depth: Int): Int = {
    if (nestedInteger.isInteger) {
      val number = nestedInteger.getInteger
      number * depth
    } else {
      nestedInteger.getList
        .map(depthSumHelper(_, depth + 1))
        .sum
    }
  }
}
