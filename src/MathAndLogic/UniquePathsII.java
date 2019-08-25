package MathAndLogic;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to
 * reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * <p>
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * <p>
 * Note: m and n will be at most 100.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 * <p>
 * https://leetcode.com/problems/unique-paths-ii/
 */

/**
 * Use top-down approach - Count the numbers which reduces more if conditions.
 */
public class UniquePathsII {
    public static void main(String[] args) {
        UniquePathsII uniquePathsII = new UniquePathsII();
        int[][] obstacleGrid = new int[3][3];
        obstacleGrid[1][1] = 1;
        System.out.println(uniquePathsII.uniquePathsWithObstacles(obstacleGrid));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int maxM = obstacleGrid.length;
        int maxN = obstacleGrid[0].length;
        int[][] mem = new int[maxM][maxN];
        return uniquePathsWithObstacles(obstacleGrid, maxM, maxN, mem);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid, int maxM, int maxN, int[][] mem) {
        if (obstacleGrid[0][0] == 0) {
            mem[0][0] = 1;
        }
        for (int i = 1; i < maxM; i++) {
            if (obstacleGrid[i][0] == 0) {
                mem[i][0] = mem[i-1][0];
            }
        }
        for (int j = 1; j < maxN; j++) {
            if (obstacleGrid[0][j] == 0) {
                mem[0][j] = mem[0][j-1];
            }
        }
        for (int i = 1; i < maxM; i++) {
            for (int j = 1; j < maxN; j++) {
                if (obstacleGrid[i][j] == 0) {
                    mem[i][j] = mem[i-1][j] + mem[i][j-1];
                }
            }
        }
        return mem[maxM-1][maxN-1];
    }
}
