package BitMasking;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 * May 01,2024 - 11:37 AM
 */

/**
 * https://leetcode.com/problems/number-of-wonderful-substrings/description/?envType=daily-question&envId=2024-04-30
 *
 * A wonderful string is a string where at most one letter appears an odd number of times.
 *
 * For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
 * Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return the number of wonderful non-empty substrings in word. If the same substring appears multiple times in word, then count each occurrence separately.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "aba"
 * Output: 4
 * Explanation: The four wonderful substrings are underlined below:
 * - "aba" -> "a"
 * - "aba" -> "b"
 * - "aba" -> "a"
 * - "aba" -> "aba"
 * Example 2:
 *
 * Input: word = "aabb"
 * Output: 9
 * Explanation: The nine wonderful substrings are underlined below:
 * - "aabb" -> "a"
 * - "aabb" -> "aa"
 * - "aabb" -> "aab"
 * - "aabb" -> "aabb"
 * - "aabb" -> "a"
 * - "aabb" -> "abb"
 * - "aabb" -> "b"
 * - "aabb" -> "bb"
 * - "aabb" -> "b"
 * Example 3:
 *
 * Input: word = "he"
 * Output: 2
 * Explanation: The two wonderful substrings are underlined below:
 * - "he" -> "h"
 * - "he" -> "e"
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 105
 * word consists of lowercase English letters from 'a' to 'j'.
 */
public class NumberofWonderfulSubstrings {

    public static void main(String[] args) {
        NumberofWonderfulSubstrings o = new NumberofWonderfulSubstrings();
        System.out.println(o.wonderfulSubstrings_faster("aba"));
        System.out.println(o.wonderfulSubstrings("aab"));
        System.out.println(o.wonderfulSubstrings("ab"));
    }

    public long wonderfulSubstrings(String word) {
        // create array to keep track of how many times we have seen a given mask
        // 2^10 because we can have max 10 chars in a string, so if all chars are odd, then all bits are 1
        var freq = new long[1024];
        freq[0] = 1;    // 0 will be string with no chars at all

        long result = 0; // 0 wonderful substrings at start
        var mask = 0;    // no odd characters at starting
        for(var ch : word.toCharArray()){
            int maskOf = maskOf(ch);
            mask ^= maskOf;  // flip the bit corresponding to current char. If it was odd, it becomes even and so on

            // if we have seen this mask in past, then chars between that posn and current are all even
            // so it is a valid wonderful substring, hence we increase result
            // freq[mask] will be 0 if we have not seen this mask earlier
            result += freq[mask];

            // even count is taken care of. now we need to consider case where one char is odd
            // so for each char in current mask, we will flip it one by one, and check if we have seen the mask earlier
            // if we have seen it, then it means that the char we flipped will be odd, and others even
            for(var curr='a'; curr<='j'; curr++){
                maskOf = maskOf(curr);
                var maskToCheck = mask ^ maskOf;
                result += freq[maskToCheck];
            }

            // now we have to update freq to say that we have seen the current mask
            freq[mask]++;
        }

        return result;
    }

    private int maskOf(char ch){
        // we start with a mask of 10 bits 0000000001
        // then we shift "1" as many times as the char position
        // so for 'a' we shift 0 times, for b shift once, and so one...
        // eg. final mask of "d" should be "0000001000"
        var val = ch-'a';
        return 1 << val;
    }

    public long wonderfulSubstrings_faster(String word) {

        int n = word.length();
        byte[] bytes = new byte[n];
        word.getBytes(0, n, bytes, 0);

        int[] count = new int[1024];
        count[0]++;

        int mask = 0;
        for(byte b:bytes){
            mask ^= 1 << (b - 'a');
            count[mask]++;
        }

        long ans = 0;
        for(int i = 0; i < 1024; ++i) {
            int c = count[i];
            if(c == 0) {
                continue;
            }

            long cur = (long) c * (c -1)/2;

            for(int s = i; s > 0; s &= (s -1)) {
                int low = s &(-s);
                mask = i - low;
                cur += (long)c * count[mask];
            }
            ans += cur;
        }
        return ans;
    }
}
