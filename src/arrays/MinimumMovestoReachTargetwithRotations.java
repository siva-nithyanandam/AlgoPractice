package arrays;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * https://leetcode.com/contest/weekly-contest-156/problems/minimum-moves-to-reach-target-with-rotations/
 *
 * In an n*n grid, there is a snake that spans 2 cells and starts moving from the top left corner at (0, 0) and (0, 1). The grid has empty cells represented by zeros and blocked cells represented by ones. The snake wants to reach the lower right corner at (n-1, n-2) and (n-1, n-1).
 * In one move the snake can:
 * Move one cell to the right if there are no blocked cells there. This move keeps the horizontal/vertical position of the snake as it is.
 * Move down one cell if there are no blocked cells there. This move keeps the horizontal/vertical position of the snake as it is.
 * Rotate clockwise if it's in a horizontal position and the two cells under it are both empty. In that case the snake moves from (r, c) and (r, c+1) to (r, c) and (r+1, c).
 *
 * Rotate counterclockwise if it's in a vertical position and the two cells to its right are both empty. In that case the snake moves from (r, c) and (r+1, c) to (r, c) and (r, c+1).
 *
 * Return the minimum number of moves to reach the target.
 * If there is no way to reach the target, return -1.
 *
 * Example 1:
 *
 * Input: grid = [[0,0,0,0,0,1],
 *                [1,1,0,0,1,0],
 *                [0,0,0,0,1,1],
 *                [0,0,1,0,1,0],
 *                [0,1,1,0,0,0],
 *                [0,1,1,0,0,0]]
 * Output: 11
 * Explanation:
 * One possible solution is [right, right, rotate clockwise, right, down, down, down, down, rotate counterclockwise, right, down].
 * Example 2:
 * Input: grid = [[0,0,1,1,1,1],
 *                [0,0,0,0,1,1],
 *                [1,1,0,0,0,1],
 *                [1,1,1,0,0,1],
 *                [1,1,1,0,0,1],
 *                [1,1,1,0,0,0]]
 * Output: 9
 *
 * Constraints:
 * 2 <= n <= 100
 * 0 <= grid[i][j] <= 1
 * It is guaranteed that the snake starts at empty cells.
 */
public class MinimumMovestoReachTargetwithRotations {

  public static void main(String[] args) {
    MinimumMovestoReachTargetwithRotations o = new MinimumMovestoReachTargetwithRotations();
    int[][] grid = new int[][]{{0,0,0,0,0,1},
        {1,1,0,0,1,0},
        {0,0,0,0,1,1},
        {0,0,1,0,1,0},
        {0,1,1,0,0,0},
        {0,1,1,0,0,0}};
    System.out.println(o.minimumMoves(grid));
  }

  public int minimumMoves(int[][] g) {
    int n = g.length, m = g[0].length;
    Queue<int[]> q = new ArrayDeque<>();
    q.add(new int[]{0, 0, 0});
    int[][][] d = new int[2][n][m];
    for(int i = 0;i < 2;i++){
      for(int j = 0;j < n;j++){
        Arrays.fill(d[i][j], 99999999);
      }
    }
    d[0][0][0] = 0;
    while(!q.isEmpty()){
      int[] cur = q.poll();
      int st = cur[0], r = cur[1], c = cur[2];
      int ur = r + (st == 1 ? 1 : 0), uc = c + (st == 1 ? 0 : 1);
      if(r == n-1 && c == n-2 && st == 0){
        return d[st][r][c];
      }
      // r
      if(uc+1 < m && g[r][c+1] == 0 && g[ur][uc+1] == 0 &&
          d[st][r][c+1] > d[st][r][c] + 1){
        d[st][r][c+1] = d[st][r][c] + 1;
        q.add(new int[]{st, r, c+1});
      }
      // d
      if(ur+1 < n && g[r+1][c] == 0 && g[ur+1][uc] == 0 &&
          d[st][r+1][c] > d[st][r][c] + 1){
        d[st][r+1][c] = d[st][r][c] + 1;
        q.add(new int[]{st, r+1, c});
      }
      int xr = r + (st == 0 ? 1 : 0), xc = c + (st == 0 ? 0 : 1);
      if(xr < n && xc < m && g[xr][xc] == 0 && g[r+1][c+1] == 0 &&
          d[st^1][r][c] > d[st][r][c] + 1){
        d[st^1][r][c] = d[st][r][c] + 1;
        q.add(new int[]{st^1, r, c});
      }
    }
    return -1;
  }
}
