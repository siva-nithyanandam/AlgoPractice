package MathAndLogic;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid
 * (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 *
 * Example 1:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 * Example 2:
 *
 * Input: m = 7, n = 3
 * Output: 28
 *
 * https://leetcode.com/problems/unique-paths/
 */
public class UniquePaths {

    public static void main(String[] args) {
        System.out.println(uniquePaths(3,2));
        System.out.println(uniquePaths(7,3));
    }

    private static int uniquePaths(int m, int n) {
        int[][] mem = new int[m][n];
        return uniquePaths(0, 0, m-1, n-1, mem);
    }

    private static int uniquePaths(int m, int n, int maxM, int maxN, int[][] mem) {
        if (mem[m][n] != 0) {
            return mem[m][n];
        }
        if (m == maxM && n == maxN) {
            mem[m][n] = 1;
            return 1;
        }
        int downVal = 0, rightVal = 0;
        //Move down
        if (m < maxM) {
            downVal = uniquePaths(m+1, n, maxM, maxN, mem);
        }
        if (n < maxN) {
            rightVal = uniquePaths(m, n+1, maxM, maxN, mem);
        }
        mem[m][n] = downVal + rightVal;
        return mem[m][n];
    }
}
