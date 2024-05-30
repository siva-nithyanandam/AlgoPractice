package dynamicProgramming;
/**
 * @time 5/26/24-3:26 PM
 * @author sivaprakashnithyanandam
 */

/**
 * https://leetcode.com/problems/student-attendance-record-ii/description/
 *
 * An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:
 *
 * 'A': Absent.
 * 'L': Late.
 * 'P': Present.
 * Any student is eligible for an attendance award if they meet both of the following criteria:
 *
 * The student was absent ('A') for strictly fewer than 2 days total.
 * The student was never late ('L') for 3 or more consecutive days.
 * Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 8
 * Explanation: There are 8 records with length 2 that are eligible for an award:
 * "PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
 * Example 2:
 *
 * Input: n = 1
 * Output: 3
 * Example 3:
 *
 * Input: n = 10101
 * Output: 183236316
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 */
public class StudentAttendanceRecordII {

    public static void main(String[] args) {
        StudentAttendanceRecordII o = new StudentAttendanceRecordII();
        System.out.println(o.checkRecord_faster(10101));
        System.out.println(o.checkRecord_good(10101));
//        System.out.println(o.checkRecord_good(3));
//        System.out.println(o.checkRecord_faster(3));
//        System.out.println(o.checkRecord(10101));
    }

    private static final int modulo = (int)1e9 + 7;

    public int checkRecord(int n) {
        int[][][] mem = new int[n][3][2];
        return helper(0, 0, 0, n, mem);
    }

    private int helper(int i, int l, int a, int n, int[][][] mem) {

        //When absent reaches 2 or late reaches 3 should not continue
        if (a == 2 || l == 3) {
            return 0;
        }
        //When index reaches end(n), return 1 count
        if (i >= n) {
            return 1;
        }
        if (mem[i][l][a] != 0) {
            return mem[i][l][a];
        }

        //Going to be present, so late resets to 0, absent will be as it is.
        long ans0 = helper(i+1, 0, a, n, mem);

        //Going to be late, absent will be as it is.
        long ans1 = helper(i+1, l+1, a, n, mem);

        //Going to be absent, so late resets to 0
        long ans2 = helper(i+1, 0, a+1, n, mem);

        return mem[i][l][a] = (int)((ans0 + ans1 + ans2) % modulo);
    }

    static final int MOD = 1000000007;

    public int checkRecord_faster(int n) {
        long[] endingPL = new long[n + 1]; // ending with P or L, no A
        long[] endingP = new long[n + 1]; // ending with P, no A
        endingPL[0] = endingP[0] = 1;
        endingPL[1] = 2;
        endingP[1] = 1;

        for (int i = 2; i <= n; i++) {
            endingP[i] = endingPL[i - 1];
            endingPL[i] = (endingP[i] + endingP[i - 1] + endingP[i - 2]) % MOD;
        }

        long result = endingPL[n];
        System.out.println(result);
        for (int i = 0; i < n; i++) { // inserting A into (n-1)-length strings
            long sum = (endingPL[i] * endingPL[n - i - 1]) % MOD;
            result = (result + sum) % MOD;
        }

        return (int) result;
    }

    /**
     * https://leetcode.com/problems/student-attendance-record-ii/solutions/1027169/simple-solution-with-detailed-thinking-process/?envType=daily-question&envId=2024-05-26
     * @param n
     * @return
     *
     * I will go through the thinking process of solving this problem. Getting the idea is the hard part than working through it or coding it up.
     *
     * The question says three consecutive Ls are not accepted. And atmost one A in the whole string is accepted.
     *
     * No LLL ❌ No AA ❌
     *
     * Let's remove the A's condition and add it later. Lets find out how many strings are possible just with L and P.
     *
     * image
     *
     * For n = 1, the count is 2, LP(1) = 2
     * For n = 2, the count is 4, LP(2) = 4
     * But for n = 3, since LLL is not counted, we have a count of 7 instead of 8, so LP(3) = 7
     * Where LP(n) represents number of strings with only L and P and of length n
     *
     * Lets try extracting a pattern here.
     *
     * There are three types of strings. Those that
     *
     * end_with_L
     * end_with_LL
     * end_with_P
     * (The ones the end_with_L don’t have those strings that end_with_LL so they are mutually exclusive. For example "PLL" will be in end_with_LL and not in end_with_L. Similarly "PPL" will be in end_with_L and not in end_with_LL)
     *
     * So if we have these values for some n, lets calculate the same values for n + 1 (Applying induction style)
     *
     * We can add L and P to end_with_L
     * We can add only P to end_with_LL (because LLL is not accepted)
     * We can add L and P to end_with_P
     * So the new values will be,
     *
     * new_end_with_L = end_with_P (end_with_L is not a part of this because adding an L to L it goes into LL)
     * new_end_with LL = end_with_L
     * new_end_with P = end_with_L + end_with_LL + end_with_P
     * So that’s how we find the number of rewardable strings with L and P.
     *
     * Pseudo code for this would be
     *
     * LP(1) = 2, end_with_L = 1, end_with_LL = 0, end_with_P = 1
     * for i in [2, 3, 4, 5, ...., n]:
     *     new_end_with_L = end_with_P
     * 	new_end_with_LL = end_with_L
     * 	new_end_with_P = end_with_L + end_with_LL + end_with_P
     * 	LP(i) = new_end_with_L + new_end_with_LL + new_end_with_P
     * 	# update the old variables with the new variables' values
     * So we have all the values of from LP(1) till LP(n)
     * Time complexity for this : O(n)
     * Space complexity for this : O(n)
     *
     * Now that we have solved the first part, lets include the A's condition too.
     *
     * Atmost a single A should be present. This means there are two conditions - strings without A and strings with A. So,
     * F(n) = strings_with_out_A + strings_with_A
     *
     * strings_without_A are LP(n) and we already calculated them.
     *
     * strings_with_A is what we have to calculate.
     *
     * So an A can be present anywhere from 1st position to nth position and the remaining characters are L and P. Basically for n = 5 it will be like
     *
     * A _ _ _ _
     * _ A _ _ _
     * _ _ A _ _
     * _ _ _ A _
     * _ _ _ _ A
     *
     * which is equivalent to
     *
     * strings_with_A =
     * 	LP(n - 1) +
     * 	LP(1) * LP(n - 2) +
     * 	LP(2) * LP(n - 3) +
     * 	… +
     * 	LP(n - 2) * LP(1) +
     * 	LP(n - 1)
     * Pseudo code for this would look like
     *
     * # Assume base case of LP(0) = 1 to simplify the for loop
     * LP(0) = 1, strings_with_a = 0
     * for i in [0, 1, 2, ...., n - 1]:
     * 	strings_with_a += (LP(i) * LP(n - i - 1))
     * As we already have LP(1) till LP(n), we can calculate this in O(n).
     * Total Time Complexity = O(n)
     * Total Space Complexity = O(n)
     */
    public int checkRecord_good(int n) {
        long[] lp = new long[n+1];
        long l = 1;
        long ll = 0;
        long p = 1;

        lp[0] = 1;
        lp[1] = 2;

        for (int i = 2; i <= n; i++) {
            long new_l = p;
            long new_ll = l;
            long new_p = (p + l + ll) % MOD;

            lp[i] = (new_p + new_ll + new_l) % MOD;

            p = new_p;
            l = new_l;
            ll = new_ll;
        }

        long result = lp[n];
        System.out.println(result);
        for (int i = 0; i < n; i++) { // inserting A into (n-1)-length strings
            long sum = (lp[i] * lp[n - i - 1]) % MOD;
            result = (result + sum) % MOD;
        }

        return (int)result;
    }
}
