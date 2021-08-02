package MathAndLogic;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/30/2021  7:21 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/explore/interview/card/google/67/sql-2/472/
 *
 * You are given a license key represented as a string s that consists of only alphanumeric characters and dashes. The string is separated into n + 1 groups by n dashes. You are also given an integer k.
 *
 * We want to reformat the string s such that each group contains exactly k characters, except for the first group, which could be shorter than k but still must contain at least one character. Furthermore, there must be a dash inserted between two groups, and you should convert all lowercase letters to uppercase.
 *
 * Return the reformatted license key.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "5F3Z-2e-9-w", k = 4
 * Output: "5F3Z-2E9W"
 * Explanation: The string s has been split into two parts, each part has 4 characters.
 * Note that the two extra dashes are not needed and can be removed.
 * Example 2:
 *
 * Input: s = "2-5g-3-J", k = 2
 * Output: "2-5G-3J"
 * Explanation: The string s has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of English letters, digits, and dashes '-'.
 * 1 <= k <= 104
 */
public class LicenseKeyFormatting {

  public static void main(String[] args) {
    LicenseKeyFormatting o = new LicenseKeyFormatting();
    System.out.println(o.licenseKeyFormatting_faster("-5F3Z-2e-9-w", 4));
  }

  static final char toUpper = (char)('a' - 'A');

  public String licenseKeyFormatting_faster(String s, int k) {
    char[] str = s.toCharArray();
    int length = str.length;
    char[] buff = new char[length + length / k + 2];
    int pos = length - 1;
    int bpos = buff.length - 1;
    char c;
    while (pos >= 0) {
      for (int n = 0; pos >= 0; pos--) {
        if (str[pos] > 'Z') {
          buff[bpos--] = (char)(str[pos] - toUpper);
        } else if (str[pos] == '-') {
          continue;
        }
        else {
          buff[bpos--] = str[pos];
        }
        if (++n == k) {
          pos--;
          break;
        }
      }
      buff[bpos--] = '-';
    }
    bpos++;
    while (bpos < buff.length && buff[bpos] == '-') {
      bpos++;
    }
    return new String(buff, bpos, buff.length - bpos);
  }

  public String licenseKeyFormatting(String s, int k) {

    int i = s.length()-1;
    int tempK = k;
    StringBuilder res = new StringBuilder();

    while(i > -1) {
      if (s.charAt(i) != '-') {
        if (Character.isDigit(s.charAt(i))) {
          res.insert(0, s.charAt(i));
        } else {
          res.insert(0, Character.toUpperCase(s.charAt(i)));
        }
        tempK--;
      }
      i--;
      if (tempK == 0) {
        res.insert(0, '-');
        tempK = k;
      }
    }
    if (res.length() > 0 && res.charAt(0) == '-') {
      res.deleteCharAt(0);
    }
    return res.toString();
  }
}
