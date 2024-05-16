package matrix;
/**
 * @time 5/15/24-7:21 PM
 * @author sivaprakashnithyanandam
 */

import java.util.*;

/**
 *https://leetcode.com/problems/find-the-safest-path-in-a-grid/description/?envType=daily-question&envId=2024-05-15
 *
 * You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:
 *
 * A cell containing a thief if grid[r][c] = 1
 * An empty cell if grid[r][c] = 0
 * You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell in the grid, including cells containing thieves.
 *
 * The safeness factor of a path on the grid is defined as the minimum manhattan distance from any cell in the path to any thief in the grid.
 *
 * Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).
 *
 * An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) and (r - 1, c) if it exists.
 *
 * The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes the absolute value of val.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
 * Output: 0
 * Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).
 * Example 2:
 *
 *
 * Input: grid = [[0,0,1],[0,0,0],[0,0,0]]
 * Output: 2
 * Explanation: The path depicted in the picture above has a safeness factor of 2 since:
 * - The closest cell of the path to the thief at cell (0, 2) is cell (0, 0). The distance between them is | 0 - 0 | + | 0 - 2 | = 2.
 * It can be shown that there are no other paths with a higher safeness factor.
 * Example 3:
 *
 *
 * Input: grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
 * Output: 2
 * Explanation: The path depicted in the picture above has a safeness factor of 2 since:
 * - The closest cell of the path to the thief at cell (0, 3) is cell (1, 2). The distance between them is | 0 - 1 | + | 3 - 2 | = 2.
 * - The closest cell of the path to the thief at cell (3, 0) is cell (3, 2). The distance between them is | 3 - 3 | + | 0 - 2 | = 2.
 * It can be shown that there are no other paths with a higher safeness factor.
 *
 *
 * Constraints:
 *
 * 1 <= grid.length == n <= 400
 * grid[i].length == n
 * grid[i][j] is either 0 or 1.
 * There is at least one thief in the grid.
 */
public class FindtheSafestPathinaGrid {

    public static void main(String[] args) {
        FindtheSafestPathinaGrid o = new FindtheSafestPathinaGrid();
        List<List<Integer>> grid = new ArrayList<>();
        List<Integer> p1 = new ArrayList<>();
        p1.add(0);
        p1.add(0);
        p1.add(1);
        grid.add(p1);

        List<Integer> p2 = new ArrayList<>();
        p2.add(0);
        p2.add(0);
        p2.add(0);
        grid.add(p2);

        List<Integer> p3 = new ArrayList<>();
        p3.add(0);
        p3.add(0);
        p3.add(0);
        grid.add(p3);

        System.out.println(o.maximumSafenessFactor(grid));
    }

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.get(n - 1).get(n - 1) == 1 || grid.get(0).get(0) == 1) {
            return 0;
        }

        int[][] dist = computeDistanceGrid(grid);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        boolean[][] vis = new boolean[n][n];

        int[] delr = {-1, 0, 1, 0};
        int[] delc = {0, 1, 0, -1};

        pq.offer(new int[]{dist[0][0], 0, 0});
        vis[0][0] = true;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int ds = curr[0];
            int i = curr[1];
            int j = curr[2];

            if (i == n - 1 && j == n - 1) {
                return ds;
            }

            for (int l = 0; l < 4; l++) {
                int nrow = i + delr[l];
                int ncol = j + delc[l];
                if (isValid(nrow, ncol, n) && grid.get(nrow).get(ncol) != 1 && !vis[nrow][ncol]) {
                    vis[nrow][ncol] = true;
                    int ds1 = dist[nrow][ncol];
                    pq.offer(new int[]{Math.min(ds, ds1), nrow, ncol});
                }
            }
        }

        return 0;
    }

    private boolean isValid(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    private int[][] computeDistanceGrid(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] distGrid = new int[n][n];
        for (int[] row : distGrid) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        Queue<int[]> q = new LinkedList<>();

        // Initialize the queue and distance for '0' cells
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid.get(i).get(j) == 1) {
                    q.offer(new int[]{i, j});
                    distGrid[i][j] = 0;
                }
            }
        }

        // Directions: up, down, left, right
        int[] delr = {-1, 1, 0, 0};
        int[] delc = {0, 0, -1, 1};

        // BFS to compute minimum distances
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];

            for (int i = 0; i < 4; i++) {
                int newX = x + delr[i];
                int newY = y + delc[i];
                if (isValid(newX, newY, n) && distGrid[newX][newY] == Integer.MAX_VALUE) {
                    distGrid[newX][newY] = distGrid[x][y] + 1;
                    q.offer(new int[]{newX, newY});
                }
            }
        }

        return distGrid;
    }

    public int maximumSafenessFactor_own_wrong(List<List<Integer>> grid) {

        int m = grid.size();
        int n = grid.get(0).size();

        List<int[]> thiefPlaces = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    thiefPlaces.add(new int[]{i, j});
                }
            }
        }
        return dfs(0, 0, grid, thiefPlaces, m, n);
    }

    private int dfs(int i, int j, List<List<Integer>> grid, List<int[]> thiefPlaces, int m, int n) {
        if (i < 0 || j < 0 || i == m || j == n || grid.get(i).get(j) == 1) {
            return 0;
        }
        if (i == m-1 && j == n-1) {
            return calculateSF(i, j, thiefPlaces);
        }

        grid.get(i).set(j, 1);

        int sf = calculateSF(i, j, thiefPlaces);

        int right = dfs(i, j+1, grid, thiefPlaces, m, n);
        int down = dfs(i+1, j, grid, thiefPlaces, m, n);
        int left = dfs(i-1, j, grid, thiefPlaces, m, n);
        int up = dfs(i-1, j, grid, thiefPlaces, m, n);

        grid.get(i).set(j, 0);

        int max = Math.max(right, Math.max(down, Math.max(left, up)));
        return Math.min(sf, max);
    }

    private int calculateSF(int i, int j, List<int[]> thiefPlaces) {
        int min = 500;
        for (int[] p : thiefPlaces) {
            min = Math.min(min, Math.abs(p[0]-i) + Math.abs(p[1]-j));
        }
        return min;
    }
}
