package MathAndLogic;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Example 4:
 *
 * Input: s = ""
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 */
public class LongestSubstringWithoutRepeatingCharacters {

  public static void main(String[] args) {
    LongestSubstringWithoutRepeatingCharacters o = new LongestSubstringWithoutRepeatingCharacters();
    System.out.println(o.lengthOfLongestSubstring_faster("abcabcbb"));
    System.out.println(o.lengthOfLongestSubstring("bbbbbbb"));
    System.out.println(o.lengthOfLongestSubstring("pwwkew"));
    System.out.println(o.lengthOfLongestSubstring(""));
    System.out.println(o.lengthOfLongestSubstring(" "));
  }

  public int lengthOfLongestSubstring_faster(String str) {
    int[] indexes= new int[256];
    char[] s = str.toCharArray();
    int res = 0, j = 0;
    for(int i = 0; i < s.length; i++) {
      j = Math.max(j, indexes[s[i]]);
      res = Math.max(res, i-j+1);
      indexes[s[i]]=i+1;
    }
    return res;
  }

  public int lengthOfLongestSubstring(String s) {
    char[] str = s.toCharArray();
    int[] holder = new int[255];
    int max = 0;
    int start = 0;
    for (int i = 0; i < s.length(); i++) {
      if (holder[str[i]] > start) {
        max = Math.max(max, i-start);
        start = holder[str[i]];
      }
      holder[str[i]] = i+1;
    }
    return Math.max(max, s.length()-start);
  }
}
