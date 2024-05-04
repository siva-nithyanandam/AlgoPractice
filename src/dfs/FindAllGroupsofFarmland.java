package dfs;

/**
 * @author sivaprakashnithyanandam
 * @time 4/19/24-10:28 PM
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-groups-of-farmland/description/?envType=daily-question&envId=2024-04-20
 *
 * You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1 represents a hectare of farmland.
 *
 * To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland. These rectangular areas are called groups. No two groups are adjacent, meaning farmland in one group is not four-directionally adjacent to another farmland in a different group.
 *
 * land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right corner of land is (m-1, n-1). Find the coordinates of the top left and bottom right corner of each group of farmland. A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is represented by the 4-length array [r1, c1, r2, c2].
 *
 * Return a 2D array containing the 4-length arrays described above for each group of farmland in land. If there are no groups of farmland, return an empty array. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: land = [[1,0,0],[0,1,1],[0,1,1]]
 * Output: [[0,0,0,0],[1,1,2,2]]
 * Explanation:
 * The first group has a top left corner at land[0][0] and a bottom right corner at land[0][0].
 * The second group has a top left corner at land[1][1] and a bottom right corner at land[2][2].
 * Example 2:
 *
 *
 * Input: land = [[1,1],[1,1]]
 * Output: [[0,0,1,1]]
 * Explanation:
 * The first group has a top left corner at land[0][0] and a bottom right corner at land[1][1].
 * Example 3:
 *
 *
 * Input: land = [[0]]
 * Output: []
 * Explanation:
 * There are no groups of farmland.
 *
 *
 * Constraints:
 *
 * m == land.length
 * n == land[i].length
 * 1 <= m, n <= 300
 * land consists of only 0's and 1's.
 * Groups of farmland are rectangular in shape.
 */
public class FindAllGroupsofFarmland {

    public static void main(String[] args) {
        FindAllGroupsofFarmland o = new FindAllGroupsofFarmland();
        Arrays.stream(o.findFarmland(new int[][]{{1,1,0,0,0,1},{1,1,0,0,0,0}}))
                .forEach(arr -> System.out.println(Arrays.toString(arr)));
        Arrays.stream(o.findFarmland(new int[][]{{1,0,0},{0,1,1},{0,1,1}}))
                .forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    public int[][] findFarmland(int[][] land) {

        List<int[]> farmLands = new ArrayList<>();
        int m = land.length;
        int n = land[0].length;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (land[r][c] == 1) {
                    int[] points = {r, c, 0, 0};
                    int endPoint = dfs(r, c, m, n, land);
                    points[2] = endPoint / n;
                    points[3] = endPoint % n;
                    farmLands.add(points);
                }
            }
        }
        return farmLands.toArray(new int[0][]);
    }

    private int dfs(int r, int c, int m, int n, int[][] land) {

        if (land[r][c] != 1) {
            return 0;
        }
        land[r][c] = 2;

        int rightPoint = 0;
        int downPoint = 0;

        //go right
        if (c < n-1) {
            rightPoint = dfs(r, c+1, m, n, land);
        }

        //go down
        if (r < m-1) {
            downPoint = dfs(r+1, c, m, n, land);
        }

        return Math.max(r*n+c, Math.max(rightPoint, downPoint));
    }

    public int[][] findFarmland_3ms(int[][] land) {
        List<int[]> result = new ArrayList<>();

        int m = land.length;
        int n = land[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 1) {
                    int[] coordinates = findFarmlandCoordinates(land, i, j);
                    result.add(coordinates);
                }
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    private int[] findFarmlandCoordinates(int[][] land, int row, int col) {
        int[] coordinates = new int[4];
        coordinates[0] = row;
        coordinates[1] = col;

        int m = land.length;
        int n = land[0].length;

        int r = row;
        int c = col;

        // Finding the bottom-right corner of the farmland group
        while (r < m && land[r][col] == 1) r++;
        while (c < n && land[row][c] == 1) c++;
        coordinates[2] = r - 1;
        coordinates[3] = c - 1;

        for (int i = row; i < r; i++) {
            for (int j = col; j < c; j++) {
                land[i][j] = 0;
            }
        }

        return coordinates;
    }
}
