package arrays;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the
 * order of characters. No two characters may map to the same character but a character may map to itself.
 * Example 1:
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 * Input: s = "paper", t = "title"
 * Output: true
 * Note:
 * You may assume both s and t have the same length.
 */
public class IsomorphicStrings {

  public static void main(String[] args) {
    IsomorphicStrings o = new IsomorphicStrings();
    System.out.println(o.isIsomorphic("ab", "aa"));
    System.out.println(o.isIsomorphic("egg", "add"));
    System.out.println(o.isIsomorphic("foo", "bar"));
    System.out.println(o.isIsomorphic("paper", "title"));
  }

  public boolean isIsomorphic(String s, String t) {
    char[] sIdx = new char[128];
    char[] tIdx = new char[128];
    char[] sArr = s.toCharArray();
    char[] tArr = t.toCharArray();
    for (int i = 0; i < sArr.length; i++) {
      if (tIdx[tArr[i]] != '\0' || sIdx[sArr[i]] != '\0') {
        if (sIdx[sArr[i]] != tArr[i]) {
          return false;
        }
      } else {
        tIdx[tArr[i]] = sArr[i];
        sIdx[sArr[i]] = tArr[i];
      }
    }
    return true;
  }
}
