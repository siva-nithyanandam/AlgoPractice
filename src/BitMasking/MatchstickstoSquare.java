package BitMasking;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 8/2/2021  4:37 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;
import java.util.HashMap;
//import javafx.util.Pair;

/**
 * https://leetcode.com/problems/matchsticks-to-square/
 *
 * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick.
 * You want to use all the matchsticks to make one square. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 *
 * Return true if you can make this square and false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matchsticks = [1,1,2,2,2]
 * Output: true
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 * Example 2:
 *
 * Input: matchsticks = [3,3,3,3,4]
 * Output: false
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 *
 *
 * Constraints:
 *
 * 1 <= matchsticks.length <= 15
 * 1 <= matchsticks[i] <= 108
 */
public class MatchstickstoSquare {

  public static void main(String[] args) {
    MatchstickstoSquare o = new MatchstickstoSquare();
    System.out.println(o.makesquare(new int[]{1,1,2,2,2}));
  }

  // The memoization cache to be used during recursion.
  /*public HashMap<Pair<Integer, Integer>, Boolean> memo;

  // Array containing our matchsticks.
  public int[] nums;

  // Possible side of our square depending on the total sum of all the matchsticks. 
  public int possibleSquareSide;

  // Default constructor to initialise our memo cache.
  public MatchstickstoSquare() {
    this.memo = new HashMap<Pair<Integer, Integer>, Boolean>();
  }

  // Main DP function.
  public boolean recurse(Integer mask, Integer sidesDone) {
    int total = 0;
    int L = this.nums.length;

    // The memo key for this recursion
    Pair<Integer, Integer> memoKey = new Pair(mask, sidesDone);

    // Find out the sum of matchsticks used till now.
    for(int i = L - 1; i >= 0; i--) {
      if ((mask&(1 << i)) == 0) {
        total += this.nums[L - 1 - i];
      }
    }

    // If the sum if divisible by our square's side, then we increment our number of complete sides formed variable.
    if (total > 0 && total % this.possibleSquareSide == 0) {
      sidesDone++;
    }

    // Base case.
    if (sidesDone == 3) {
      return true;
    }


    // Return precomputed results
    if (this.memo.containsKey(memoKey)) {
      return this.memo.get(memoKey);
    }

    boolean ans = false;
    int c = total / this.possibleSquareSide;

    // Remaining vlength in the current partially formed side.
    int rem = this.possibleSquareSide * (c + 1) - total;

    // Try out all remaining options (that are valid)
    for(int i = L - 1; i >= 0; i--) {
      if (this.nums[L - 1 - i] <= rem && (mask&(1 << i)) > 0) {
        if (this.recurse(mask ^ (1 << i), sidesDone)) {
          ans = true;
          break;
        }
      }
    }

    // Cache the computed results.
    this.memo.put(memoKey, ans);
    return ans;
  }*/

  /*public boolean makesquare_Bitmasking(int[] nums) {

    // Empty matchsticks.
    if (nums == null || nums.length == 0) {
      return false;
    }

    // Find the perimeter of the square (if at all possible)
    int L = nums.length;
    int perimeter = 0;
    for(int i = 0; i < L; i++) {
      perimeter += nums[i];
    }

    int possibleSquareSide =  perimeter / 4;
    if (possibleSquareSide * 4 != perimeter) {
      return false;
    }

    this.nums = nums;
    this.possibleSquareSide = possibleSquareSide;
    return this.recurse((1 << L) - 1, 0);
  }*/

  public boolean makesquare(int[] nums) {
    if (nums == null || nums.length < 4) {
      return false;
    }
    int n = nums.length;
    boolean[] dp = new boolean[1 << n];
    dp[0] = true;
    int[] total = new int[1 << n];
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    Arrays.sort(nums);
    if (sum % 4 != 0) {
      return false;
    }
    sum /= 4;
    if (nums[n - 1] > sum) {
      return false;
    }
    // Loop over power set
    for (int i = 0; i < 1 << n; i++) {
      if (dp[i]) {
        for (int j = 0; j < n; j++) {
          // Loop over each element to find subset
          int temp = i | (1 << j);
          if (temp != i) {
            // if total sum is less than target store in dp and total array
            if (nums[j] <= (sum - (total[i] % sum))) {
              dp[temp] = true;
              total[temp] = nums[j] + total[i];
            } else {
              break;
            }
          }
        }
      }
    }
    return dp[(1 << n) - 1];
  }

}
