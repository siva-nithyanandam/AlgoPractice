package arrays;

import java.util.Arrays;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the
 * relative order of the non-zero elements.
 *
 * Example:
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 *
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZeroes {

  public static void main(String[] args) {
    MoveZeroes o = new MoveZeroes();
    System.out.println(Arrays.toString(o.moveZeroes(new int[]{0,0,1,2,3,4})));
    System.out.println(Arrays.toString(o.moveZeroes(new int[]{0,1,2,3,4})));
    System.out.println(Arrays.toString(o.moveZeroes(new int[]{1,2,3,4})));
    System.out.println(Arrays.toString(o.moveZeroes(new int[]{0,1,0,3,12})));
  }

  public int[] moveZeroes(int[] nums) {
    int k = 0, count = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        nums[k++] = nums[i];
        if (count > 0) {
          nums[i] = 0;
        }
      } else {
        count++;
      }
    }
    return nums;
  }
}
