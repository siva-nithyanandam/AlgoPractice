package StacksAndQueues;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/island-perimeter/
 *
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water,
 * and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island.
 * One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * Output: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image above.
 * Example 2:
 *
 * Input: grid = [[1]]
 * Output: 4
 * Example 3:
 *
 * Input: grid = [[1,0]]
 * Output: 4
 *
 *
 * Constraints:
 *
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 100
 * grid[i][j] is 0 or 1.
 * There is exactly one island in grid.
 *
 */
public class IslandPerimeter {

    public static void main(String[] args) {
        IslandPerimeter o = new IslandPerimeter();
        System.out.println(o.islandPerimeter(new int[][]{{1,0},{1,1}}));
    }

    public int islandPerimeter_faster(int[][] grid) {
        int ans = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    ans += 4;

                    if(i > 0 && grid[i - 1][j] == 1)
                        ans -= 2;
                    if(j > 0 && grid[i][j -1] == 1)
                        ans -= 2;
                }
            }
        }

        return ans;
    }

    int islandPerimeter(int[][] grid) {

        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    q.add(new int[]{i, j});

                    while (!q.isEmpty()) {
                        int[] curr = q.remove();
                        if (set.contains(curr[0] * n + curr[1])) {
                            continue;
                        }
                        set.add(curr[0] * n + curr[1]);

                        grid[curr[0]][curr[1]] = -1;
                        for (int[] dir : dirs) {
                            if (isValid(curr[0]+dir[0], curr[1]+dir[1], m, n)) {
                                if (grid[curr[0]+dir[0]][curr[1]+dir[1]] == 1) {
                                    q.add(new int[]{curr[0]+dir[0], curr[1]+dir[1]});
                                    continue;
                                } else if (grid[curr[0]+dir[0]][curr[1]+dir[1]] == -1) {
                                    continue;
                                }
                            }
                            res++;
                        }
                    }
                    return res;
                }
            }
        }
        return res;
    }

    private boolean isValid(int i, int j, int m, int n) {
        if (i < 0 || j < 0 || i == m || j == n) {
            return false;
        }
        return true;
    }
}
