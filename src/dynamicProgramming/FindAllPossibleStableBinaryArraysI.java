package dynamicProgramming;
/**
 * @time 4/27/24-5:53 PM
 * @author sivaprakashnithyanandam
 */

/**
 * You are given 3 positive integers zero, one, and limit.
 * <p>
 * A
 * binary array
 * arr is called stable if:
 * <p>
 * The number of occurrences of 0 in arr is exactly zero.
 * The number of occurrences of 1 in arr is exactly one.
 * Each
 * subarray
 * of arr with a size greater than limit must contain both 0 and 1.
 * Return the total number of stable binary arrays.
 * <p>
 * Since the answer may be very large, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: zero = 1, one = 1, limit = 2
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * The two possible stable binary arrays are [1,0] and [0,1], as both arrays have a single 0 and a single 1, and no subarray has a length greater than 2.
 * <p>
 * Example 2:
 * <p>
 * Input: zero = 1, one = 2, limit = 1
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * The only possible stable binary array is [1,0,1].
 * <p>
 * Note that the binary arrays [1,1,0] and [0,1,1] have subarrays of length 2 with identical elements, hence, they are not stable.
 * <p>
 * Example 3:
 * <p>
 * Input: zero = 3, one = 3, limit = 2
 * <p>
 * Output: 14
 * <p>
 * Explanation:
 * <p>
 * All the possible stable binary arrays are [0,0,1,0,1,1], [0,0,1,1,0,1], [0,1,0,0,1,1], [0,1,0,1,0,1], [0,1,0,1,1,0], [0,1,1,0,0,1], [0,1,1,0,1,0], [1,0,0,1,0,1], [1,0,0,1,1,0], [1,0,1,0,0,1], [1,0,1,0,1,0], [1,0,1,1,0,0], [1,1,0,0,1,0], and [1,1,0,1,0,0].
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= zero, one, limit <= 200
 */
public class FindAllPossibleStableBinaryArraysI {

    int MOD = 1_000_000_007;

    public static void main(String[] args) {
        FindAllPossibleStableBinaryArraysI o = new FindAllPossibleStableBinaryArraysI();
        System.out.println(o.numberOfStableArrays(1, 1, 2));
    }

    public int numberOfStableArrays(int zero, int one, int limit) {
        long[][][] dp = new long[zero + 1][one + 1][2];
        dp[0][0][0] = dp[0][0][1] = 1;

        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                for (int k = 1; k <= limit; k++) {
                    if (i - k >= 0) {
                        dp[i][j][1] = (dp[i][j][1] + dp[i - k][j][0]) % MOD;
                    }
                    if (j - k >= 0) {
                        dp[i][j][0] = (dp[i][j][0] + dp[i][j - k][1]) % MOD;
                    }
                }
            }
        }
        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }
}
