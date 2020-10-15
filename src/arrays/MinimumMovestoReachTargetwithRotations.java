package arrays;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * https://leetcode.com/contest/weekly-contest-156/problems/minimum-moves-to-reach-target-with-rotations/
 * <p>
 * In an n*n grid, there is a snake that spans 2 cells and starts moving from the top left corner at
 * (0, 0) and (0, 1). The grid has empty cells represented by zeros and blocked cells represented by
 * ones. The snake wants to reach the lower right corner at (n-1, n-2) and (n-1, n-1). In one move
 * the snake can: Move one cell to the right if there are no blocked cells there. This move keeps
 * the horizontal/vertical position of the snake as it is. Move down one cell if there are no
 * blocked cells there. This move keeps the horizontal/vertical position of the snake as it is.
 * Rotate clockwise if it's in a horizontal position and the two cells under it are both empty. In
 * that case the snake moves from (r, c) and (r, c+1) to (r, c) and (r+1, c).
 * <p>
 * Rotate counterclockwise if it's in a vertical position and the two cells to its right are both
 * empty. In that case the snake moves from (r, c) and (r+1, c) to (r, c) and (r, c+1).
 * <p>
 * Return the minimum number of moves to reach the target. If there is no way to reach the target,
 * return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [[0,0,0,0,0,1], [1,1,0,0,1,0], [0,0,0,0,1,1], [0,0,1,0,1,0], [0,1,1,0,0,0],
 * [0,1,1,0,0,0]] Output: 11 Explanation: One possible solution is [right, right, rotate clockwise,
 * right, down, down, down, down, rotate counterclockwise, right, down]. Example 2: Input: grid =
 * [[0,0,1,1,1,1], [0,0,0,0,1,1], [1,1,0,0,0,1], [1,1,1,0,0,1], [1,1,1,0,0,1], [1,1,1,0,0,0]]
 * Output: 9
 * <p>
 * Constraints: 2 <= n <= 100 0 <= grid[i][j] <= 1 It is guaranteed that the snake starts at empty
 * cells.
 *
 * Snake board
 */
public class MinimumMovestoReachTargetwithRotations {

  private static final int HORIZONTAL = 0;
  private static final int VERTICAL = 1;

  public static void main(String[] args) {
    int s = 4;
    System.out.println(s ^ 1);
    MinimumMovestoReachTargetwithRotations o = new MinimumMovestoReachTargetwithRotations();
    int[][] grid1 = new int[][]
        {{0, 0, 0, 0, 0, 1},
            {1, 1, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 1, 0},
            {0, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 0, 0}};

    int[][] grid = new int[][]{{0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0}};
    System.out.println(o.minimumMoves(grid1));
  }

