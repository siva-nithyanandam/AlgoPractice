package ShortestPath;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/28/2021  6:45 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/
 *
 * Given a m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
 * 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
 * 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
 * 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
 * 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
 * Notice that there could be some invalid signs on the cells of the grid which points outside the grid.
 *
 * You will initially start at the upper left cell (0,0). A valid path in the grid is a path which starts from the
 * upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid.
 * The valid path doesn't have to be the shortest.
 *
 * You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
 *
 * Return the minimum cost to make the grid have at least one valid path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
 * Output: 3
 * Explanation: You will start at point (0, 0).
 * The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
 * The total cost = 3.
 * Example 2:
 *
 *
 * Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
 * Output: 0
 * Explanation: You can follow the path from (0, 0) to (2, 2).
 * Example 3:
 *
 *
 * Input: grid = [[1,2],[4,3]]
 * Output: 1
 * Example 4:
 *
 * Input: grid = [[2,2,2],[2,2,2]]
 * Output: 3
 * Example 5:
 *
 * Input: grid = [[4]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 */
public class MinimumCosttoMakeatLeastOneValidPathinaGrid {

  public static void main(String[] args) {
    MinimumCosttoMakeatLeastOneValidPathinaGrid o = new MinimumCosttoMakeatLeastOneValidPathinaGrid();
    System.out.println(o.minCost_faster(new int[][]{{2,1,3},{3,2,2},{1,1,4}}));
  }

  int[] dx = {0, 0, 1, -1};
  int[] dy = {1, -1, 0, 0};
  int INF = (int) 1e9;

  public int minCost_faster(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    int[][] cost = new int[n][m];
    for (int i = 0; i < n; i++) {
      Arrays.fill(cost[i], INF);
    }

    Queue<int[]> queue = new LinkedList<>();
    dfs(0, 0, 0, cost, grid, queue);
    int minCost = 0;
    while (!queue.isEmpty()) {
      minCost++;
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] top = queue.poll();
        for (int k = 0; k < 4; k++) {
          dfs(top[0] + dx[k], top[1] + dy[k], minCost, cost, grid, queue);
        }
      }
    }
    return cost[n - 1][m - 1];
  }

  void dfs(int row, int col, int minCost, int[][] cost, int[][] grid, Queue<int[]> queue) {
    if (row < 0 || col < 0 || row >= grid.length || col >= grid[row].length
        || cost[row][col] != INF) {
      return;
    }
    int dir = grid[row][col] - 1;
    cost[row][col] = minCost;
    queue.add(new int[]{row, col});
    dfs(row + dx[dir], col + dy[dir], minCost, cost, grid, queue);
  }


  public int minCost(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    boolean[][] visited = new boolean[m][n];
    int[][] matrix = new int[m][n];

    PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);
    queue.add(new int[]{0,0,0});
    visited[0][0] = true;

    while (!queue.isEmpty()) {
      int[] pos = queue.poll();
      if (pos[0] == m-1 && pos[1] == n-1) {
        return matrix[m-1][n-1];
      }
      //Move Top
      if (pos[0] > 0 && grid[pos[0]][pos[1]] > 0 && grid[pos[0]][pos[1]] < 5) {
        int newVal = grid[pos[0]][pos[1]] == 4 ? matrix[pos[0]][pos[1]] : matrix[pos[0]][pos[1]]+1;
        if (!visited[pos[0] - 1][pos[1]] || matrix[pos[0] - 1][pos[1]] > newVal) {
          matrix[pos[0] - 1][pos[1]] = newVal;
          queue.add(new int[]{pos[0]-1, pos[1], newVal});
          visited[pos[0] - 1][pos[1]] = true;
        }
      }
      //Move Down
      if (pos[0] < m-1 && grid[pos[0]][pos[1]] > 0 && grid[pos[0]][pos[1]] < 5) {
        int newVal = grid[pos[0]][pos[1]] == 3 ? matrix[pos[0]][pos[1]] : matrix[pos[0]][pos[1]]+1;
        if (!visited[pos[0] + 1][pos[1]] || matrix[pos[0] + 1][pos[1]] > newVal) {
          matrix[pos[0] + 1][pos[1]] = newVal;
          queue.add(new int[]{pos[0]+1, pos[1], newVal});
          visited[pos[0] + 1][pos[1]] = true;
        }
      }

      //Move Left
      if (pos[1] > 0 && grid[pos[0]][pos[1]] > 0 && grid[pos[0]][pos[1]] < 5) {
        int newVal = grid[pos[0]][pos[1]] == 2 ? matrix[pos[0]][pos[1]] : matrix[pos[0]][pos[1]]+1;
        if (!visited[pos[0]][pos[1]-1] || matrix[pos[0]][pos[1]-1] > newVal) {
          matrix[pos[0]][pos[1]-1] = newVal;
          queue.add(new int[]{pos[0], pos[1]-1, newVal});
          visited[pos[0]][pos[1]-1] = true;
        }
      }

      //Move Right
      if (pos[1] < n-1 && grid[pos[0]][pos[1]] > 0 && grid[pos[0]][pos[1]] < 5) {
        int newVal = grid[pos[0]][pos[1]] == 1 ? matrix[pos[0]][pos[1]] : matrix[pos[0]][pos[1]]+1;
        if (!visited[pos[0]][pos[1]+1] || matrix[pos[0]][pos[1]+1] > newVal) {
          matrix[pos[0]][pos[1]+1] = newVal;
          queue.add(new int[]{pos[0], pos[1]+1, newVal});
          visited[pos[0]][pos[1]+1] = true;
        }
      }
    }
    return -1;
  }
}
