package Sliding;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/17/2021  4:16 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 * <p>
 * Given a string s and an integer k, return the length of the longest substring of s such that the
 * frequency of each character in this substring is greater than or equal to k.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aaabb", k = 3 Output: 3 Explanation: The longest substring is "aaa", as 'a' is
 * repeated 3 times. Example 2:
 * <p>
 * Input: s = "ababbc", k = 2 Output: 5 Explanation: The longest substring is "ababb", as 'a' is
 * repeated 2 times and 'b' is repeated 3 times.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 104 s consists of only lowercase English letters. 1 <= k <= 105
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters {

  public static void main(String[] args) {
    LongestSubstringwithAtLeastKRepeatingCharacters o = new LongestSubstringwithAtLeastKRepeatingCharacters();
    System.out.println(o.longestSubstring_divide_and_conquer("aababbc", 2));
  }

  public int longestSubstring_divide_and_conquer(String s, int k) {
    return longestSubstringUtil(s, 0, s.length(), k);
  }

  int longestSubstringUtil(String s, int start, int end, int k) {
    if (end < k) return 0;
    int[] countMap = new int[26];
    // update the countMap with the count of each character
    for (int i = start; i < end; i++)
      countMap[s.charAt(i) - 'a']++;
    for (int mid = start; mid < end; mid++) {
      if (countMap[s.charAt(mid) - 'a'] >= k) continue;
      int midNext = mid + 1;
      while (midNext < end && countMap[s.charAt(midNext) - 'a'] < k) midNext++;
      return Math.max(longestSubstringUtil(s, start, mid, k),
          longestSubstringUtil(s, midNext, end, k));
    }
    return (end - start);
  }


  public int longestSubstring(String s, int k) {
    char[] str = s.toCharArray();
    int[] countMap = new int[26];
    int maxUnique = getMaxUniqueLetters(s);
    int result = 0;
    for (int currUnique = 1; currUnique <= maxUnique; currUnique++) {
      // reset countMap
      Arrays.fill(countMap, 0);
      int windowStart = 0, windowEnd = 0, idx = 0, unique = 0, countAtLeastK = 0;
      while (windowEnd < str.length) {
        // expand the sliding window
        if (unique <= currUnique) {
          idx = str[windowEnd] - 'a';
          if (countMap[idx] == 0) {
            unique++;
          }
          countMap[idx]++;
          if (countMap[idx] == k) {
            countAtLeastK++;
          }
          windowEnd++;
        }
        // shrink the sliding window
        else {
          idx = str[windowStart] - 'a';
          if (countMap[idx] == k) {
            countAtLeastK--;
          }
          countMap[idx]--;
          if (countMap[idx] == 0) {
            unique--;
          }
          windowStart++;
        }
        if (unique == currUnique && unique == countAtLeastK) {
          result = Math.max(windowEnd - windowStart, result);
        }
      }
    }

    return result;
  }

  // get the maximum number of unique letters in the string s
  int getMaxUniqueLetters(String s) {
    boolean map[] = new boolean[26];
    int maxUnique = 0;
    for (int i = 0; i < s.length(); i++) {
      if (!map[s.charAt(i) - 'a']) {
        maxUnique++;
        map[s.charAt(i) - 'a'] = true;
      }
    }
    return maxUnique;
  }
}
