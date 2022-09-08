package dynamicProgramming;

/**
 * https://leetcode.com/contest/weekly-contest-299/problems/count-number-of-ways-to-place-houses/
 *
 * There is a street with n * 2 plots, where there are n plots on each side of the street. The plots on each side are numbered from 1 to n. On each plot, a house can be placed.
 *
 * Return the number of ways houses can be placed such that no two houses are adjacent to each other on the same side of the street. Since the answer may be very large, return it modulo 109 + 7.
 *
 * Note that if a house is placed on the ith plot on one side of the street, a house can also be placed on the ith plot on the other side of the street.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 4
 * Explanation:
 * Possible arrangements:
 * 1. All plots are empty.
 * 2. A house is placed on one side of the street.
 * 3. A house is placed on the other side of the street.
 * 4. Two houses are placed, one on each side of the street.
 * Example 2:
 *
 *
 * Input: n = 2
 * Output: 9
 * Explanation: The 9 possible arrangements are shown in the diagram above.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 104
 */
public class CountNumberofWaystoPlaceHouses {

    public static void main(String[] args) {
        CountNumberofWaystoPlaceHouses o = new CountNumberofWaystoPlaceHouses();
        System.out.println(o.countHousePlacements(2));
        System.out.println(o.countHousePlacements(3));
    }

    int mod = (int)1e9 + 7;

    public int countHousePlacements(int n) {

        double[][] dp = new double[n+1][2];
        double res = helper(n+1, 0, 0, dp);
        return (int) ((res * res) % mod);
    }

    private double helper(int n, int pos, int placed, double[][] dp) {

        if (pos == n-1) {
            return 1;
        }
        if (dp[pos][placed] != 0) {
            return dp[pos][placed];
        }
        double res = 0;
        if (placed == 0) {
            res += helper(n, pos+1, 0, dp) % mod;
            res %= mod;
            res += (helper(n, pos+1, 1, dp) % mod);
        } else {
            res += (helper(n, pos+1, 0, dp) % mod);
        }
        res %= mod;
        dp[pos][placed] = res;
        return res;
    }
}
