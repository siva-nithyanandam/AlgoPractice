package dynamicProgramming;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/20/2021  12:36 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 *
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 *
 * Find the maximum profit you can achieve. You may complete at most k transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2:
 *
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 *
 * Constraints:
 *
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */
public class BestTimetoBuyandSellStockIV {

  public static void main(String[] args) {
    BestTimetoBuyandSellStockIV o = new BestTimetoBuyandSellStockIV();
    System.out.println(o.maxProfit_bottom_up(2, new int[]{3,2,6,5,0,3}));
    System.out.println(o.maxProfit(2, new int[]{3,2,6,5,0,3}));
    System.out.println(o.maxProfit_top_down(2, Arrays.asList(2,5,4,0,4,0,7)));
    System.out.println(o.maxProfit_bottom_up(2, new int[]{2,5,4,0,4,0,7}));
  }

  public int maxProfit(int k, int[] prices) {

    int n = prices.length;
    int[][][] mem = new int[n+1][k+1][2];
    return dp(prices, 0, k, 1, mem);
  }

  private int dp(int[] prices, int p, int k, int isBuy, int[][][] mem) {
    if (p >= prices.length || (k == 0 && isBuy == 1)) {
      return 0;
    }

    if (mem[p][k][isBuy] == 0) {
      int next = dp(prices, p+1, k, isBuy, mem);
      int curr = (isBuy == 1 ? -1 : 1) * prices[p];
      int curr1 = dp(prices, p+1, k-isBuy, isBuy ^ 1, mem);
      int ans = Math.max(next, curr+curr1);
      mem[p][k][isBuy] = ans;
    }
    return mem[p][k][isBuy];
  }

  public int maxProfit_bottom_up(int k, int[] prices) {
    if (prices.length == 0 || k == 0) {
      return 0;
    }
    int res = 0;
    int n = prices.length;
    if (k > n/2) {
      for (int i = 0; i < n-1; i++) {
        if (prices[i] < prices[i+1]) {
          res += prices[i+1] - prices[i];
        }
      }
      return res;
    }

    int[] buys = new int[k];
    int[] sells = new int[k];
    Arrays.fill(buys, Integer.MIN_VALUE);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < k; j++) {
        buys[j] = Math.max(buys[j], j == 0 ? -prices[i] : sells[j-1] - prices[i]);
        sells[j] = Math.max(sells[j], buys[j] + prices[i]);
      }
    }
    return sells[k-1];
  }

  static int[][][] dp = new int[1000][101][2];

  public static int dfs(List<Integer> ps, int p, int k, boolean buy) {
    if (p >= ps.size() || (k == 0 && buy)) {
      return 0;
    }
    if (dp[p][k][buy ? 1 : 0] == 0) {

      dp[p][k][buy ? 1 : 0] = Math.max(dfs(ps, p + 1, k, buy),
              (buy ? -1 : 1) * ps.get(p) + dfs(ps, p + 1, k - (buy ? 1 : 0), !buy));
    }
    return dp[p][k][buy ? 1 : 0];
  }

  public int maxProfit_top_down(int k, List<Integer> ps) {
    if (k * 2 >= ps.size())
      return maxProfit2(ps);
    return dfs(ps, 0, k, true);
  }

  public int maxProfit2(List<Integer> ps) {
    int res = 0;
    for (int i = 1; i < ps.size(); ++i)
      res += Math.max(0, ps.get(i) - ps.get(i - 1));
    return res;
  }
}
