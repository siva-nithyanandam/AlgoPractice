package Sliding;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 * Sep 24,2023 - 5:23 PM
 */

/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 *
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aba"
 * Output: true
 * Example 2:
 *
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 * Example 3:
 *
 * Input: s = "abc"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of lowercase English letters.
 */
public class ValidPalindromeII {

    public static void main(String[] args) {
        ValidPalindromeII o = new ValidPalindromeII();
        System.out.println(o.validPalindrome("aba"));
        System.out.println(o.validPalindrome("abca"));
        System.out.println(o.validPalindrome("abcda"));
        System.out.println(o.validPalindrome("deeee"));
        System.out.println(o.validPalindrome("ebcbbececabbacecbbcbe"));
    }

    public boolean validPalindrome(String s) {
        int n = s.length();
        return validPalindrome(s, 1, 0, n-1);
    }

    public boolean validPalindrome(String s, int skip, int i, int j) {
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
                continue;
            } else if (skip > 0) {
                skip--;
                return validPalindrome(s, skip, i+1, j) || validPalindrome(s, skip, i, j-1);
            } else {
                return false;
            }
        }
        return true;
    }
}
