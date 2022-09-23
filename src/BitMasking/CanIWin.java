package BitMasking;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 8/1/2021  7:11 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/problems/can-i-win/
 *
 * In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10.
 * The player who first causes the running total to reach or exceed 100 wins.
 *
 * What if we change the game so that players cannot re-use integers?
 *
 * For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until they reach a total >= 100.
 *
 * Given two integers maxChoosableInteger and desiredTotal, return true if the first player to move can force a win, otherwise, return false. Assume both players play optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: maxChoosableInteger = 10, desiredTotal = 11
 * Output: false
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 * Example 2:
 *
 * Input: maxChoosableInteger = 10, desiredTotal = 0
 * Output: true
 * Example 3:
 *
 * Input: maxChoosableInteger = 10, desiredTotal = 1
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= maxChoosableInteger <= 20
 * 0 <= desiredTotal <= 300
 */
public class CanIWin {

  public static void main(String[] args) {
    CanIWin o = new CanIWin();
    System.out.println(o.canIWin_faster(2,3));
    System.out.println(o.canIWin_faster(4,7));
    System.out.println(o.canIWin_self(10,11));
  }

  public boolean canIWin_faster(int maxChoosableInteger, int desiredTotal) {
    if (maxChoosableInteger >= desiredTotal) {
      return true;
    }
    int sum = maxChoosableInteger * (maxChoosableInteger + 1) >> 1;
    if (desiredTotal > sum) {
      return false;
    }
    if (desiredTotal == sum) {
      return (maxChoosableInteger & 1) == 1;
    }
    if ((maxChoosableInteger & 1) == 0 && (desiredTotal % (maxChoosableInteger + 1) == 0)) {
      return false;
    }
    return dfs(new boolean[maxChoosableInteger + 1], 0, 0, desiredTotal, new Boolean[1 << maxChoosableInteger]);
  }

  private boolean dfs(boolean[] visited, int sum, int flag, int desiredTotal, Boolean[] memo) {
    if (sum >= desiredTotal) {
      return false;
    }
    if (memo[flag] != null) {
      return memo[flag];
    }
    int maxChoosableInteger = visited.length - 1;
    for (int i = 1; i <= maxChoosableInteger; i++) {
      if (!visited[i]) {
        visited[i] = true;
        if (!dfs(visited, sum + i, flag | (1 << (i - 1)), desiredTotal, memo)) {
          memo[flag] = true;
          visited[i] = false;
          return true;
        }
        visited[i] = false;
      }
    }
    memo[flag] = false;
    return false;
  }


  boolean canIWin_self(int maxChoosableInteger, int desiredTotal) {

    if (maxChoosableInteger < 1) {
      return false;
    }
    if (desiredTotal == 0) {
      return true;
    }
    // sum of all integers should be >= total for any player to win
    if ((maxChoosableInteger * (maxChoosableInteger + 1) / 2) < desiredTotal) {
      return false;
    }

    int key = 1 << (maxChoosableInteger+1);
    Boolean[] mem = new Boolean[key << 1];
    return canIWin(maxChoosableInteger, desiredTotal, key, mem, true);
  }

  private boolean canIWin(int max, int target, int key, Boolean[] mem, boolean player1) {
    if (target <= 0) {
      return !player1;
    }
    if (mem[key] != null) {
      return mem[key];
    }
    if (player1) {
      for (int i = 1; i <= max; i++) {
        int mask = 1 << i;
        if ((key & mask) == mask) {
          continue;
        }
        if (canIWin(max, target - i, key | mask, mem, false)) {
          return mem[key] = true;
        }
      }
      return mem[key] = false;
    } else {
      for (int i = 1; i <= max; i++) {
        int mask = 1 << i;
        if ((key & mask) == mask) {
          continue;
        }
        boolean ans = canIWin(max, target - i, key | mask, mem, true);
        if (!ans) {
          mem[key] = false;
          return false;
        }
      }
      return mem[key] = true;
    }

  }

  Boolean[] memo;

  public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    if (desiredTotal == 0) {
      return true;
    }
    // sum of all integers should be >= total for any player to win
    if ((maxChoosableInteger * (maxChoosableInteger + 1) / 2) < desiredTotal) {
      return false;
    }

    int key = 1 << (maxChoosableInteger + 1);
    memo = new Boolean[key << 1];
    return canWin(maxChoosableInteger, desiredTotal, key, 0);
  }

  public boolean canWin(int max, int target, int key, int player) {
    if (target <= 0) {
      return player == 1; // player at is 1, that means player 0 already won, return true
    }
    if (memo[key] != null) {
      return memo[key];
    }
    if (player == 0) {
      for (int i = 1; i <= max; i++) {
        int mask = 1 << i;
        if (mask == (mask & key)) {
          continue;
        }
        // if player 0 wins here that means player 1 lost in all cases in next turn
        if (canWin(max, target - i, key | mask, 1)) {
          return memo[key] = true;
        }
      }
      return memo[key] = false;
    } else {
      boolean ans = true;
      for (int i = 1; i <= max; i++) {
        int mask = 1 << i;
        if (mask == (mask & key)) {
          continue;
        }
        // if player 0 wins in all cases in next step, than player 0 has forced his/her win in previous move
        ans = ans && canWin(max, target - i, key | mask, 0);
      }
      return memo[key] = ans;
    }
  }
}
