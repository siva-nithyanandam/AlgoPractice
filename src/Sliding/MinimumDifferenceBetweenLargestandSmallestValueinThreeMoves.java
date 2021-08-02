package Sliding;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/20/2021  7:22 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/
 *
 * Given an array nums, you are allowed to choose one element of nums and change it by any value in one move.
 *
 * Return the minimum difference between the largest and smallest value of nums after perfoming at most 3 moves.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,3,2,4]
 * Output: 0
 * Explanation: Change the array [5,3,2,4] to [2,2,2,2].
 * The difference between the maximum and minimum is 2-2 = 0.
 * Example 2:
 *
 * Input: nums = [1,5,0,10,14]
 * Output: 1
 * Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1].
 * The difference between the maximum and minimum is 1-0 = 1.
 * Example 3:
 *
 * Input: nums = [6,6,0,1,1,4,6]
 * Output: 2
 * Example 4:
 *
 * Input: nums = [1,5,6,14,15]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
/**
 * Idea:
 * Case 1: Update only 3 min, 0 max numbers
 * Case 2: Update 2 min, 1 max numbers
 * Case 2: Update 1 min, 2 max numbers
 * Case 2: Update 0 min, 3 max numbers
 */
public class MinimumDifferenceBetweenLargestandSmallestValueinThreeMoves {

  public static void main(String[] args) {
    MinimumDifferenceBetweenLargestandSmallestValueinThreeMoves o = new MinimumDifferenceBetweenLargestandSmallestValueinThreeMoves();
    System.out.println(o.minDifference(new int[]{-1,-1,3,4,5,8}));
  }

  public int minDifference(int[] nums) {
    int n = nums.length;
    if (n <= 4) {
      return 0;
    }
    Arrays.sort(nums);
    int res = Integer.MAX_VALUE;
    int interval = n - 4;

    for (int i = 0; i + interval < n; i++) {
      res = Math.min(nums[i + interval] - nums[i], res);
    }
    return res;
  }
}
