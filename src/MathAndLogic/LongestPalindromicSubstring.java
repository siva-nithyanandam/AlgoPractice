package MathAndLogic;

/**
 * Given a string s, return the longest palindromic substring in s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "babad" Output: "bab" Note: "aba" is also a valid answer. Example 2:
 * <p>
 * Input: s = "cbbd" Output: "bb" Example 3:
 * <p>
 * Input: s = "a" Output: "a" Example 4:
 * <p>
 * Input: s = "ac" Output: "a"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000 s consist of only digits and English letters (lower-case and/or
 * upper-case)
 */
public class LongestPalindromicSubstring {

  public static void main(String[] args) {
    LongestPalindromicSubstring o = new LongestPalindromicSubstring();
    //System.out.println(o.longestPalindrome_faster("aap"));
    System.out.println(o.longestPalindrome_faster("ababababa"));
    //System.out.println(o.longestPalindrome_faster("paapa"));
  }

  int start = 0, end = 0;

  public String longestPalindrome_faster(String s) {
    if (s.length() < 2) {
      return s;
    }
    char[] c = s.toCharArray();
    longestPallindromeAt(c, 0);
    return s.substring(start, end + 1);
  }

  private void longestPallindromeAt(char[] c, int p) {
    int a = p;
    int b = p;
    int n = c.length;
    if (p == n - 1 || (n - p) < (end - start + 1)/2) {
      return;
    }
    while (b < n - 1 && c[b] == c[b + 1]) {
      b++;
    }
    p = b;
    while (a > 0 && b < n - 1 && c[a - 1] == c[b + 1]) {
      a--;
      b++;
    }
    if (b - a > end - start) {
      end = b;
      start = a;
    }
    longestPallindromeAt(c, p + 1);
  }

  public String longestPalindrome(String s) {
    boolean[][] b = new boolean[s.length()][s.length()];

    for (int i = 0; i < s.length(); i++) {
      b[i][i] = true;
    }
    int start = 0, length = 0;
    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i - 1) == s.charAt(i)) {
        b[i - 1][i] = true;
        start = i - 1;
        length = 1;
      }
    }

    for (int ss = 2; ss < s.length(); ss++) {
      for (int i = 0; i < s.length() - ss; i++) {
        if (s.charAt(i) == s.charAt(i + ss) && b[i + 1][i + ss - 1]) {
          b[i][i + ss] = true;
          start = i;
          length = ss;
        }
      }
    }

    return s.substring(start, start + length + 1);
  }
}
