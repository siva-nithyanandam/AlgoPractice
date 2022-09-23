package TreesAndGraphs;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/4/2021  9:49 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/explore/interview/card/google/61/trees-and-graphs/3072/
 *
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down.
 * You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 *
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Example 3:
 *
 * Input: matrix = [[1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 */
public class LongestIncreasingPathinaMatrix {

  public static void main(String[] args) {
    LongestIncreasingPathinaMatrix o = new LongestIncreasingPathinaMatrix();
    System.out.println(o.longestIncreasingPath(new int[][]{{9,9,4},{6,6,8},{2,1,1}}));
  }

  public int longestIncreasingPath(int[][] matrix) {

    int m = matrix.length;
    int n = matrix[0].length;

    int[][] counter = new int[m][n];
    int res = 0, max;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (counter[i][j] == 0) {
          max = findGoodNeighbours(i, j, matrix, m, n, counter);
          res = Math.max(res, max);
        }
      }
    }
    return res;
  }

  private int findGoodNeighbours(int i, int j, int[][] matrix, int m, int n, int[][] counter) {

    if (counter[i][j] > 0) {
      return counter[i][j];
    }

    counter[i][j] = 1;

    int[][] dirs = new int[][]{{0,1}, {1,0}, {0,-1}, {-1, 0}};

    for (int[] dir : dirs) {
      int p = i+dir[0], q = j+dir[1];
      if (p >= 0 && q >= 0 && p < m && q < n && matrix[i][j] < matrix[p][q]) {
        int v = findGoodNeighbours(p, q, matrix, m, n, counter);
        counter[i][j] = Math.max(counter[i][j], 1+v);
      }
    }
    return counter[i][j];
  }
}
