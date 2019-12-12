package arrays;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * Example 1:
 * Input: [[1,1],[2,2],[3,3]]
 * Output: 3
 * Explanation:
 * ^
 * |
 * |        o
 * |     o
 * |  o
 * +------------->
 * 0  1  2  3  4
 * Example 2:
 * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 * Explanation:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 */
public class MaxPointsonaLine {

  public static void main(String[] args) {
    MaxPointsonaLine o = new MaxPointsonaLine();
    System.out.println(o.maxPoints(new int[][]{{2,3},{3,3},{-5,3}}));
    System.out.println(o.maxPoints(new int[][]{{84,250},{0,0},{1,0},{0,-70},{0,-70},{1,-1},{21,10},{42,90},{-42,-230}}));
    System.out.println(o.maxPoints(new int[][]{{0,0},{1,65536},{65536,0}}));
    System.out.println(o.maxPoints(new int[][]{{1,1},{1,1},{2,3}}));
    System.out.println(o.maxPoints(new int[][]{{1,1},{2,2},{3,3}}));
    System.out.println(o.maxPoints(new int[][]{{0,0},{1,0}}));
    System.out.println(o.maxPoints(new int[][]{{0,0},{0,1}}));
    System.out.println(o.maxPoints(new int[][]{{0,0},{1,1},{1,-1}}));
    System.out.println(o.maxPoints(new int[][]{{0,0},{-1,-1},{2,2}}));
    System.out.println(o.maxPoints(new int[][]{{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}}));
  }

  public int maxPoints(int[][] points) {
    int n = points.length;
    if (n == 0 || n < 3) {
      return n;
    }
    int max = 2;
    for (int i = 1; i < n; i++) {
      int count = 0;
      int x1 = points[i-1][0];
      int y1 = points[i-1][1];
      int x2 = points[i][0];
      int y2 = points[i][1];

      if (x1 == x2 && y1 == y2) {
        for (int j = 0; j < n; j++) {
          if (points[j][0] == x1 && points[j][1] == y1) {
            count++;
          }
        }
      } else {
        for (int j = 0; j < n; j++) {
          if ((long)(points[j][1] - y2) * (x2 - x1) == (long)(y2-y1) * (points[j][0] - x2)) {
            count++;
          }
        }
      }
      max = Math.max(max, count);
    }
    return max;
  }
}
