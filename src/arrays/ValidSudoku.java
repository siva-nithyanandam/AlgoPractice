package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according
 * to the following rules:
 * <p>
 * Each row must contain the digits 1-9 without repetition. Each column must contain the digits 1-9
 * without repetition. Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9
 * without repetition. Note:
 * <p>
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable. Only the filled
 * cells need to be validated according to the mentioned rules.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [["5","3",".",".","7",".",".",".","."] ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."] ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"] ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."] ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]] Output: true Example 2:
 * <p>
 * Input: board = [["8","3",".",".","7",".",".",".","."] ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."] ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"] ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."] ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]] Output: false Explanation: Same as Example 1, except with
 * the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3
 * sub-box, it is invalid.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * board.length == 9 board[i].length == 9 board[i][j] is a digit or '.'
 */
public class ValidSudoku {

  public static void main(String[] args) {
    ValidSudoku o = new ValidSudoku();

    char[][] board =
        {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
            , {'6', '5', '.', '1', '9', '5', '.', '.', '.'}
            , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
            , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
            , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
            , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
            , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
            , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
            , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
    System.out.println(o.isValidSudoku_also_fast(board));

    board = new char[][]
        {{'8', '3', '.', '.', '7', '.', '.', '.', '.'}
            , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
            , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
            , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
            , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
            , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
            , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
            , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
            , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
    System.out.println(o.isValidSudoku(board));
  }

  public boolean isValidSudoku_also_fast(char[][] board) {
    Map<Integer, Integer> seen = new HashMap<>();

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] != '.') {
          int num = (int) board[i][j] - (int) '0';

          // construct the binary number
          int val = (1 << (18 + i)) + (1 << (9 + j)) + (1 << (i / 3 * 3 + j / 3));
          if (seen.containsKey(num)) {
            int idx = seen.get(num);

            if ((idx & val) > 0) {
              return false;
            }
            seen.put(num, idx ^ val);
          } else {
            seen.put(num, val);
          }
        }
      }
    }
    return true;
  }

  public boolean isValidSudoku(char[][] board) {
    boolean isValid = true;
    for (int i = 0; i < 9 && isValid; i++) {
      isValid = isValidRow(board, i) && isValidCol(board, i);
    }
    for (int i = 0; i < 9 && isValid; i = i + 3) {
      for (int j = 0; j < 9 && isValid; j = j + 3) {
        isValid = isValidBox(board, i, j);
      }
    }
    return isValid;
  }

  private boolean isValidRow(char[][] board, int row) {
    int[] arr = new int[10];
    for (int i = 0; i < 9; i++) {
      if (board[row][i] != '.' && arr[board[row][i] - '0']++ > 0) {
        return false;
      }
    }
    return true;
  }

  private boolean isValidCol(char[][] board, int col) {
    int[] arr = new int[10];
    for (int i = 0; i < 9; i++) {
      if (board[i][col] != '.' && arr[board[i][col] - '0']++ > 0) {
        return false;
      }
    }
    return true;
  }

  private boolean isValidBox(char[][] board, int row, int col) {
    int[] arr = new int[10];
    for (int i = row; i < row + 3; i++) {
      for (int j = col; j < col + 3; j++) {
        if (board[i][j] != '.' && arr[board[i][j] - '0']++ > 0) {
          return false;
        }
      }
    }
    return true;
  }
}
