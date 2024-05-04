package dynamicProgramming;
/**
 * @time 4/21/24-11:28 AM
 * @author sivaprakashnithyanandam
 */

import java.util.Arrays;

/**
 * You are given a 2D matrix grid of size m x n. In one operation, you can change the value of any cell to any non-negative number. You need to perform some operations such that each cell grid[i][j] is:
 *
 * Equal to the cell below it, i.e. grid[i][j] == grid[i + 1][j] (if it exists).
 * Different from the cell to its right, i.e. grid[i][j] != grid[i][j + 1] (if it exists).
 * Return the minimum number of operations needed.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,0,2],[1,0,2]]
 *
 * Output: 0
 *
 * Explanation:
 *
 *
 *
 * All the cells in the matrix already satisfy the properties.
 *
 * Example 2:
 *
 * Input: grid = [[1,1,1],[0,0,0]]
 *
 * Output: 3
 *
 * Explanation:
 *
 *
 *
 * The matrix becomes [[1,0,1],[1,0,1]] which satisfies the properties, by doing these 3 operations:
 *
 * Change grid[1][0] to 1.
 * Change grid[0][1] to 0.
 * Change grid[1][2] to 1.
 * Example 3:
 *
 * Input: grid = [[1],[2],[3]]
 *
 * Output: 2
 *
 * Explanation:
 *
 *
 *
 * There is a single column. We can change the value to 1 in each cell using 2 operations.
 *
 *
 *
 * Constraints:
 *
 * 1 <= n, m <= 1000
 * 0 <= grid[i][j] <= 9
 */
public class MinimumNumberofOperationstoSatisfyConditions {

    public static void main(String[] args) {
        MinimumNumberofOperationstoSatisfyConditions o = new MinimumNumberofOperationstoSatisfyConditions();
        System.out.println(o.minimumOperations(new int[][]{{1,3},{2,4}}));
        System.out.println(o.minimumOperations(new int[][]{{1,1,1},{0,0,0}}));
        System.out.println(o.minimumOperations(new int[][]{{1,0,2},{1,0,2}}));
    }

    public int minimumOperations(int[][] grid) {

        int totalRows = grid.length;
        int totalCols = grid[0].length;

        int[][] valueByCols = new int[totalCols][10];

        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                valueByCols[j][grid[i][j]]++;
            }
        }
        int[][] mem = new int[totalCols][11];
        Arrays.stream(mem).forEach(arr -> Arrays.fill(arr, -1));

        return dp(0, -1, totalRows, totalCols, valueByCols, mem);
    }

    private int dp(int currCol, int reserved, int totalRows, int totalCols, int[][] valueByCols, int[][] mem) {

        if (currCol == totalCols) {
            return 0;
        }

        if (mem[currCol][reserved+1] != -1) {
            return mem[currCol][reserved+1];
        }

        int res = Integer.MAX_VALUE;
        for (int x = 0; x < 10; x++) {
            if (x != reserved) {
                res = Math.min(res, totalRows - valueByCols[currCol][x] + dp(currCol+1, x, totalRows, totalCols, valueByCols, mem));
            }
        }
        mem[currCol][reserved+1] = res;
        return res;
    }


    public int minimumOperations_1(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[] dp = new int[10];
        {
            int[] f = new int[10];
            for (int i = 0; i < n; i++) {
                f[grid[i][0]]++;
            }
            for (int i = 0; i < 10; i++) {
                dp[i] = n - f[i];
            }
        }

        for(int i = 1;i < m;i++){
            int[] ndp = new int[10];
            Arrays.fill(ndp, 99999999);
            {
                int[] f = new int[10];
                for (int j = 0; j < n; j++) {
                    f[grid[j][i]]++;
                }
                for (int j = 0; j < 10; j++) {
                    for(int k = 0;k < 10;k++) {
                        if(k != j) {
                            ndp[j] = Math.min(ndp[j], dp[k] + n - f[j]);
                        }
                    }
                }
            }
            dp = ndp;
        }
        int ans = 99999999;
        for(int i = 0;i < 10;i++){
            ans = Math.min(ans, dp[i]);
        }
        return ans;
    }
}
