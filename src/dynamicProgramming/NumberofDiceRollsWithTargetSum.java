package dynamicProgramming;

/**
 * You have n dice and each die has k faces numbered from 1 to k.
 *
 * Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1, k = 6, target = 3
 * Output: 1
 * Explanation: You throw one die with 6 faces.
 * There is only one way to get a sum of 3.
 * Example 2:
 *
 * Input: n = 2, k = 6, target = 7
 * Output: 6
 * Explanation: You throw two dice, each with 6 faces.
 * There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
 * Example 3:
 *
 * Input: n = 30, k = 30, target = 500
 * Output: 222616187
 * Explanation: The answer must be returned modulo 109 + 7.
 *
 *
 * Constraints:
 *
 * 1 <= n, k <= 30
 * 1 <= target <= 1000
 */
public class NumberofDiceRollsWithTargetSum {

    int mod = (int)1e9+7;

    public int numRollsToTarget(int n, int k, int target) {

        int[][] dp = new int[target+1][n+1];
        int ans = helper(n, k, target, dp);
        return ans == -1 ? 0 : ans;
    }

    private int helper(int n, int k, int target, int[][] dp) {


        if (target == 0 && n == 0) {
            return 1;
        }
        else if (target <= 0 || n == 0 || dp[target][n] == -1) {
            return 0;
        }

        if (dp[target][n] != 0) {
            return dp[target][n];
        }

        int res = 0;
        for (int i = 1; i <= k; i++) {
            res = (res + helper(n-1, k, target-i, dp)) % mod;
        }

        dp[target][n] = res == 0 ? -1 : res;
        return res;
    }
}
