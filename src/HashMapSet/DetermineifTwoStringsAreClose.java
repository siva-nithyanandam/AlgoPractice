package HashMapSet;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 * Jul 18,2023 - 7:57 AM
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/determine-if-two-strings-are-close/?envType=study-plan-v2&envId=leetcode-75
 *
 * Two strings are considered close if you can attain one from the other using the following operations:
 *
 * Operation 1: Swap any two existing characters. For example, abcde -> aecdb Operation 2: Transform every occurrence of
 * one existing character into another existing character, and do the same with the other character. For example, aacabb
 * -> bbcbaa (all a's turn into b's, and all b's turn into a's) You can use the operations on either string as many
 * times as necessary.
 *
 * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "abc", word2 = "bca" Output: true Explanation: You can attain word2 from word1 in 2 operations. Apply
 * Operation 1: "abc" -> "acb" Apply Operation 1: "acb" -> "bca" Example 2:
 *
 * Input: word1 = "a", word2 = "aa" Output: false Explanation: It is impossible to attain word2 from word1, or vice
 * versa, in any number of operations. Example 3:
 *
 * Input: word1 = "cabbba", word2 = "abbccc" Output: true Explanation: You can attain word2 from word1 in 3 operations.
 * Apply Operation 1: "cabbba" -> "caabbb" Apply Operation 2: "caabbb" -> "baaccc" Apply Operation 2: "baaccc" ->
 * "abbccc"
 *
 *
 * Constraints:
 *
 * 1 <= word1.length, word2.length <= 105 word1 and word2 contain only lowercase English letters.
 */
public class DetermineifTwoStringsAreClose {

    public static void main(String[] args) {
        DetermineifTwoStringsAreClose o = new DetermineifTwoStringsAreClose();
        System.out.println(o.closeStrings("abc", "bca"));
        System.out.println(o.closeStrings(
                "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii",
                "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"));
    }

    public boolean closeStrings(String w1, String w2) {
        int l1, l2;
        if ((l1 = w1.length()) != (l2 = w2.length())) {
            return false;
        }
        byte[] b1 = w1.getBytes(), b2 = w2.getBytes();
        int[] c1 = new int[26], c2 = new int[26];
        method(b1, c1, l1);
        method(b2, c2, l2);
        for (int i = 0; i < 26; i++) {
            if (c1[i] > 0 ^ c2[i] > 0) {
                return false;
            }
        }
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }

    void method(byte[] b, int[] c, int l) {
        while (l-- > 0) {
            c[b[l] - 97]++;
        }
    }

    public boolean closeStrings_5ms(String word1, String word2) {
        int n = word1.length();
        if (n != word2.length()) {
            return false;
        }
        if (word1.equals(word2)) {
            return true;
        }

        int freq1[] = new int['z' + 1];
        int freq2[] = new int['z' + 1];
        byte[] w = new byte[n];
        word1.getBytes(0, n, w, 0);
        for (byte c : w) {
            freq1[c]++;
        }
        word2.getBytes(0, n, w, 0);
        for (byte c : w) {
            freq2[c]++;
        }

        for (int i = 'a'; i <= 'z'; i++) {
            if (freq1[i] == 0 ^ freq2[i] == 0) {
                return false;
            }
        }
        freq1['a' - 1] = -1;
        for (int i, k = 'a'; ++k <= 'z'; ) {
            int f1i = freq1[i = k];
            while (f1i < freq1[--i]) {
                freq1[i + 1] = freq1[i];
            }
            freq1[i + 1] = f1i;
        }
        freq2['a' - 1] = -1;
        for (int i, k = 'a'; ++k <= 'z'; ) {
            int f2i = freq2[i = k];
            while (f2i < freq2[--i]) {
                freq2[i + 1] = freq2[i];
            }
            freq2[i + 1] = f2i;
        }
        for (int i = 'a'; i <= 'z'; i++) {
            if (freq1[i] != freq2[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean closeStrings_90ms(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (char c : word1.toCharArray()) {
            map1.merge(c, 1, (a, b) -> a + b);
        }

        for (char c : word2.toCharArray()) {
            map2.merge(c, 1, (a, b) -> a + b);
        }

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            if (!map2.containsKey(entry.getKey())) {
                return false;
            }
            list2.add(map2.get(entry.getKey()));
            map2.remove(entry.getKey());
            list1.add(entry.getValue());
        }
        if (map2.size() != 0) {
            return false;
        }

        Collections.sort(list1);
        Collections.sort(list2);

        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).compareTo(list2.get(i)) != 0) {
                return false;
            }
        }
        return true;
    }
}
