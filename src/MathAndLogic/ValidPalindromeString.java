package MathAndLogic;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/problems/valid-palindrome/
 *
 * Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * Example 2:
 *
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 2 * 105
 * s consists only of printable ASCII characters.
 */
public class ValidPalindromeString {

  public static void main(String[] args) {
    ValidPalindromeString o = new ValidPalindromeString();
    System.out.println(o.isPalindrome("0P"));
    System.out.println(o.isPalindrome("A man, a plan, a canal: Panama"));
    System.out.println(o.isPalindrome("race a car"));
  }

  public boolean isPalindrome(String s) {
    int start = 0;
    int end = s.length()-1;
    char[] chars = s.toLowerCase().toCharArray();
    while (start < end) {
      if ((chars[start] < 48 || chars[start] > 57) && (chars[start] < 97 || chars[start] > 122)) {
        start++;
      } else if ((chars[end] < 48 || chars[end] > 57) && (chars[end] < 97 || chars[end] > 122)) {
        end--;
      } else {
        if (chars[start] == chars[end]) {
          start++;
          end--;
        } else {
          return false;
        }
      }
    }
    return true;
  }
}
