package backtracking;/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/word-search-ii/
 *
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 * Example 2:
 *
 *
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] is a lowercase English letter.
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 */
public class WordSearchII {

  public static void main(String[] args) {
    WordSearchII o = new WordSearchII();
    System.out.println(o.findWords(new char[][]{{'o','a','a','n'},
    {'e','t','a','e'},{'i','h','k','r'},
    {'i','f','l','v'}}, new String[]{"oath","pea","eat","rain"}));
  }

  public List<String> findWords_faster(char[][] board, String[] words) {
    List<String> li=new ArrayList<String>();
    for(String word : words){
      if(exist(board,word)){
        li.add(word);
      }
    }
    return li;
  }

  public boolean exist(char[][] board, String word) {
    int row=board.length;
    int col=board[0].length;

    for(int i=0;i<row;i++){
      for(int j=0;j<col;j++){
        if(board[i][j]==word.charAt(0) && dfs(board,i,j,0,word)){
          return true;
        }
      }
    }
    return false;
  }

  public boolean dfs(char[][] board,int i,int j,int count,String word){
    if(count==word.length()){
      return true;
    }

    if(i< 0 || i>=board.length || j<0 || j>= board[0].length || board[i][j]!=word.charAt(count)){
      return false;
    }

    char temp=board[i][j];
    board[i][j]=' ';
    boolean found= dfs(board,i-1,j,count+1,word) ||
        dfs(board,i+1,j,count+1,word) ||
        dfs(board,i,j-1,count+1,word) ||
        dfs(board,i,j+1,count+1,word) ;
    board[i][j]=temp;
    return found;
  }

  public List<String> findWords(char[][] board, String[] words) {
    List<String> result = new ArrayList<>();
    for (String s : words) {
      findWords(s, board, result);
    }
    return result;
  }

  private void findWords(String s, char[][] board, List<String> result) {
    boolean[][] visited = new boolean[board.length][board[0].length];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (findWords(s, s.toCharArray(), board, i, j, 0, visited, result)) {
          return;
        }
      }
    }
  }

  private boolean findWords(String s, char[] chars, char[][] board, int m, int n, int idx, boolean[][] visited, List<String> result) {
    if (visited[m][n]) {
      return false;
    }
    boolean isMatch = false;
    if (board[m][n] == chars[idx]) {
      if (idx == chars.length-1) {
        result.add(s);
        return true;
      }
      visited[m][n] = true;
      //Go right
      if (n != board[0].length-1) {
        isMatch = findWords(s, chars, board, m, n+1, idx+1, visited, result);
      }
      //Go down
      if (!isMatch && m != board.length-1) {
        isMatch = findWords(s, chars, board, m+1, n, idx+1, visited, result);
      }
      //Go left
      if (!isMatch && n != 0) {
        isMatch = findWords(s, chars, board, m, n-1, idx+1, visited, result);
      }
      //Go top
      if (!isMatch && m != 0) {
        isMatch = findWords(s, chars, board, m-1, n, idx+1, visited, result);
      }
      visited[m][n] = false;
    }
    return isMatch;
  }
}
