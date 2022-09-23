package dynamicProgramming;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/23/2021  12:48 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
 *
 * You are given two integer arrays of the same length nums1 and nums2. In one operation, you are allowed to swap nums1[i] with nums2[i].
 *
 * For example, if nums1 = [1,2,3,8], and nums2 = [5,6,7,4], you can swap the element at i = 3 to obtain nums1 = [1,2,3,4] and nums2 = [5,6,7,8].
 * Return the minimum number of needed operations to make nums1 and nums2 strictly increasing. The test cases are generated so that the given input always makes it possible.
 *
 * An array arr is strictly increasing if and only if arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1].
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
 * Output: 1
 * Explanation:
 * Swap nums1[3] and nums2[3]. Then the sequences are:
 * nums1 = [1, 3, 5, 7] and nums2 = [1, 2, 3, 4]
 * which are both strictly increasing.
 * Example 2:
 *
 * Input: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 2 <= nums1.length <= 105
 * nums2.length == nums1.length
 * 0 <= nums1[i], nums2[i] <= 2 * 105
 */
public class MinimumSwapsToMakeSequencesIncreasing {

  public static void main(String[] args) {
    MinimumSwapsToMakeSequencesIncreasing o = new MinimumSwapsToMakeSequencesIncreasing();
    System.out.println(o.minSwap(new int[]{1,3,5,4}, new int[]{1,2,3,7}));
  }

  public int minSwap(int[] nums1, int[] nums2) {
    return Math.min(recurse(nums1, nums2, nums1.length - 1, 0),
        recurse(nums1, nums2, nums1.length - 1, 1));
  }

  private int recurse(int[] nums1, int[] nums2, int i, int swap) {
    // base case
    if (i == 0) {
      return swap;
    }

    // default is set as max
    int res = Integer.MAX_VALUE;

    // if array is increasing without swapping
    if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
      res = recurse(nums1, nums2, i - 1, swap);
    }

    // if array is increasing with swapping
    if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
      res = Math.min(res,
          recurse(nums1, nums2, i - 1, 1 - swap));
    }

    return swap == 0 ? res : res + 1;
  }
}
