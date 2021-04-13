package MathAndLogic;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */
public class WordSearch {

  public static void main(String[] args) {
    WordSearch o = new WordSearch();
    System.out.println(o.exist_faster(new char[][]{{'A','B','C','E'},
        {'S','F','C','S'},{'A','D','E','E'}}, "ABCCED"));
    System.out.println(o.exist(new char[][]{{'A','B','C','E'},
        {'S','F','C','S'},{'A','D','E','E'}}, "ABCCED"));
  }

  public boolean exist_faster(char[][] board, String word) {
    int m = board.length, n = board[0].length;
    int end = word.length() - 1;
    boolean isExist = false;
    for (int i=0; i<m; i++) {
      for (int j=0; j<n; j++) {
        if (board[i][j] == word.charAt(end)) {
          //System.out.print("restart: " + i + ", " + j);
          boolean[][] isVisited = new boolean[m][n];
          isVisited[i][j] = true;
          isExist = backtrack(board, i, j, isVisited, word, end-1);
          //System.out.println("end: " + isExist);
          if (isExist) return true;
        }
      }
    }
    return isExist;
  }

  boolean backtrack(char[][] board, int i, int j, boolean[][] isVisited, String word, int k) {
    //System.out.println("");
    //System.out.println("(" + i + ", " + j + ") - " + board[i][j] + ": " + k);
    if (k < 0) return true;
    int m = board.length, n = board[0].length;
    boolean isExist = false;
    for (int p=i-1; p<=i+1; p++) {
      if (p < 0 || p >= m) continue;
      for (int q=j-1; q<=j+1; q++) {
        if (q < 0 || q >= n) continue;
        //System.out.print("(" + p + "," + q + "):" + board[p][q] + ", " + isVisited[p][q] + "|| ");

        if (isVisited[p][q]) continue;
        if (p != i && q != j) continue;
        if (board[p][q] == word.charAt(k)) {
          isVisited[p][q] = true;
          if (k == 0) return true;
          isExist = backtrack(board, p, q, isVisited, word, k-1);
          if (isExist) return true;
          isVisited[p][q] = false;
        }
      }
    }
    return isExist;
  }

  public boolean exist(char[][] board, String word) {
    boolean[][] routes = new boolean[board.length][board[0].length];
    char[] chars = word.toCharArray();
    for (int m = 0; m < board.length; m++) {
      for (int n = 0; n < board[0].length; n++) {
        if (exist(board, routes, chars, m, n, 0)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean exist(char[][] board, boolean[][] routes, char[] chars, int m, int n, int pos) {

    if (pos == chars.length) {
      return true;
    } else if (m == -1 || n == -1 || m == board.length || n == board[0].length || routes[m][n] == true) {
      return false;
    }
    routes[m][n] = true;

    if (board[m][n] == chars[pos]) {
      //Move right
      if (exist(board, routes, chars, m, n+1, pos+1)) {
        return true;
      }
      //Move down
      else if (exist(board, routes, chars, m+1, n, pos+1)) {
        return true;
      }
      //Move left
      else if (exist(board, routes, chars, m, n-1, pos+1)) {
        return true;
      }
      //Move top
      else if (exist(board, routes, chars, m-1, n, pos+1)) {
        return true;
      }
    }
    routes[m][n] = false;
    return false;
  }

  public boolean exist_4ms(char[][] board, String word) {
    if (word == null || word.length() == 0) {
      return true;
    }
    char[] chs = word.toCharArray();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if(dfs(board, chs, 0, i, j)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean dfs(char[][] board, char[] words, int idx, int x, int y) {
    if (idx == words.length) {
      return true;
    }
    if (x < 0 || x == board.length || y < 0 || y == board[0].length) {
      return false;
    }
    if (board[x][y] != words[idx]) {
      return false;
    }
    board[x][y] ^= 256;
    boolean exist = dfs(board, words, idx + 1, x, y + 1) ||
        dfs(board, words, idx + 1, x, y - 1) || dfs(board, words, idx + 1, x + 1, y) ||
        dfs(board, words, idx + 1, x - 1, y) ;
    board[x][y] ^= 256;
    return exist;
  }
}
