package disjointset_unionfind;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 5/28/2022  1:57 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3845/
 *
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 * Example 2:
 *
 *
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] is 1 or 0.
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
public class NumberOfProvinces {

  public static void main(String[] args) {
    NumberOfProvinces o = new NumberOfProvinces();
    System.out.println(o.findCircleNum(new int[][]{{1,1,0},{1,1,0},{0,0,1}}));
  }

  public int findCircleNum(int[][] isConnected) {

    int n = isConnected.length;
    int[] res = {n};
    int[] rank = new int[n];
    int[] root = new int[n];

    for (int i = 1; i < n; i++) {
      root[i] = i;
    }

    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        if (i == j || isConnected[i][j] == 0) {
          continue;
        }
        union(i, j, root, rank, res);
      }
    }
    return res[0];
  }

  private void union(int i, int j, int[] root, int[] rank, int[] res) {
    int ri = find(i, root);
    int rj = find(j, root);

    if (ri == rj) {
      return;
    }
    res[0]--;
    if (rank[ri] > rank[rj]) {
      root[rj] = ri;
    } else if (rank[rj] > rank[ri]) {
      root[ri] = rj;
    } else {
      root[rj] = ri;
      rank[ri]++;
    }
  }

  private int find(int x, int[] root) {
    if (x == root[x]) {
      return x;
    }
    return root[x] = find(root[x], root);
  }
}
