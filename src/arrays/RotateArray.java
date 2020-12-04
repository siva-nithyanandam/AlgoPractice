package arrays;

import java.util.Arrays;
/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Follow up:
 *
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 *
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 */
public class RotateArray {

  public static void main(String[] args) {
    int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
    RotateArray o = new RotateArray();
    o.rotate(nums, 3);
    System.out.println(Arrays.toString(nums));

    nums = new int[]{-1,-100,3,99};
    o.rotate(nums, 2);
    System.out.println(Arrays.toString(nums));

    nums = new int[]{1, 2};
    o.rotate(nums, 1);
    System.out.println(Arrays.toString(nums));
  }
  public void rotate(int[] nums, int k) {
    int l = nums.length;
    int np = k % l;
    int a = np;
    if (np == 0) {
      return;
    }
    int oldVal = nums[0];
    int newVal;
    while (--l >= 0) {
      newVal = nums[np];
      nums[np] = oldVal;
      oldVal = newVal;
      np = (np + k) % nums.length;
      if (a == np) {
        oldVal = nums[(++np) % nums.length];
        np = (np + k) % nums.length;
      }
    }
  }
}
