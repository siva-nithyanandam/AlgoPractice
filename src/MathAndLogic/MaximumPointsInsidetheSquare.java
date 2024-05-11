package MathAndLogic;
/**
 * @time 5/11/24-12:06 PM
 * @author sivaprakashnithyanandam
 */

import java.util.*;

/**
 * https://leetcode.com/problems/maximum-points-inside-the-square/description/
 * <p>
 * You are given a 2D array points and a string s where, points[i] represents the coordinates of point i, and s[i] represents the tag of point i.
 * <p>
 * A valid square is a square centered at the origin (0, 0), has edges parallel to the axes, and does not contain two points with the same tag.
 * <p>
 * Return the maximum number of points contained in a valid square.
 * <p>
 * Note:
 * <p>
 * A point is considered to be inside the square if it lies on or within the square's boundaries.
 * The side length of the square can be zero.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: points = [[2,2],[-1,-2],[-4,4],[-3,1],[3,-3]], s = "abdca"
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * The square of side length 4 covers two points points[0] and points[1].
 * <p>
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: points = [[1,1],[-2,-2],[-2,2]], s = "abb"
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * The square of side length 2 covers one point, which is points[0].
 * <p>
 * Example 3:
 * <p>
 * Input: points = [[1,1],[-1,-1],[2,-2]], s = "ccd"
 * <p>
 * Output: 0
 * <p>
 * Explanation:
 * <p>
 * It's impossible to make any valid squares centered at the origin such that it covers only one point among points[0] and points[1].
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, points.length <= 105
 * points[i].length == 2
 * -109 <= points[i][0], points[i][1] <= 109
 * s.length == points.length
 * points consists of distinct coordinates.
 * s consists only of lowercase English letters.
 */
public class MaximumPointsInsidetheSquare {

    public static void main(String[] args) {
        MaximumPointsInsidetheSquare o = new MaximumPointsInsidetheSquare();
        System.out.println(o.maxPointsInsideSquare(new int[][]{{2, 2}, {-1, -2}, {-4, 4}, {-3, 1}, {3, -3}}, "abdca"));
    }

    public int maxPointsInsideSquare(int[][] points, String s) {
        Set<Integer> seen = new HashSet<>();
        TreeMap<Integer, List<int[]>> atDist = new TreeMap<>();
        for (int i = 0; i < points.length; i++) {
            int[] pt = {points[i][0], points[i][1], s.charAt(i)};
            int dist = (int) Math.max((int) Math.abs(pt[0]), (int) Math.abs(pt[1])) * 2;
            atDist.computeIfAbsent(dist, nothing -> new ArrayList<>()).add(pt);
        }
        int last = 0;
        for (var e : atDist.entrySet()) {
            boolean fails = false;
            int with = 0;
            for (int[] pt : e.getValue()) {
                if (!seen.add(pt[2])) {
                    fails = true;
                } else with++;
            }
            if (fails) return last;
            last += with;
        }
        return last;
    }
}
