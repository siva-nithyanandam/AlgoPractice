package MathAndLogic;

/**
 * Balanced strings are those who have equal quantity of 'L' and 'R' characters.
 *
 * Given a balanced string s split it in the maximum amount of balanced strings.
 *
 * Return the maximum amount of splitted balanced strings.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "RLRRLLRLRL"
 * Output: 4
 * Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
 * Example 2:
 *
 * Input: s = "RLLLLRRRLR"
 * Output: 3
 * Explanation: s can be split into "RL", "LLLRRR", "LR", each substring contains same number of 'L' and 'R'.
 * Example 3:
 *
 * Input: s = "LLLLRRRR"
 * Output: 1
 * Explanation: s can be split into "LLLLRRRR".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s[i] = 'L' or 'R'
 */
public class SplitaStringinBalancedStrings {

    public static void main(String[] args) {
        SplitaStringinBalancedStrings o = new SplitaStringinBalancedStrings();
        System.out.println(o.balancedStringSplit("RLRRLLRLRL"));
        System.out.println(o.balancedStringSplit("RLLLLRRRLR"));
        System.out.println(o.balancedStringSplit("LLLLRRRR"));
        System.out.println(o.balancedStringSplit("RRLRRLRLLLRL"));
    }

    public int balancedStringSplit(String s) {
        int bal = 0, res = 0;
        for (char c : s.toCharArray()) {
            if (c == 'R') {
                bal++;
            } else {
                bal--;
            }
            if (bal == 0) {
                res++;
            }
        }
        return res;
    }
}
