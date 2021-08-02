package dynamicProgramming;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/19/2021  11:10 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * Example 2:
 *
 * Input: prices = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */
public class BestTimetoBuyandSellStockwithCooldown {

  public static void main(String[] args) {
    BestTimetoBuyandSellStockwithCooldown o = new BestTimetoBuyandSellStockwithCooldown();
    System.out.println(o.maxProfit_0ms(new int[]{2,1,4,5,2,9,7}));
  }

  public int maxProfit_0ms(int[] prices) {
    int sold = 0, coolDown = 0, hold = -prices[0];
    int preSold;

    for (int i = 1; i < prices.length; i++) {
      preSold = sold;
      sold = hold + prices[i];
      hold = Math.max(hold, coolDown - prices[i]);
      coolDown = Math.max(coolDown, preSold);
    }
    return Math.max(coolDown, sold);
  }

  public int maxProfit_1ms(int[] prices) {
    int n = prices.length;

    if (n < 2) {
      return 0;
    }

    int[][] dp = new int[n][2];

    //First day - Sell
    dp[0][0] = 0;

    //First day - Buy
    dp[0][1] = -prices[0];

    //Second day - Sell
    dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);

    //Second day - Buy
    dp[1][1] = Math.max(dp[0][1], dp[0][0] - prices[1]);


    for (int i = 2; i < n; i++) {
      //Selling it
      dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);

      //Buying it
      dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);
    }
    return dp[n-1][0];
  }
}
