package bfs;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 10/4/2021  8:55 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/dungeon-game/
 *
 * The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of m x n rooms laid out in a 2D grid. Our valiant knight was initially positioned in the top-left room and must fight his way through dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons (represented by negative integers), so the knight loses health upon entering these rooms; other rooms are either empty (represented as 0) or contain magic orbs that increase the knight's health (represented by positive integers).
 *
 * To reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *
 * Return the knight's minimum initial health so that he can rescue the princess.
 *
 * Note that any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
 * Output: 7
 * Explanation: The initial health of the knight must be at least 7 if he follows the optimal path: RIGHT-> RIGHT -> DOWN -> DOWN.
 * Example 2:
 *
 * Input: dungeon = [[0]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == dungeon.length
 * n == dungeon[i].length
 * 1 <= m, n <= 200
 * -1000 <= dungeon[i][j] <= 1000
 */
public class DungeonGame {
    public static void main(String[] args) {
        DungeonGame o = new DungeonGame();
        System.out.println(o.calculateMinimumHP(new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}}));
    }

    public int calculateMinimumHP(int[][] dungeon) {

        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][] health = new int[m][n];

        // from bottom to origin

        for (int i=m-1;i>=0;i--) {
            for (int j=n-1;j>=0;j--) {
                if (i == m-1 && j == n-1) {
                    /*
                    1. health >= 1
                    2. health + dungeon >=1
                    */
                    health[i][j] = Math.max(1, 1 - dungeon[i][j]);
                } else if (i == m -1) {
                    /*
                    1. health >= 1
                    2. health + dungeon >= health_on_right
                    */
                    health[i][j] = Math.max(1, health[i][j+1] - dungeon[i][j]);
                } else if (j == n-1) {
                    health[i][j] = Math.max(1, health[i+1][j] - dungeon[i][j]);
                } else {
                    /*
                    1.health >=1
                    2. Since health should be min, then health + dungeon >= min(right, down)
                    */
                    health[i][j] = Math.max(1, Math.min(health[i+1][j], health[i][j+1]) - dungeon[i][j]);
                }
            }
        }

        return health[0][0];
    }

    public int calculateMinimumHP_TLE(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> b[2] - a[2]);
        pq.add(new int[]{0, 0, dungeon[0][0], dungeon[0][0]});
        int min, actual;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (curr[0] == m-1 && curr[1] == n-1) {
                if (curr[2] < 0) {
                    return curr[2] * -1;
                } else {
                    return 1;
                }
            }

            //Go right
            if (curr[1] < n-1) {
                actual = curr[3] + dungeon[curr[0]][curr[1]+1];
                min = Math.min(curr[2], actual);
                pq.add(new int[]{curr[0], curr[1]+1, min, actual});
            }

            //Go down
            if (curr[0] < m-1) {
                actual = curr[3] + dungeon[curr[0]+1][curr[1]];
                min = Math.min(curr[2], actual);
                pq.add(new int[]{curr[0]+1, curr[1], min, actual});
            }
        }
        return 0;
    }
}
