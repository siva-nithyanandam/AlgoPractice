package MathAndLogic;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * Note:
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 * Example 5:
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 */

/**
 * Using dynamic programming - Bottom Up approach
 */
public class RegularExpressionMatching {

  public static void main(String[] args) {
    RegularExpressionMatching o = new RegularExpressionMatching();
    System.out.println(o.isMatch("xaabyc", "xa*b.c"));//true
    System.out.println(o.isMatch("aa", "a"));//false
    System.out.println(o.isMatch("aa", "a*"));//true
    System.out.println(o.isMatch("ab", ".*"));//true
    System.out.println(o.isMatch("aab", "c*a*b"));//true
    System.out.println(o.isMatch("mississippi", "mis*is*p*."));//false
    System.out.println(o.isMatch("mississippi", "mis*is*ip*."));//true
  }

  public boolean isMatch(String s, String p) {
    boolean[][] arr = new boolean[s.length()+1][p.length()+1];
    arr[0][0] = true;
    for (int i = 1; i <= p.length(); i++) {
      if (p.charAt(i-1) == '*') {
        arr[0][i] = arr[0][i-2];
      }
    }
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= p.length(); j++) {
        if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
          arr[i][j] = arr[i-1][j-1];
        } else if (p.charAt(j-1) == '*') {
          if (arr[i][j-2]) {
            arr[i][j] = arr[i][j-2];
          } else if (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') {
            arr[i][j] = arr[i-1][j];
          }
        } else {
          arr[i][j] = false;
        }
      }
    }
    return arr[s.length()][p.length()];
  }

  public boolean isMatch_faster(String s, String p) {
    char[] sChars = s.toCharArray();
    char[] pChars = p.toCharArray();
    Boolean [] [] result = new Boolean [s.length() + 1][p.length() + 1];
    return isMatch(sChars, 0, pChars, 0, result);
  }

  private boolean isMatch(char [] sChars, int sIndex, char [] pChars, int pIndex, Boolean [] [] result) {
    if (result[sIndex][pIndex] != null) {
      return result[sIndex][pIndex];
    }
    boolean ret;
    if (pIndex >= pChars.length) {
      // if pattern is empty, then it would only match an empty string
      ret = sIndex == sChars.length;
    } else {

      boolean matchFirstChar = sIndex < sChars.length
              && (sChars[sIndex] == pChars[pIndex] || pChars[pIndex] == '.');

      if (pIndex < pChars.length - 1 && pChars[pIndex + 1] == '*') {
        ret = isMatch(sChars, sIndex, pChars, pIndex + 2, result)
                || matchFirstChar && isMatch(sChars, sIndex + 1, pChars, pIndex, result);
      } else {
        ret = matchFirstChar && isMatch(sChars, sIndex + 1, pChars, pIndex + 1, result);
      }
    }
    result[sIndex][pIndex] = ret;
    return ret;
  }
}
