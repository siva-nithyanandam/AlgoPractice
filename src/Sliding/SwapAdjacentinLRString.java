package Sliding;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/13/2021  4:31 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/problems/swap-adjacent-in-lr-string/
 *
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one
 * occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR".
 * Given the starting string start and the ending string end, return True if and only if there exists a sequence of
 * moves to transform one string to the other.
 *
 *
 *
 * Example 1:
 *
 * Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * Output: true
 * Explanation: We can transform start to end following these steps:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 * Example 2:
 *
 * Input: start = "X", end = "L"
 * Output: false
 * Example 3:
 *
 * Input: start = "LLR", end = "RRL"
 * Output: false
 * Example 4:
 *
 * Input: start = "XL", end = "LX"
 * Output: true
 * Example 5:
 *
 * Input: start = "XLLR", end = "LXLX"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= start.length <= 104
 * start.length == end.length
 * Both start and end will only consist of characters in 'L', 'R', and 'X'.
 */
public class SwapAdjacentinLRString {

  public static void main(String[] args) {
    SwapAdjacentinLRString o = new SwapAdjacentinLRString();
    System.out.println(o.canTransform_faster("XLLR", "LXLX"));
  }

  public boolean canTransform_faster(String start, String end) {
    char[] s = start.toCharArray();
    char[] e = end.toCharArray();

    if(s.length!=e.length)
      return false;

    int sIndex = 0;
    int eIndex = 0;
    int N = s.length;
    while(sIndex < N){
      while(sIndex < N && s[sIndex]=='X'){
        sIndex++;
      }

      while(eIndex < N && e[eIndex]=='X'){
        eIndex++;
      }

      if(sIndex==N && eIndex==N)
        return true;

      if(sIndex==N || eIndex==N)
        return false;

      if(s[sIndex]!=e[eIndex])
        return false;

      if(s[sIndex]=='L'){
        if(sIndex < eIndex)
          return false;
      }else if(s[sIndex]=='R'){
        if(sIndex > eIndex)
          return false;
      }
      sIndex++;
      eIndex++;
    }

    while(sIndex < N && s[sIndex]=='X'){
      sIndex++;
    }

    while(eIndex < N && e[eIndex]=='X'){
      eIndex++;
    }

    if(sIndex==N && eIndex==N)
      return true;

    if(sIndex==N || eIndex==N)
      return false;

    return true;
  }

  public boolean canTransform(String start, String end) {
    if (!start.replace("X", "").equals(end.replace("X", ""))) {
      return false;
    }

    char[] startChars = start.toCharArray();
    char[] endChars = end.toCharArray();
    int l = 0, r = 0;

    for (int i = 0; i < startChars.length; i++) {
      if (startChars[i] == 'R') {
        r++;
      }
      if (startChars[i] == 'L') {
        l--;
      }
      if (endChars[i] == 'R') {
        r--;
      }
      if (endChars[i] == 'L') {
        l++;
      }
      if (r > 0 && l > 0) {
        return false;
      }
      if (l < 0 || r < 0) {
        return false;
      }

    }
    return l == 0 && r == 0;
  }
}