  public int minimumMoves(int[][] grid) {
    if (grid.length == 0) {
      return -1;
    }
    int n = grid.length;
    int m = grid[0].length;
    Queue<int[]> q = new ArrayDeque<>();
    q.add(new int[]{0, 0, HORIZONTAL});
    int[][][] dist = new int[2][n][m];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < m; k++) {
          dist[i][j][k] = 99999;
        }
      }
    }
    dist[HORIZONTAL][0][0] = 0;

    while (!q.isEmpty()) {
      int[] loc = q.poll();
      int r = loc[0];
      int c = loc[1];
      int pos = loc[2];
      int ur = pos == HORIZONTAL ? r : r + 1;
      int uc = pos == HORIZONTAL ? c + 1 : c;

      if (r == n - 1 && c == m - 2 && pos == HORIZONTAL) {
        return dist[pos][r][c];
      }

      //Slide right - Either Horizontal position or Vertical position
      if ((uc + 1 < m) && grid[r][c + 1] == 0 && grid[ur][uc + 1] == 0) {
        if (dist[pos][r][c + 1] > dist[pos][r][c] + 1) {
          dist[pos][r][c + 1] = dist[pos][r][c] + 1;
          q.add(new int[]{r, c + 1, pos});
        }
      }

      //Slide down - Either Horizontal position or Vertical position
      if ((ur + 1 < n) && grid[r + 1][c] == 0 && grid[ur + 1][uc] == 0) {
        if (dist[pos][r + 1][c] > dist[pos][r][c] + 1) {
          dist[pos][r + 1][c] = dist[pos][r][c] + 1;
          q.add(new int[]{r + 1, c, pos});
        }
      }

      int xr = r + (pos == HORIZONTAL ? 1 : 0);
      int xc = c + (pos == HORIZONTAL ? 0 : 1);
      if (xr < n && xc < m && grid[xr][xc] == 0 && grid[r + 1][c + 1] == 0) {
        if (dist[pos ^ 1][r][c] > dist[pos][r][c] + 1) {
          dist[pos ^ 1][r][c] = dist[pos][r][c] + 1;
          q.add(new int[]{r, c, pos ^ 1});
        }
      }
    }
    return -1;
  }

  public int minimumMoves2(int[][] grid) {
    int[][] vV = new int[grid.length][grid[0].length];
    int[][] vH = new int[grid.length][grid[0].length];
    return check(grid, 0, 0, false, vV, vH, 0);
  }

  private int check(int[][] grid, int row, int col, boolean up, int[][] vV, int[][] vH, int steps) {
    if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
      return -1;
    } else if (up && (row + 1 >= grid.length || grid[row][col] == 1 || grid[row + 1][col] == 1 || (
        vH[row][col] != 0 && steps >= vH[row][col]))) {
      return -1;
    } else if (!up && (col + 1 >= grid[0].length || grid[row][col] == 1 || grid[row][col + 1] == 1
        || (vV[row][col] != 0 && steps >= vV[row][col]))) {
      return -1;
    }

    if (row == grid.length - 1 && col == grid[0].length - 2 && up == false) {
      return steps;
    }

    if (up) {
      vH[row][col] = steps;
    } else {
      vV[row][col] = steps;
    }
    // System.out.println(row + " " + col + " " + steps + " " + up);

    int res = Integer.MAX_VALUE;
    int rt = check(grid, row, col + 1, up, vV, vH, steps + 1);
    if (rt != -1 && rt < res) {
      res = rt;
    }
    rt = check(grid, row + 1, col, up, vV, vH, steps + 1);
    if (rt != -1 && rt < res) {
      res = rt;
    }
    if (row + 1 < grid.length && col + 1 < grid[0].length && grid[row][col] != 1
        && grid[row + 1][col] != 1 && grid[row][col + 1] != 1 && grid[row + 1][col + 1] != 1) {
      rt = check(grid, row, col, !up, vV, vH, steps + 1);
      if (rt != -1 && rt < res) {
        res = rt;
      }
    }

    return res == Integer.MAX_VALUE ? -1 : res;
  }

  public int minimumMoves1(int[][] g) {
    int n = g.length, m = g[0].length;
    Queue<int[]> q = new ArrayDeque<>();
    q.add(new int[]{0, 0, 0});
    int[][][] d = new int[2][n][m];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < n; j++) {
        Arrays.fill(d[i][j], 99999999);
      }
    }
    d[0][0][0] = 0;
    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int st = cur[0], r = cur[1], c = cur[2];
      int ur = r + (st == 1 ? 1 : 0), uc = c + (st == 1 ? 0 : 1);
      if (r == n - 1 && c == n - 2 && st == 0) {
        return d[st][r][c];
      }
      // r
      if (uc + 1 < m && g[r][c + 1] == 0 && g[ur][uc + 1] == 0 &&
          d[st][r][c + 1] > d[st][r][c] + 1) {
        d[st][r][c + 1] = d[st][r][c] + 1;
        q.add(new int[]{st, r, c + 1});
      }
      // d
      if (ur + 1 < n && g[r + 1][c] == 0 && g[ur + 1][uc] == 0 &&
          d[st][r + 1][c] > d[st][r][c] + 1) {
        d[st][r + 1][c] = d[st][r][c] + 1;
        q.add(new int[]{st, r + 1, c});
      }
      int xr = r + (st == 0 ? 1 : 0), xc = c + (st == 0 ? 0 : 1);
      if (xr < n && xc < m && g[xr][xc] == 0 && g[r + 1][c + 1] == 0 &&
          d[st ^ 1][r][c] > d[st][r][c] + 1) {
        d[st ^ 1][r][c] = d[st][r][c] + 1;
        q.add(new int[]{st ^ 1, r, c});
      }
    }
    return -1;
  }
}
