package MathAndLogic;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle/
 *
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 *
 *
 *
 * Example 1:
 *
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * Example 2:
 *
 * Input: numRows = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= numRows <= 30
 */
public class PascalsTriangle {

  public static void main(String[] args) {
    PascalsTriangle o = new PascalsTriangle();
    o.generate(5);
  }

  public List<List<Integer>> generate(int numRows) {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> subRes = new ArrayList<>();
    subRes.add(1);
    result.add(subRes);

    for (int i = 2; i <= numRows; i++) {
      subRes = new ArrayList<>();
      subRes.add(1);
      int prev = 1;
      for (int j = 1; j < i-1; j++) {
        prev = prev * (i - j)/j;
        subRes.add(prev);
      }
      subRes.add(1);
      result.add(subRes);
    }
    return result;
  }
}
