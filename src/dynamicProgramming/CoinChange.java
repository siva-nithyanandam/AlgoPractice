package dynamicProgramming;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/14/2021  11:01 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;

/**
 *
 */
public class CoinChange {

  public static void main(String[] args) {
    CoinChange o = new CoinChange();
    System.out.println(o.coinChange_faster(new int[]{5, 4, 1}, 13));
    System.out.println(o.coinChange(new int[]{5, 4, 1}, 13));
    System.out.println(o.coinChange(new int[]{186, 419, 83, 408}, 6249));
    System.out.println(o.coinChange_bottom_up(new int[]{186, 419, 83, 408}, 6249));
    System.out.println(o.coinChange_bottom_up(new int[]{1, 2, 5}, 11));
  }


  public int coinChange_faster(int[] coins, int amount) {
    int n = coins.length;
    // int[][] f = new int[n + 1][amount + 1];
    int[] f = new int[amount + 1];
    for (int i = 1; i <= amount; i++) {
      f[i] = amount + 1;
    }

    for (int coin : coins){
      for (int j = coin; j <= amount; j++) {
        f[j] = Math.min(f[j], f[j - coin] + 1);
      }
    }

    // return f[n][amount] > amount ? -1 : f[n][amount];
    return f[amount] > amount ? -1 : f[amount];
  }

  public int coinChange_bottom_up(int[] coins, int amount) {
    int max = amount + 1;
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, max);
    dp[0] = 0;
    for (int i = 1; i <= amount; i++) {
      for (int j = 0; j < coins.length; j++) {
        if (coins[j] <= i) {
          dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
        }
      }
    }
    return dp[amount] > amount ? -1 : dp[amount];
  }

  public int coinChange(int[] coins, int amount) {
    if (amount < 1) {
      return 0;
    }
    return coinChange(coins, amount, new int[amount]);
  }

  private int coinChange(int[] coins, int rem, int[] count) {
    if (rem < 0) {
      return -1;
    }
    if (rem == 0) {
      return 0;
    }
    if (count[rem - 1] != 0) {
      return count[rem - 1];
    }
    int min = Integer.MAX_VALUE;
    for (int coin : coins) {
      int res = coinChange(coins, rem - coin, count);
      if (res >= 0 && res < min) {
        min = 1 + res;
      }
    }
    count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
    return count[rem - 1];
  }
}
