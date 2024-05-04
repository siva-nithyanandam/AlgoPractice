package MathAndLogic;
/**
 * @time 4/27/24-1:47 PM
 * @author sivaprakashnithyanandam
 */

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/freedom-trail/description/?envType=daily-question&envId=2024-04-27
 * <p>
 * In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring" and use the dial to spell a specific keyword to open the door.
 * <p>
 * Given a string ring that represents the code engraved on the outer ring and another string key that represents the keyword that needs to be spelled, return the minimum number of steps to spell all the characters in the keyword.
 * <p>
 * Initially, the first character of the ring is aligned at the "12:00" direction. You should spell all the characters in key one by one by rotating ring clockwise or anticlockwise to make each character of the string key aligned at the "12:00" direction and then by pressing the center button.
 * <p>
 * At the stage of rotating the ring to spell the key character key[i]:
 * <p>
 * You can rotate the ring clockwise or anticlockwise by one place, which counts as one step. The final purpose of the rotation is to align one of ring's characters at the "12:00" direction, where this character must equal key[i].
 * If the character key[i] has been aligned at the "12:00" direction, press the center button to spell, which also counts as one step. After the pressing, you could begin to spell the next character in the key (next stage). Otherwise, you have finished all the spelling.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: ring = "godding", key = "gd"
 * Output: 4
 * Explanation:
 * For the first key character 'g', since it is already in place, we just need 1 step to spell this character.
 * For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
 * Also, we need 1 more step for spelling.
 * So the final output is 4.
 * Example 2:
 * <p>
 * Input: ring = "godding", key = "godding"
 * Output: 13
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= ring.length, key.length <= 100
 * ring and key consist of only lower case English letters.
 * It is guaranteed that key could always be spelled by rotating ring.
 */
public class FreedomTrail {

    public static void main(String[] args) {
        FreedomTrail o = new FreedomTrail();
        System.out.println(o.findRotateSteps("godding", "gd"));
        System.out.println(o.findRotateSteps("caotmcaataijjxi", "oatjiioicitatajtijciocjcaaxaaatmctxamacaamjjx"));
    }

    public int findRotateSteps(String ring, String key) {
        char[] r = ring.toCharArray();
        List<Integer>[] p = new List[26];
        for (int i = 0; i < r.length; i++) {
            int c = r[i] - 'a';
            List<Integer> l = p[c];
            if (l == null) {
                p[c] = l = new ArrayList<>();
            }
            l.add(i);
        }
        return helper(0, 0, p, key.toCharArray(), ring, new int[key.length()][r.length]);
    }

    int helper(int in, int pos, List<Integer>[] p, char[] k, String r, int[][] memo) {
        if (in == k.length) {
            return 0;
        }
        if (memo[in][pos] > 0) {
            return memo[in][pos] - 1;
        }
        int min = Integer.MAX_VALUE;
        for (int i : p[k[in] - 'a']) {
            int m;
            if (i >= pos) {
                m = Math.min(i - pos, pos + r.length() - i);
            } else {
                m = Math.min(pos - i, i + r.length() - pos);
            }
            min = Math.min(min, m + helper(in + 1, i, p, k, r, memo));
        }
        return (memo[in][pos] = min + 2) - 1;
    }
}
