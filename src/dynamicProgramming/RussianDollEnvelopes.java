package dynamicProgramming;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/24/2021  1:06 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/russian-doll-envelopes/
 *
 * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
 *
 * One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.
 *
 * Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
 *
 * Note: You cannot rotate an envelope.
 *
 *
 *
 * Example 1:
 *
 * Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 * Example 2:
 *
 * Input: envelopes = [[1,1],[1,1],[1,1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= envelopes.length <= 5000
 * envelopes[i].length == 2
 * 1 <= wi, hi <= 104
 */
public class RussianDollEnvelopes {

  public static void main(String[] args) {
    RussianDollEnvelopes o = new RussianDollEnvelopes();
    System.out.println(o.maxEnvelopes(new int[][] {{5,4},{6,5},{6,7},{2,3}}));
  }

  public int lengthOfLIS(int[] nums) {
    int[] dp = new int[nums.length];
    int len = 0;
    for (int num : nums) {
      int i = Arrays.binarySearch(dp, 0, len, num);
      if (i < 0) {
        i = -(i + 1);
      }
      dp[i] = num;
      if (i == len) {
        len++;
      }
    }
    return len;
  }

  public int maxEnvelopes(int[][] envelopes) {
    // sort on increasing in first dimension and decreasing in second
    Arrays.sort(envelopes, new Comparator<int[]>() {
      public int compare(int[] arr1, int[] arr2) {
        if (arr1[0] == arr2[0]) {
          return arr2[1] - arr1[1];
        } else {
          return arr1[0] - arr2[0];
        }
      }
    });
    // extract the second dimension and run LIS
    int[] secondDim = new int[envelopes.length];
    for (int i = 0; i < envelopes.length; ++i)
      secondDim[i] = envelopes[i][1];
    return lengthOfLIS(secondDim);
  }
}
