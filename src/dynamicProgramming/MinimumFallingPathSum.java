package dynamicProgramming;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 10/25/2021  4:57 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-falling-path-sum/
 *
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 *
 * A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * Output: 13
 * Explanation: There are two falling paths with a minimum sum underlined below:
 * [[2,1,3],      [[2,1,3],
 *  [6,5,4],       [6,5,4],
 *  [7,8,9]]       [7,8,9]]
 * Example 2:
 *
 * Input: matrix = [[-19,57],[-40,-5]]
 * Output: -59
 * Explanation: The falling path with a minimum sum is underlined below:
 * [[-19,57],
 *  [-40,-5]]
 * Example 3:
 *
 * Input: matrix = [[-48]]
 * Output: -48
 *
 *
 * Constraints:
 *
 * n == matrix.length
 * n == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 */
public class MinimumFallingPathSum {
    public static void main(String[] args) {
        MinimumFallingPathSum o = new MinimumFallingPathSum();
        System.out.println(o.minFallingPathSum(new int[][]{{100,-42,-46,-41},{31,97,10,-10},{-58,-51,82,89},{51,81,69,-51}}));
        System.out.println(o.minFallingPathSum(new int[][]{{2,1,3},{6,5,4},{7,8,9}}));
    }

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;

        int[] mem = new int[n*n];
        Arrays.fill(mem, Integer.MAX_VALUE);
        int min = Integer.MAX_VALUE;
        int subMin;

        for (int i = 0; i < n; i++) {
            subMin = helper(matrix, 0, i, mem, n);
            if (subMin < min) {
                min = subMin;
            }
        }

        return min;
    }

    private int helper(int[][] matrix, int row, int col, int[] mem, int n) {
        if (row == n-1) {
            return matrix[row][col];
        }

        if (mem[row * n + col] != Integer.MAX_VALUE) {
            return mem[row * n + col];
        }

        int min = Integer.MAX_VALUE;
        int subMin;

        if (col > 0) {
            subMin = helper(matrix, row+1, col-1, mem, n);
            if (subMin < min) {
                min = subMin;
            }
        }

        subMin = helper(matrix, row+1, col, mem, n);
        if (subMin < min) {
            min = subMin;
        }

        if (col < n-1) {
            subMin = helper(matrix, row+1, col+1, mem, n);
            if (subMin < min) {
                min = subMin;
            }
        }

        mem[row * n + col] = min + matrix[row][col];
        return mem[row * n + col];
    }
}
