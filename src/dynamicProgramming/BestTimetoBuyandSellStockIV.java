package dynamicProgramming;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/20/2021  12:36 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;

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
    System.out.println(o.maxProfit(2, new int[]{2,5,4,0,4,0,7}));
    System.out.println(o.maxProfit(2, new int[]{3,2,6,5,0,3}));
  }

  public int maxProfit(int k, int[] prices) {
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
}
