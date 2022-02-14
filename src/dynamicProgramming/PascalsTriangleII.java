package dynamicProgramming;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 10/25/2021  3:42 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle-ii/
 *
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 *
 *
 *
 * Example 1:
 *
 * Input: rowIndex = 3
 * Output: [1,3,3,1]
 * Example 2:
 *
 * Input: rowIndex = 0
 * Output: [1]
 * Example 3:
 *
 * Input: rowIndex = 1
 * Output: [1,1]
 *
 *
 * Constraints:
 *
 * 0 <= rowIndex <= 33
 *
 *
 * Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
 *
 * https://leetcode.com/problems/pascals-triangle-ii/discuss/38437/My-8-lines-java-solution-use-ArrayList
 */
public class PascalsTriangleII {
    public static void main(String[] args) {
        PascalsTriangleII o = new PascalsTriangleII();
        System.out.println(o.getRow(3));
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> ret = new LinkedList<Integer>();
        for (int row = 0; row <= rowIndex; row++) {
            ret.add(0, 1);
            for (int i = 1; i < row; i++)
                ret.set(i, ret.get(i) + ret.get(i + 1));
        }
        return ret;
    }
}
