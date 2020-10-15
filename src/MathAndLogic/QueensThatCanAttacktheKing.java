package MathAndLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/contest/weekly-contest-158/problems/queens-that-can-attack-the-king/
 *
 * On an 8x8 chessboard, there can be multiple Black Queens and one White King.
 *
 * Given an array of integer coordinates queens that represents the positions of the Black Queens,
 * and a pair of coordinates king that represent the position of the White King,
 * return the coordinates of all the queens (in any order) that can attack the King.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
 * Output: [[0,1],[1,0],[3,3]]
 * Explanation:
 * The queen at [0,1] can attack the king cause they're in the same row.
 * The queen at [1,0] can attack the king cause they're in the same column.
 * The queen at [3,3] can attack the king cause they're in the same diagnal.
 * The queen at [0,4] can't attack the king cause it's blocked by the queen at [0,1].
 * The queen at [4,0] can't attack the king cause it's blocked by the queen at [1,0].
 * The queen at [2,4] can't attack the king cause it's not in the same row/column/diagnal as the king.
 * Example 2:
 *
 *
 *
 * Input: queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
 * Output: [[2,2],[3,4],[4,4]]
 * Example 3:
 *
 *
 *
 * Input: queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
 * Output: [[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]
 *
 *
 * Constraints:
 *
 * 1 <= queens.length <= 63
 * queens[0].length == 2
 * 0 <= queens[i][j] < 8
 * king.length == 2
 * 0 <= king[0], king[1] < 8
 * At most one piece is allowed in a cell.
 */
public class QueensThatCanAttacktheKing {
    public static void main(String[] args) {
        QueensThatCanAttacktheKing o = new QueensThatCanAttacktheKing();
        List<List<Integer>> res;

        res = o.queensAttacktheKing(new int[][]  {{0,1},{1,0},{4,0},{0,4},{3,3},{2,4}}, new int[] {0, 0});
        printList(res);

        res = o.queensAttacktheKing(new int[][]  {{0,0},{1,1},{2,2},{3,4},{3,5},{4,4},{4,5}}, new int[] {3, 3});
        printList(res);
    }

    private static void printList(List<List<Integer>> res) {
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
        System.out.println();
    }

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[][] q = new boolean[8][8];
        for (int[] u : queens) {
            q[u[0]][u[1]] = true;
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                for (int r = king[0]+i, c = king[1]+j; r>=0 && r < 8 && c>=0 && c < 8; r+=i, c+=j) {
                    if (q[r][c]) {
                        List<Integer> re  = new ArrayList<>();
                        re.add(r);
                        re.add(c);
                        res.add(re);
                        break;
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> queensAttacktheKing1(int[][] queens, int[] king) {
        boolean[][] q = new boolean[8][8];
        for(int[] u : queens){
            q[u[0]][u[1]] = true;
        }
        List<List<Integer>> ret = new ArrayList<>();

        int r, c;
        for(int i = -1;i <= 1;i++){
            outer:
            for(int j = -1;j <= 1;j++){
                if(i == 0 && j == 0)continue;
                for(r = king[0] + i, c = king[1] + j;r >= 0 && r < 8 && c >= 0 && c < 8;r += i, c += j){
                    if(q[r][c]){
                        List<Integer> re  = new ArrayList<>();
                        re.add(r);
                        re.add(c);
                        ret.add(re);
                        continue outer;
                    }
                }
            }
        }

        return ret;
    }
}
