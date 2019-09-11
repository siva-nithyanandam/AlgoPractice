package MathAndLogic;


/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * Example:
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */

/**
 * Idea is to use sliding window technique. Move left and right cursor according to matches found.
 * Optimized solution moves time to 1ms from 4ms.
 */
public class MinimumWindowSubstring {

  public static void main(String[] args) {
    MinimumWindowSubstring mws = new MinimumWindowSubstring();
    System.out.println(mws.minWindow("ADOBECODEBANC", "ABC"));
    System.out.println(mws.minWindow_optimized("ab", "b"));
  }

  public String minWindow_optimized(String s, String t) {
    int[] counter = new int[256];
    for (char c : t.toCharArray()) {
      counter[c]++;
    }
    char[] sArr = s.toCharArray();
    int target = t.length(), len = s.length()+1, start = -1, i = 0, j = 0;
    while (j < sArr.length) {
      if (counter[sArr[j++]]-- > 0) target--;
      if (target == 0 && j - i < len) {
        len = j - i;
        start = i;
      }
      while(i < j && (target == 0 || counter[sArr[i]] < 0)) {
        if (counter[sArr[i++]]++ == 0) target++;
      }
    }
    return start == -1 ? "" : s.substring(start, start + len);
  }

  public String minWindow(String s, String t) {
    int[] strArr = new int[256];
    int[] patArr = new int[256];
    for (int i = 0; i < t.length(); i++) {
      patArr[t.charAt(i)]++;
    }
    int count = t.length();
    int start = 0;
    int startIndex = -1;
    int minLength = Integer.MAX_VALUE;
    for (int i = start; i < s.length(); i++) {
      char c = s.charAt(i);
      strArr[c]++;
      if (patArr[c] == 0) {
        continue;
      }
      if (patArr[c] * strArr[c] >= strArr[c] * strArr[c]) {
        count--;
      }
      if (count == 0) {
        while(strArr[s.charAt(start)] > patArr[s.charAt(start)]) {
          strArr[s.charAt(start)]--;
          start++;
        }
        if (minLength > i - start + 1) {
          minLength = i - start + 1;
          startIndex = start;
        }
      }
    }
    if (minLength == Integer.MAX_VALUE) {
      return "";
    } else {
      return s.substring(startIndex, startIndex+minLength);
    }
  }
}
