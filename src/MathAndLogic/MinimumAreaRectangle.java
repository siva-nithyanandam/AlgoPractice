package MathAndLogic;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/12/2021  10:23 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/minimum-area-rectangle/
 * <p>
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from
 * these points, with sides parallel to the x and y axes.
 * <p>
 * If there isn't any rectangle, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]] Output: 4 Example 2:
 * <p>
 * Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]] Output: 2
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= points.length <= 500 0 <= points[i][0] <= 40000 0 <= points[i][1] <= 40000 All points are
 * distinct.
 */
public class MinimumAreaRectangle {

  public static void main(String[] args) {
    MinimumAreaRectangle o = new MinimumAreaRectangle();
    System.out.println(o.minAreaRect_better(new int[][]{{1, 1}, {1, 3}, {3, 1}, {3, 3}, {2, 2}}));
    System.out.println(o.minAreaRect_faster(new int[][]{{1, 1}, {1, 3}, {3, 1}, {3, 3}, {2, 2}}));
  }

  public int minAreaRect_better(int[][] points) {
    Set<Integer> seen = new HashSet<>();
    for (int[] p : points) {
      seen.add(p[0] * 40009 + p[1]);
    }
    int ans = 0, len = points.length;
    for (int i = 0; i < len; i++) {
      int a0 = points[i][0], a1 = points[i][1];
      for (int j = 0; j < i; j++) {
        int b0 = points[j][0], b1 = points[j][1],
            area = Math.abs((a0 - b0) * (a1 - b1));
        if (area > 0 && (ans == 0 || ans > area) && seen.contains(a0 * 40009 + b1) && seen
            .contains(b0 * 40009 + a1)) {
          ans = area;
        }
      }
      seen.add(a0 * 40009
          + a1); // so that each quadruplet will be dicovered only when the last index among them = i
    }
    return ans;
  }

  public int minAreaRect_faster(int[][] points) {

    Set<Integer> xSet = new HashSet<>(), ySet = new HashSet<>();
    for (int[] point : points) {
      xSet.add(point[0]);
      ySet.add(point[1]);
    }
    if (xSet.size() == points.length || ySet.size() == points.length) {
      return 0;
    }
    int ans = Integer.MAX_VALUE, s = xSet.size() < ySet.size() ? 0 : 1;

    Map<Integer, List<Integer>> x2y = new TreeMap<>();
    for (int[] point : points) {
      x2y.computeIfAbsent(point[1 - s], a -> new ArrayList<>()).add(point[s]);
    }
    Map<Integer, Integer> seenPairsY2X = new HashMap<>();
    for (Map.Entry<Integer, List<Integer>> entry : x2y.entrySet()) {
      int x2 = entry.getKey();
      List<Integer> ylist = entry.getValue();
      for (int y1 : ylist) {
        for (int y2 : ylist) {
          if (y1 < y2) {
            Integer x1 = seenPairsY2X.put(y1 * 40009 + y2, x2);
            if (x1 != null) {
              ans = Math.min(ans, (x2 - x1) * (y2 - y1));
            }
          }
        }
      }
    }
    return ans == Integer.MAX_VALUE ? 0 : ans;
  }
}
