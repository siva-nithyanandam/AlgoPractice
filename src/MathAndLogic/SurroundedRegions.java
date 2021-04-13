package MathAndLogic;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/problems/surrounded-regions/
 *
 * Given an m x n matrix board containing 'X' and 'O', capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * Explanation: Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
 * Example 2:
 *
 * Input: board = [["X"]]
 * Output: [["X"]]
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is 'X' or 'O'.
 */
public class SurroundedRegions {

  public static void main(String[] args) {
    SurroundedRegions o = new SurroundedRegions();
    System.out.println();
  }

  public void solve_faster(char[][] board) {
    if(board==null || board.length==0) return;
    for(int i=0;i<board.length;i++) {
      travel(board, i, 0);
      travel(board, i, board[0].length-1);
    }
    for(int j=0;j<board[0].length;j++) {
      travel(board, 0, j);
      travel(board, board.length-1, j);
    }
    change('O', 'X', board);
    change('1', 'O', board);
  }
  private void travel(char[][] board,int i,int j) {
    if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!='O') return;
    board[i][j]='1';
    travel(board, i+1, j);
    travel(board, i-1, j);
    travel(board, i, j+1);
    travel(board, i, j-1);
  }
  private void change(char from,char to,char[][] board) {
    for(int i=0;i<board.length;i++) {
      for(int j=0;j<board[0].length;j++) {
        if(board[i][j]==from) board[i][j]=to;
      }
    }
  }

  public void solve(char[][] board) {

    int m = board.length;
    int n = board[0].length;
    boolean[][] visited = new boolean[m][n];

    for (int i = 0; i < n; i++) {
      markOpens(0, i, m, n, board, visited);
    }
    for (int i = 0; i < m; i++) {
      markOpens(i, n-1, m, n, board, visited);
    }
    for (int i = 0; i < n; i++) {
      markOpens(m-1, i, m, n, board, visited);
    }
    for (int i = 0; i < m; i++) {
      markOpens(i, 0, m, n, board, visited);
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'T') {
          board[i][j] = 'O';
        } else if (board[i][j] == 'O') {
          board[i][j] = 'X';
        }
      }
    }
  }

  private void markOpens(int i, int j, int m, int n, char[][] board, boolean[][] visited) {
    if (i < 0 || i == m || j < 0 || j == n || visited[i][j]) {
      return;
    }
    visited[i][j] = true;
    if (board[i][j] == 'O') {
      board[i][j] = 'T';
      markOpens(i, j+1, m, n, board, visited);
      markOpens(i, j-1, m, n, board, visited);
      markOpens(i+1, j, m, n, board, visited);
      markOpens(i-1, j, m, n, board, visited);
    }
  }
}
