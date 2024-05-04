package MathAndLogic;
/**
 * @time 4/21/24-10:40 AM
 * @author sivaprakashnithyanandam
 */

import test.Test;

import java.util.HashMap;

/**
 *You are given a string word. A letter is called special if it appears both in lowercase and uppercase in word.
 *
 * Return the number of special letters in word.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "aaAbcBC"
 *
 * Output: 3
 *
 * Explanation:
 *
 * The special characters in word are 'a', 'b', and 'c'.
 *
 * Example 2:
 *
 * Input: word = "abc"
 *
 * Output: 0
 *
 * Explanation:
 *
 * No character in word appears in uppercase.
 *
 * Example 3:
 *
 * Input: word = "abBCab"
 *
 * Output: 1
 *
 * Explanation:
 *
 * The only special character in word is 'b'.
 *
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 50
 * word consists of only lowercase and uppercase English letters.
 */
public class CounttheNumberofSpecialCharactersI {

    public static void main(String[] args) {
        CounttheNumberofSpecialCharactersI o = new CounttheNumberofSpecialCharactersI();
        System.out.println(o.numberOfSpecialChars("eEb"));
        System.out.println(o.numberOfSpecialChars("AbBCab"));
        System.out.println(o.numberOfSpecialChars("aaAbcBC"));
    }

    public int numberOfSpecialChars_own(String word) {
        int res = 0;
        int[] chars = new int[256];
        for (int i = 0; i < word.length(); i++) {
            chars[word.charAt(i)] = i+1;
        }

        for (int i = 65; i <= 91; i++) {
            if (chars[i] != 0 && chars[i+32] != 0 && chars[i] > chars[i+32]) {
                res++;
            }
        }
        return res;
    }

    public static int numberOfSpecialChars(String word) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        for (int nx : word.toCharArray()) {
            if ('A' <= nx && nx <= 'Z') {
                mp.put(nx-'A', mp.getOrDefault(nx-'A', 0) | 1);
            } else {
                mp.put(nx-'a', mp.getOrDefault(nx-'a', 0) | 2);
            }
        }

        int res = 0;
        for (int val : mp.values()) {
            if (val == 3) {
                res++;
            }
        }
        return res;
    }
}
