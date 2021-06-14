package arrays;
/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;

/**
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {

  public static void main(String[] args) {
    LongestSubstringWithoutRepeatingCharacters o = new LongestSubstringWithoutRepeatingCharacters();
    System.out.println(o.lengthOfLongestSubstring("abcabcbb"));
    System.out.println(o.lengthOfLongestSubstring("abba"));
    System.out.println(o.lengthOfLongestSubstring("au"));
  }

  public int lengthOfLongestSubstring(String src) {
    int[] holder = new int[256];
    Arrays.fill(holder, -1);
    char[] chars = src.toCharArray();
    int i = 0, s = 0, res = 0;
    while (i < chars.length) {
      if (holder[chars[i]] >= s && holder[chars[i]] > -1) {
        res = Math.max(res, i - s);
        s = holder[chars[i]]+1;
      }
      holder[chars[i]] = i;
      i++;
    }
    return Math.max(res, i - s);
  }
}
