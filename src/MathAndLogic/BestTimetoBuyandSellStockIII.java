package MathAndLogic;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/16/2021  7:19 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * Example 4:
 *
 * Input: prices = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 */
public class BestTimetoBuyandSellStockIII {

  public static void main(String[] args) {
    BestTimetoBuyandSellStockIII o = new BestTimetoBuyandSellStockIII();
    System.out.println(o.maxProfit(new int[]{1,2,4,2,5,7,2,4,9,0}));
  }

  public int maxProfit(int[] prices) {

    int t1Cost = Integer.MAX_VALUE;
    int t1Profit = 0;
    int t2Cost = Integer.MAX_VALUE;
    int t2Profit = 0;

    for (int price : prices) {
      // Buy on minimum
      t1Cost = Math.min(t1Cost, price);

      //When there is a difference between current price and buying price, see profit
      t1Profit = Math.max(t1Profit, price - t1Cost);

      //When you expect stock down, sell t1 on high profit and buy again on low price
      t2Cost = Math.min(t2Cost, price - t1Profit);

      //Like t1Profit
      t2Profit = Math.max(t2Profit, price - t2Cost);
    }
    return t2Profit;
  }
}
