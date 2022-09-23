package dynamicProgramming;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/17/2021  12:09 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/split-array-largest-sum/
 *
 * Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
 *
 * Write an algorithm to minimize the largest sum among these m subarrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [7,2,5,10,8], m = 2
 * Output: 18
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 * Example 2:
 *
 * Input: nums = [1,2,3,4,5], m = 2
 * Output: 9
 * Example 3:
 *
 * Input: nums = [1,4,4], m = 3
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 106
 * 1 <= m <= min(50, nums.length)
 */
public class SplitArrayLargestSum {

  public static void main(String[] args) {
    SplitArrayLargestSum o = new SplitArrayLargestSum();
    System.out.println(o.splitArray(new int[]{7,2,5,10,8}, 2));
    System.out.println(o.splitArray(new int[]{7,2,5,10,8}, 1));
  }

  public int splitArray(int[] nums, int m) {
    int low = IntStream.of(nums).max().orElse(0);
    int high = IntStream.of(nums).sum();
    while (low < high) {
      int mid = low + (high - low) / 2;
      int totalPartitions = split(nums, mid);
      if (totalPartitions > m) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }
    return low;
  }

  private int split(int[] nums, int sum) {
    int ret = 1;
    int currentSum = 0;
    for (int i = 0; i < nums.length; i++) {
      if (currentSum + nums[i] > sum) {
        ret++;
        currentSum = 0;
      }
      currentSum += nums[i];
    }
    return ret;
  }
}
