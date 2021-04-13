package MathAndLogic;

/**
 * Given a string s and an integer k, return the length of the longest substring of s such
 * that the frequency of each character in this substring is greater than or equal to k.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 *
 * Input: s = "ababbc", k = 2
 * Output: 5
 * Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of only lowercase English letters.
 * 1 <= k <= 105
 */
//https://gist.github.com/bilbo3000/141f54fb5c5cf3dd9a4015485dd2275d
public class LongestSubstringwithAtLeastKRepeatingCharacters {

  public static void main(String[] args) {
    LongestSubstringwithAtLeastKRepeatingCharacters o = new LongestSubstringwithAtLeastKRepeatingCharacters();
//    System.out.println(o.longestSubstring("aaabb", 3));
//    System.out.println(o.longestSubstring("ababbc", 2));
  }

  /*public int longestSubstring(String s, int k) {
    char[] chars = s.toCharArray();
    int[] letters;
    int start, len;
    for (int i = 0; i < s.length(); i++) {
      letters = new int[26];
      for (int j = i; j < s.length(); j++) {
        letters[chars[j]]++;
        if (hasFreq(letters, k)) {

        }
      }
    }
  }*/

  private boolean hasFreq(int[] letters, int k) {
    for (int i : letters) {
      if (i != 0 && i < k) {
        return false;
      }
    }
    return true;
  }
}
