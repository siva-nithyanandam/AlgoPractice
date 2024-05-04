package stack;
/**
 * @time 4/20/24-9:43 AM
 * @author sivaprakashnithyanandam
 */

import java.util.Stack;

/**
 *Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either '(' , ')', or lowercase English letter.
 */
public class MinimumRemovetoMakeValidParentheses {

    public static void main(String[] args) {
        MinimumRemovetoMakeValidParentheses o = new MinimumRemovetoMakeValidParentheses();
        System.out.println(o.minRemoveToMakeValid("lee(t(c)o)de)"));
    }

    public String minRemoveToMakeValid(String s) {

        Stack<Integer> opens = new Stack<>();
        Stack<Integer> closes = new Stack<>();

        StringBuilder sb = new StringBuilder(s);
        int i = 0;
        while (i < sb.length()) {
            if (sb.charAt(i) == '(') {
                opens.push(i);
                i++;
            } else if (sb.charAt(i) == ')') {
                if (opens.isEmpty()) {
                    sb.deleteCharAt(i);
                } else {
                    opens.pop();
                    i++;
                }
            } else {
                i++;
            }
        }

        i = sb.length()-1;
        while (i >= 0) {
            if (sb.charAt(i) == ')') {
                closes.push(i);
                i--;
            } else if (sb.charAt(i) == '(') {
                if (closes.isEmpty()) {
                    sb.deleteCharAt(i);
                    i--;
                } else {
                    closes.pop();
                    i--;
                }
            } else {
                i--;
            }
        }

        return sb.toString();
    }
}
