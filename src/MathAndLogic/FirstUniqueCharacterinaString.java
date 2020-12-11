package MathAndLogic;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * <p>
 * Given a string, find the first non-repeating character in it and return its index. If it doesn't
 * exist, return -1.
 * <p>
 * Examples:
 * <p>
 * s = "leetcode" return 0.
 * <p>
 * s = "loveleetcode" return 2.
 * <p>
 * <p>
 * Note: You may assume the string contains only lowercase English letters.
 */
public class FirstUniqueCharacterinaString {

  public static void main(String[] args) {
    FirstUniqueCharacterinaString o = new FirstUniqueCharacterinaString();
    System.out.println(o.firstUniqChar("leetcode"));
    System.out.println(o.firstUniqChar("loveleetcode"));
  }

  public int firstUniqChar_faster(String s) {
    int ans = Integer.MAX_VALUE;
    for (char c = 'a'; c <= 'z'; ++c) {
      int index = s.indexOf(c);
      if (index != -1 && index == s.lastIndexOf(c)) {
        ans = Math.min(ans, index);
      }
    }
    return ans == Integer.MAX_VALUE ? -1 : ans;
  }

  public int firstUniqChar(String s) {
    int[] arr = new int[256];
    for (int i = 0; i < s.length(); i++) {
      arr[s.charAt(i)]++;
    }
    for (int i = 0; i < s.length(); i++) {
      if (arr[s.charAt(i)] == 1) {
        return i;
      }
    }
    return -1;
  }
}
