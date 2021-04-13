package arrays;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/first-missing-positive/
 *
 * Given an unsorted integer array nums, find the smallest missing positive integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 300
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * Follow up: Could you implement an algorithm that runs in O(n) time and uses constant extra space?
 */
public class FirstMissingPositive {

  public static void main(String[] args) {
    FirstMissingPositive o = new FirstMissingPositive();
    System.out.println(o.firstMissingPositive(new int[]{1,1}));
    System.out.println(o.firstMissingPositive(new int[]{1,2,0}));
    System.out.println(o.firstMissingPositive(new int[]{-1,-4,3,1}));
    System.out.println(o.firstMissingPositive(new int[]{3,4,-1,1}));
    System.out.println(o.firstMissingPositive(new int[]{7,8,9,11,12}));
  }

  public int firstMissingPositive_another(int[] nums) {
    int result = 1;
    Arrays.sort(nums);

    for(int i = 0; i < nums.length; i++) {
      if(nums[i] > result){
        return result;
      } else if(nums[i] == result) {
        result++;
      }
    }

    return result;
  }

  public int firstMissingPositive(int[] nums) {
    int splitPoint = segregate(nums);
    if (splitPoint+1 == nums.length) {
      return 1;
    } else {
      int newSize = nums.length - splitPoint - 1;
      int[] arr = new int[newSize];
      for (int i = splitPoint+1, j = 0; i < nums.length; i++, j++) {
        arr[j] = nums[i];
      }
      for (int i = 0; i < arr.length; i++) {
        int x = Math.abs(arr[i]);
        if (x <= newSize && arr[x-1] > 0) {
          arr[x-1] = -arr[x-1];
        }
      }
      for (int i = 0; i < newSize; i++) {
        if (arr[i] > 0) {
          return i+1;
        }
      }
      return newSize + 1;
    }
  }

  private int segregate(int[] nums) {
    int j = -1;
    int temp;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] <= 0) {
        j++;
        temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
      }
    }
    return j;
  }
}
