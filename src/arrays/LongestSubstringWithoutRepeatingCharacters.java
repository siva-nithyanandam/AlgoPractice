package arrays;
/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {

  public static void main(String[] args) {
    LongestSubstringWithoutRepeatingCharacters o = new LongestSubstringWithoutRepeatingCharacters();
    System.out.println(o.lengthOfLongestSubstring("abcacbcbb"));
    System.out.println(o.lengthOfLongestSubstring_fastest("abcacbcbb"));
    System.out.println(o.lengthOfLongestSubstring("abba"));
    System.out.println(o.lengthOfLongestSubstring("au"));
  }

  public int lengthOfLongestSubstring_fastest(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    int start = 0;
    int len = 1;

    char[] c = s.toCharArray();

    for(int end = 0; end < c.length; end++) {
      for(int j = start; j < end; j++) {
        if (c[j] == c[end]) {
          start = j+1;
          break;
        }
      }
      len = Math.max(len, end - start + 1);
    }

    return len;

  }

  public int lengthOfLongestSubstring(String s) {
    int start = 0, res = 0;
    int[] arr = new int[256];
    int i = 0;

    for (; i < s.length(); i++) {
      if (arr[s.charAt(i)] > start) {
        res = Math.max(res, i - start);
        start = arr[s.charAt(i)];
      }
      arr[s.charAt(i)] = i+1;
    }
    return Math.max(res, i - start);
  }
}
