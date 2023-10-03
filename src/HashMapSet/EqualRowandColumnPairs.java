package HashMapSet;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 * Jul 19,2023 - 11:12 PM
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/equal-row-and-column-pairs/?envType=study-plan-v2&envId=leetcode-75
 *
 * Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.
 *
 * A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
 * Output: 1
 * Explanation: There is 1 equal row and column pair:
 * - (Row 2, Column 1): [2,7,7]
 * Example 2:
 *
 *
 * Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
 * Output: 3
 * Explanation: There are 3 equal row and column pairs:
 * - (Row 0, Column 0): [3,1,2,2]
 * - (Row 2, Column 2): [2,4,2,2]
 * - (Row 3, Column 2): [2,4,2,2]
 *
 *
 * Constraints:
 *
 * n == grid.length == grid[i].length
 * 1 <= n <= 200
 * 1 <= grid[i][j] <= 105
 */
public class EqualRowandColumnPairs {

    public static void main(String[] args) {
        EqualRowandColumnPairs o = new EqualRowandColumnPairs();
        System.out.println(o.equalPairs(new int[][]{{3,1,2,2},{1,4,4,5},{2,4,2,2},{2,4,2,2}}));
        System.out.println(o.equalPairs(new int[][]{{3,2,1},{1,7,6},{2,7,7}}));
    }

    public int equalPairs_fastest(int[][] grid) {
        Map<Integer,Integer> map = new HashMap<>();
        int ans = 0;
        for(int i=0; i<grid.length; i++){
            int hash = getRowHash(grid[i]);
            map.put(hash,map.getOrDefault(hash,0)+1);
        }

        for(int i=0; i<grid.length; i++){
            int hash = getColHash(grid,i);
            ans += map.getOrDefault(hash,0);
        }
        return ans;
    }

    public static int getRowHash(int[] grid){
        int prime = 29;
        for(int i=0; i<grid.length; i++){
            prime=prime*29+grid[i];
        }
        return prime;
    }

    public static int getColHash(int [][]grid, int j){
        int prime = 29;
        for(int i=0; i<grid.length; i++){
            prime=prime*29 + grid[i][j];
        }
        return prime;
    }

    public int equalPairs(int[][] grid) {

        int n = grid.length;
        Map<String, Integer> rowsMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int res = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(grid[i][j]);
                sb.append('-');
            }
            rowsMap.merge(sb.toString(), 1, Integer::sum);
            sb.setLength(0);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(grid[j][i]);
                sb.append('-');
            }
            if (rowsMap.containsKey(sb.toString())) {
                res += rowsMap.get(sb.toString());
            }
            sb.setLength(0);
        }
        return res;
    }
}
