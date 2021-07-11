package recursion;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/5/2021  4:15 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/problems/android-unlock-patterns/
 *
 * Android devices have a special lock screen with a 3 x 3 grid of dots. Users can set an "unlock pattern" by connecting the dots in a specific sequence, forming a series of joined line segments where each segment's endpoints are two consecutive dots in the sequence. A sequence of k dots is a valid unlock pattern if both of the following are true:
 *
 * All the dots in the sequence are distinct.
 * If the line segment connecting two consecutive dots in the sequence passes through the center of any other dot, the other dot must have previously appeared in the sequence. No jumps through the center non-selected dots are allowed.
 * For example, connecting dots 2 and 9 without dots 5 or 6 appearing beforehand is valid because the line from dot 2 to dot 9 does not pass through the center of either dot 5 or 6.
 * However, connecting dots 1 and 3 without dot 2 appearing beforehand is invalid because the line from dot 1 to dot 3 passes through the center of dot 2.
 * Here are some example valid and invalid unlock patterns:
 *
 *
 *
 * The 1st pattern [4,1,3,6] is invalid because the line connecting dots 1 and 3 pass through dot 2, but dot 2 did not previously appear in the sequence.
 * The 2nd pattern [4,1,9,2] is invalid because the line connecting dots 1 and 9 pass through dot 5, but dot 5 did not previously appear in the sequence.
 * The 3rd pattern [2,4,1,3,6] is valid because it follows the conditions. The line connecting dots 1 and 3 meets the condition because dot 2 previously appeared in the sequence.
 * The 4th pattern [6,5,4,1,9,2] is valid because it follows the conditions. The line connecting dots 1 and 9 meets the condition because dot 5 previously appeared in the sequence.
 * Given two integers m and n, return the number of unique and valid unlock patterns of the Android grid lock screen that consist of at least m keys and at most n keys.
 *
 * Two unlock patterns are considered unique if there is a dot in one sequence that is not in the other, or the order of the dots is different.
 *
 *
 *
 * Example 1:
 *
 * Input: m = 1, n = 1
 * Output: 9
 * Example 2:
 *
 * Input: m = 1, n = 2
 * Output: 65
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 9
 */
public class AndroidUnlockPatterns {

  public static void main(String[] args) {
    AndroidUnlockPatterns o = new AndroidUnlockPatterns();
    System.out.println(o.numberOfPatterns_fastest(2, 3));
  }

  int[][] skips;
  public int numberOfPatterns_fastest(int m, int n) {
    skips = new int[10][10];
    skips[1][3] = skips[3][1] = 2;
    skips[4][6] = skips[6][4] = 5;
    skips[7][9] = skips[9][7] = 8;
    skips[1][7] = skips[7][1] = 4;
    skips[2][8] = skips[8][2] = 5;
    skips[3][9] = skips[9][3] = 6;
    skips[1][9] = skips[9][1] = skips[3][7] = skips[7][3] = 5;

    boolean[] visited = new boolean[10];
    int total = 0;
    total += dfs(m, n, 1, visited, 1) * 4;
    total += dfs(m, n, 2, visited, 1) * 4;
    total += dfs(m, n, 5, visited, 1);
    return total;
  }


  int dfs(int m, int n, int curPos, boolean[] visited, int len) {

    int total = 0;
    if(len >= m && len <= n) {
      total = 1;
    }

    if(len >= n) {
      return 1;
    }

    visited[curPos] = true;   //Can not put in the beginning, because line 30 return
    for(int i=1; i<=9; i++) {
      if(!visited[i] && (skips[curPos][i] == 0 || visited[skips[curPos][i]])) {
        total += dfs(m, n, i, visited, len+1);
      }
    }

    visited[curPos] = false;
    return total;
  }

  public int numberOfPatterns_faster(int m, int n) {
    return helper(m, n, new boolean[3][3], 0, 0, 1) * 4 + helper(m, n, new boolean[3][3], 0, 1, 1) * 4 + helper(m, n, new boolean[3][3], 1, 1, 1);
  }

  private int helper(int m, int n, boolean[][] visited, int x, int y, int len) {
    if (len == n) {
      return 1;
    }
    int cnt = 0;
    if (len >= m) {
      cnt++;
    }
    visited[x][y] = true;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int dx = Math.abs(i - x);
        int dy = Math.abs(j - y);
        boolean jump = dx % 2 == 0 && dy % 2 == 0;
        if (!visited[i][j] && (!jump || visited[(i + x) / 2][(j + y) / 2])) {
          cnt += helper(m, n, visited, i, j, len + 1);
        }
      }
    }
    visited[x][y] = false;
    return cnt;
  }

  // cur: the current position
  // remain: the steps remaining
  int DFS(boolean vis[], int[][] skip, int cur, int remain) {
    if(remain < 0) return 0;
    if(remain == 0) return 1;
    vis[cur] = true;
    int rst = 0;
    for(int i = 1; i <= 9; ++i) {
      // If vis[i] is not visited and (two numbers are adjacent or skip number is already visited)
      if(!vis[i] && (skip[cur][i] == 0 || (vis[skip[cur][i]]))) {
        rst += DFS(vis, skip, i, remain - 1);
      }
    }
    vis[cur] = false;
    return rst;
  }

  public int numberOfPatterns(int m, int n) {
    // Skip array represents number to skip between two pairs
    int skip[][] = new int[10][10];
    skip[1][3] = skip[3][1] = 2;
    skip[1][7] = skip[7][1] = 4;
    skip[3][9] = skip[9][3] = 6;
    skip[7][9] = skip[9][7] = 8;
    skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
    boolean vis[] = new boolean[10];
    int rst = 0;
    // DFS search each length from m to n
    for(int i = m; i <= n; ++i) {
      rst += DFS(vis, skip, 1, i - 1) * 4;    // 1, 3, 7, 9 are symmetric
      rst += DFS(vis, skip, 2, i - 1) * 4;    // 2, 4, 6, 8 are symmetric
      rst += DFS(vis, skip, 5, i - 1);        // 5
    }
    return rst;
  }
}
