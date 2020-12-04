package arrays;

import java.util.Arrays;

/**
 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.
 *
 * Follow up: Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,3,2,5]
 * Output: [3,5]
 * Explanation:  [5, 3] is also a valid answer.
 * Example 2:
 *
 * Input: nums = [-1,0]
 * Output: [-1,0]
 * Example 3:
 *
 * Input: nums = [0,1]
 * Output: [1,0]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 30000
 *  Each integer in nums will appear twice, only two integers will appear once.
 */
public class SingleNumberIII {

  public static void main(String[] args) {
    SingleNumberIII o = new SingleNumberIII();
    System.out.println(Arrays.toString(o.singleNumber(new int[]{1,2,1,3,2,5})));
    System.out.println(Arrays.toString(o.singleNumber(new int[]{-1,0})));
    System.out.println(Arrays.toString(o.singleNumber(new int[]{0,1})));
  }
  public int[] singleNumber(int[] nums) {
    int c = 0;
    for (int num : nums) {
      c ^= num;
    }
    // get the lowest "1" bit of C
    c &= -c;
    // rets[0], rets[1] would be the XOR results of Group A and Group B
    int[] rets = new int[2];
    for (int num : nums) {
      if ((num & c) == 0) {
        rets[0] ^= num;
      }
      else {
        rets[1] ^= num;
      }
    }
    return rets;
  }
}
