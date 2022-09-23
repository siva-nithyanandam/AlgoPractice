package dynamicProgramming;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 * Example 2:
 *
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        MinimumPathSum o = new MinimumPathSum();
        System.out.println(o.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
    }

    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[] arr = new int[n];

        for(int y=m-1; y>=0; y--){
            for(int x=n-1; x>=0; x--){

                if(y==m-1 && x==n-1){   //at bottom right
                    arr[x] = grid[y][x];

                }else if(y==m-1){   //at bottom
                    arr[x] = arr[x+1]+grid[y][x];

                }else if(x==n-1){   //at right
                    arr[x] += grid[y][x];

                }else{

                    if(arr[x]<arr[x+1]){    //compare below and right
                        arr[x] += grid[y][x];
                    }else{
                        arr[x] = arr[x+1]+grid[y][x];
                    }
                }
            }
        }

        return arr[0];
    }
}
