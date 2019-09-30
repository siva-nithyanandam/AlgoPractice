package arrays;

/**
 * Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.
 *
 * We repeatedly make k duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made.
 *
 * It is guaranteed that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 * Example 2:
 *
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 * Example 3:
 *
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 2 <= k <= 10^4
 * s only contains lower case English letters.
 */

/**
 * Interesting technique. Go through.
 */
public class RemoveAllAdjacentDuplicatesinStringII {

    public static void main(String[] args) {
        RemoveAllAdjacentDuplicatesinStringII o = new RemoveAllAdjacentDuplicatesinStringII();
        System.out.println(o.removeDuplicates("deeedbbcccbdaa", 3));
        System.out.println(o.removeDuplicates("pbbcggttciiippooaais", 2));
        System.out.println(o.removeDuplicates("abcd", 2));
    }

    public String removeDuplicates(String s, int k) {
        int n = s.length();
        char[] ch = new char[n];
        int[] cn = new int[n];
        int p = 1;
        ch[0] = s.charAt(0);
        cn[0] = 1;
        char[] sArr = s.toCharArray();
        for (int i = 1; i < sArr.length; i++) {
            if (p > 0 && sArr[i] == ch[p-1]) {
                if (++cn[p-1] == k) {
                    p--;
                }
            } else {
                ch[p] = sArr[i];
                cn[p] = 1;
                p++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p; i++) {
            int cnt = cn[i];
            while (cnt-- > 0) {
                sb.append(ch[i]);
            }
        }
        return sb.toString();
    }
}
