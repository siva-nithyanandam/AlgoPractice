package arrays;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;

/**
 *
 * https://leetcode.com/problems/merge-sorted-array/
 *
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * The number of elements initialized in nums1 and nums2 are m and n respectively. You may assume that nums1 has a size equal to m + n such that it has enough space to hold additional elements from nums2.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * Example 2:
 *
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 *
 *
 * Constraints:
 *
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[i] <= 109
 */
public class MergeSortedArray {

  public static void main(String[] args) {
    MergeSortedArray o = new MergeSortedArray();
    int[] nums1 = new int[]{1,2,3,0,0,0};
    o.merge(nums1, 3, new int[]{2,5,6}, 3);
    nums1 = new int[]{2,0};
    o.merge(nums1, 1, new int[]{1}, 1);
    System.out.println(Arrays.toString(nums1));
  }

  public void merge(int[] nums1, int m, int[] nums2, int n) {
    if (n == 0) {
      return;
    }
    int x = m - 1;
    int y = n-1;
    int curr = nums1.length - 1;
    while (curr >= 0) {
      if (x < 0) {
        nums1[curr--] = nums2[y];
        y--;
      } else if (y < 0) {
        nums1[curr--] = nums1[x];
        x--;
      } else {
        if (nums1[x] > nums2[y]) {
          nums1[curr--] = nums1[x];
          x--;
        } else {
          nums1[curr--] = nums2[y];
          y--;
        }
      }
    }
  }
}
