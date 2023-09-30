package leetcode

object MergeSortedArray {
  def merge(nums1: Array[Int], m: Int, nums2: Array[Int], n: Int): Unit = {
    var i = 0
    var j = 0

    while (i < m && j < n) {
      if (nums1(i) > nums2(j)) {
        val temp = nums1(i)
        nums1(i) = nums2(j)
        nums2(j) = temp
        nums2.sortInPlace()
        i += 1
      } else {
        i += 1
      }
    }

    while (i < nums1.length && j < nums2.length) {
      nums1(i) = nums2(j)
      i += 1
      j += 1
    }
  }
}
