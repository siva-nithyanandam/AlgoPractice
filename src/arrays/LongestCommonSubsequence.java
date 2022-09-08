package arrays;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 10/4/2021  11:03 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 *
 * Constraints:
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        LongestCommonSubsequence o = new LongestCommonSubsequence();
        System.out.println(o.lcsDynamic_own("abcde", "ace"));
        System.out.println(o.lcsDynamic("abcde", "ace"));
    }

    public int lcsDynamic(String s, String t) {
        int[][] m = new int[s.length() +1][t.length() +1];
        for (int i=s.length()-1; i>=0; i--) {
            for (int j=t.length()-1; j>=0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    m[i][j] = m[i+1][j+1] + 1;
                } else {
                    m[i][j] = Math.max(m[i+1][j], m[i][j+1]);
                }
            }
        }
        return m[0][0];
    }

    public int lcsDynamic_own(String s, String t) {

        int[][] a = new int[s.length() + 1][t.length() + 1];

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    a[i+1][j+1] = a[i][j] + 1;
                } else {
                    a[i+1][j+1] = Math.max(a[i+1][j], a[i][j+1]);
                }
            }
        }
        return a[s.length()][t.length()];
    }


}
