package TreesAndGraphs;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/4/2021  10:45 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Stack;

/**
 * https://leetcode.com/problems/decode-string/
 *
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Example 2:
 *
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * Example 3:
 *
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 * Example 4:
 *
 * Input: s = "abc3[cd]xyz"
 * Output: "abccdcdcdxyz"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 */
public class DecodeString {

  public static void main(String[] args) {
    DecodeString o = new DecodeString();
    System.out.println(o.decodeString_own("3[a2[c]]"));
    System.out.println(o.decodeString_own("3[a]2[bc]"));
    System.out.println(o.decodeString("3[a]2[bc]"));
  }

  public String decodeString(String s) {
    StringBuilder res = new StringBuilder();
    char[] chars = s.toCharArray();
    recur(chars, 0, 1, res, true);
    return res.toString();
  }
  // "abc3[cd]xyz"
// Output: "abccdcdcdxyz"

  private int recur(char[] chars, int pos, int count, StringBuilder res, boolean head) {

    int start = pos;
    int end = 0;
    while (start < chars.length && (count > 0 || head)) {
      if (Character.isDigit(chars[start])) {
        int d = chars[start] - '0';
        while (Character.isDigit(chars[start+1])) {
          d = d * 10 + chars[start+1] - '0';
          start++;
        }
        start += 2;
        start = recur(chars, start, d, res, false);
      } else if (chars[start] == ']') {
        count--;
        end = start;
        if (!head) {
          start = pos;
        } else {
          start++;
        }
      } else {
        res.append(chars[start]);
        start++;
      }
    }
    return end + 1;
  }

  public String decodeString_own(String s) {
    Stack<StringBuilder> stringStack = new Stack<>();
    Stack<Integer> countStack = new Stack<>();
    StringBuilder currentString = new StringBuilder();
    int count = 0;
    
    for (char c : s.toCharArray()) {
      if (Character.isDigit(c)) {
        count = count * 10 + c - '0';
      } else if (c == '[') {
        countStack.push(count);
        stringStack.push(currentString);
        currentString = new StringBuilder();
        count = 0;
      } else if(c == ']') {
        StringBuilder prevString = stringStack.pop();
        for (int i = countStack.pop(); i > 0; i--) {
          prevString.append(currentString);
        }
        currentString = prevString;
      } else {
        currentString.append(c);
      }
    }
    return currentString.toString();
  }
}
