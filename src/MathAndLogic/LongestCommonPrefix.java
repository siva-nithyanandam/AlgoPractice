package MathAndLogic;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 *
 * Constraints:
 *
 * 0 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lower-case English letters.
 */
public class LongestCommonPrefix {

  public static void main(String[] args) {
    LongestCommonPrefix o = new LongestCommonPrefix();
    System.out.println(o.longestCommonPrefix(new String[]{"flower","flow","flight"}));
    System.out.println(o.longestCommonPrefix(new String[]{"dog","racecar","car"}));
    System.out.println(o.longestCommonPrefix(new String[]{"ab","a"}));
  }

  public String longestCommonPrefix_similar_internet(String[] strs) {
    if (strs.length == 0) {
      return "";
    }
    String prefix = strs[0];
    for (int i = 0; i < strs.length; i++) {
      while(strs[i].indexOf(prefix) != 0) {
        prefix = prefix.substring(0, prefix.length() - 1);
      }
    }
    return prefix;
  }

  public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) {
      return "";
    }
    for (int i = 0; i < strs[0].length(); i++) {
      char c = strs[0].charAt(i);
      for (int j = 1; j < strs.length; j++) {
        if (i >= strs[j].length()) {
          return strs[0].substring(0,i);
        } else if (strs[j].charAt(i) != c) {
          if (i > 0) {
            return strs[0].substring(0,i);
          } else {
            return "";
          }
        }
      }
    }
    return strs[0];
  }
}
