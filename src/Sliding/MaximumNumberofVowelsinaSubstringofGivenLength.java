package Sliding;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 * Jul 11,2023 - 6:56 PM
 */

/**
 * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
 *
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: The substring "iii" contains 3 vowel letters.
 * Example 2:
 *
 * Input: s = "aeiou", k = 2
 * Output: 2
 * Explanation: Any substring of length 2 contains 2 vowels.
 * Example 3:
 *
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: "lee", "eet" and "ode" contain 2 vowels.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of lowercase English letters.
 * 1 <= k <= s.length
 */
public class MaximumNumberofVowelsinaSubstringofGivenLength {

    public static void main(String[] args) {
        MaximumNumberofVowelsinaSubstringofGivenLength o = new MaximumNumberofVowelsinaSubstringofGivenLength();
        System.out.println(o.maxVowels("abciiidef", 3));
    }

    public int maxVowels_faster(String s, int k) {

        int n = s.length();
        byte[] vowels = new byte[256];
        vowels['a'] = vowels['e'] = vowels['i'] = vowels['o'] = vowels['u'] = 1;

        byte[] givenString = s.getBytes();

        int count = 0, i = 0, maxCount = 0;
        while (i < k) {
            count += vowels[givenString[i++]];
        }
        maxCount = count;

        while (i < n) {
            count += vowels[givenString[i]] - vowels[givenString[i-k]];
            i++;
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }

    public int maxVowels(String s, int k) {
        int n = s.length(), b = 0, z = 0;
        byte[] w = new byte[n];
        s.getBytes(0, n, w, 0);

        byte[] gs = s.getBytes();

        byte[] t = new byte['{'];
        t['a'] = t['e'] = t['i'] = t['o'] = t['u'] = 1;

        while (b < k) {
            z += t[w[b++]];
        }

        for (int a = 0, c = z; b < n; ) {
            if ((c += t[w[b++]] - t[w[a++]]) > z && (z = c) == k) {
                return k;
            }
        }
        return z;
    }

}
