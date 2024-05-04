package BitManipulation;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/5/2021  3:37 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/problems/counting-bits/
 *
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [0,1,1]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * Example 2:
 *
 * Input: n = 5
 * Output: [0,1,1,2,1,2]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *
 *
 * Constraints:
 *
 * 0 <= n <= 105
 *
 *
 * Follow up:
 *
 * It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n) and possibly in a single pass?
 * Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
 */
public class CountingBits {

  public static void main(String[] args) {
    CountingBits o = new CountingBits();
    System.out.println(o.countBits(5));
    System.out.println(o.countBits_1(3));
  }

  public int[] countBits_1(int num) {
    int[] ans = new int[num + 1];
    for (int i = 1; i <= num; ++i)
      ans[i] = ans[i >> 1] + (i & 1); // x / 2 is x >> 1 and x % 2 is x & 1
    return ans;
  }

  public int[] countBits(int num) {
    int[] ans = new int[num + 1];
    int i = 0, b = 1;
    // [0, b) is calculated
    while (b <= num) {
      // generate [b, 2b) or [b, num) from [0, b)
      while(i < b && i + b <= num){
        ans[i + b] = ans[i] + 1;
        ++i;
      }
      i = 0;   // reset i
      b <<= 1; // b = 2b
    }
    return ans;
  }
}
