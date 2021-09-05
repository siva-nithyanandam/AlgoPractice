package StacksAndQueues;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/21/2021  6:07 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;

/**
 * https://leetcode.com/problems/last-stone-weight-ii/description/
 *
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 *
 * We are playing a game with the stones. On each turn, we choose any two stones and smash them together. Suppose the stones have weights x and y with x <= y. The result of this smash is:
 *
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 * At the end of the game, there is at most one stone left.
 *
 * Return the smallest possible weight of the left stone. If there are no stones left, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
 * we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
 * we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
 * we can combine 1 and 1 to get 0, so the array converts to [1], then that's the optimal value.
 * Example 2:
 *
 * Input: stones = [31,26,33,21,40]
 * Output: 5
 * Example 3:
 *
 * Input: stones = [1,2]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 */
//Knap Sack Algorithm
public class LastStoneWeightII {

  public static void main(String[] args) {
    LastStoneWeightII o = new LastStoneWeightII();
    System.out.println(o.lastStoneWeightII_self(new int[]{2,7,4,1,8,1}));
    System.out.println(o.lastStoneWeightII_1ms(new int[]{31,26,33,21,40}));
    System.out.println(o.lastStoneWeightII_1ms(new int[]{1,2}));
    System.out.println(o.lastStoneWeightII_1ms(new int[]{1,2}));
  }

  public int lastStoneWeightII_1ms(int[] stones) {
    int sum=0, n=stones.length;

    for (int stone: stones) {
      sum+=stone;
    }

    int half=sum/2;

    boolean[] dp = new boolean[half+1];
    dp[0]=true;

    for (int stone: stones) {
      for (int i=half; i>=stone; i--) {
        dp[i]|=dp[i-stone];
      }
    }

    int s2=0;
    for (int i=half; i>=0; i--) {
      if (dp[i]) {
        s2=i;
        break;
      }
    }

    return sum-s2*2;
  }

  public int lastStoneWeightII_self(int[] stones) {
    int n = stones.length;
    if (n == 2) {
      return Math.abs(stones[0] - stones[1]);
    }
    int totalWt = 0;
    Arrays.sort(stones);
    for (int stone : stones) {
      totalWt += stone;
    }
    int maxWt =(totalWt/2) + 1;
    int[][] ks = new int[n+1][maxWt];

    for (int i = 1; i <= n; i++) {
      for (int j = stones[i-1]; j < maxWt; j++) {
        ks[i][j] = Math.max(ks[i-1][j], stones[i-1] + ks[i-1][j - stones[i-1]]);
      }
    }
    return totalWt - ks[n][maxWt-1] * 2;
  }

  public int lastStoneWeightII(int[] stones) {
    Arrays.sort(stones);
    int S = 0;
    for (int stone : stones)
      S += stone;

    int n = stones.length;
    int[][] dp = new int[n+1][S/2+1];

    for (int i = 1; i <= n; i++)
      for (int j = 1; j <= S/2; j++) {
        if (stones[i-1] <= j)
          dp[i][j] = Math.max(dp[i-1][j], stones[i-1] + dp[i-1][j-stones[i-1]]);
        else
          dp[i][j] = dp[i-1][j];
      }
    return S - 2 * dp[n][S/2];
  }
}
