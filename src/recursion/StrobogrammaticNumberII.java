package recursion;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/5/2021  12:19 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/explore/interview/card/google/62/recursion-4/399/
 * <p>
 * Given an integer n, return all the strobogrammatic numbers that are of length n. You may return
 * the answer in any order.
 * <p>
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at
 * upside down).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2 Output: ["11","69","88","96"] Example 2:
 * <p>
 * Input: n = 1 Output: ["0","1","8"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 14
 */
public class StrobogrammaticNumberII {

  public static void main(String[] args) {
    StrobogrammaticNumberII o = new StrobogrammaticNumberII();
    System.out.println(o.findStrobogrammatic_logical(4));
    System.out.println(o.findStrobogrammatic(4));
  }

  List<String> result;

  public List<String> findStrobogrammatic_logical(int n) {
    result = new ArrayList<>();
    findStrobogrammaticFrom(new char[n], 0, n - 1);
    return result;
  }

  private void findStrobogrammaticFrom(char[] curRes, int l, int r) {
    if (l > r) {
      result.add(new String(curRes));
      return;
    }
    if (l == r) {
      curRes[l] = '0';
      result.add(new String(curRes));
      curRes[l] = '1';
      result.add(new String(curRes));
      curRes[l] = '8';
      result.add(new String(curRes));
      return;
    }
    if (l != 0) {
      curRes[l] = '0';
      curRes[r] = '0';
      findStrobogrammaticFrom(curRes, l + 1, r - 1);
    }

    curRes[l] = '1';
    curRes[r] = '1';
    findStrobogrammaticFrom(curRes, l + 1, r - 1);

    curRes[l] = '8';
    curRes[r] = '8';
    findStrobogrammaticFrom(curRes, l + 1, r - 1);

    curRes[l] = '6';
    curRes[r] = '9';
    findStrobogrammaticFrom(curRes, l + 1, r - 1);

    curRes[l] = '9';
    curRes[r] = '6';
    findStrobogrammaticFrom(curRes, l + 1, r - 1);
  }

  public List<String> findStrobogrammatic(int n) {
    return helper(n, n);
  }

  List<String> helper(int n, int m) {
    if (n == 0) {
      return new ArrayList<String>(Arrays.asList(""));
    }
    if (n == 1) {
      return new ArrayList<String>(Arrays.asList("0", "1", "8"));
    }

    List<String> list = helper(n - 2, m);

    List<String> res = new ArrayList<String>();

    for (int i = 0; i < list.size(); i++) {
      String s = list.get(i);

      if (n != m) {
        res.add("0" + s + "0");
      }

      res.add("1" + s + "1");
      res.add("6" + s + "9");
      res.add("8" + s + "8");
      res.add("9" + s + "6");
    }

    return res;
  }
}
