package disjointset_unionfind;/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.stream.IntStream;

/**
 * https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3911/
 *
 * You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
 *
 * Return the number of connected components in the graph.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 * Output: 2
 * Example 2:
 *
 *
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2000
 * 1 <= edges.length <= 5000
 * edges[i].length == 2
 * 0 <= ai <= bi < n
 * ai != bi
 * There are no repeated edges.
 */
public class NumberofConnectedComponentsinanUndirectedGraph {

  int[] parent;
  int[] rank;
  int[] ans;

  public static void main(String[] args) {
    NumberofConnectedComponentsinanUndirectedGraph o = new NumberofConnectedComponentsinanUndirectedGraph();
    System.out.println(o.countComponents(5, new int[][]{{0,1},{1,2},{3,4}}));
  }

  public int countComponents(int n, int[][] edges) {

    parent = new int[n];
    rank = new int[n];
    ans = new int[]{n};

    IntStream.range(0,n).forEach(i -> parent[i] = i);

    for (int[] edge : edges) {
      union(edge[0], edge[1]);
    }
    return ans[0];
  }

  private int find(int node) {
    if (parent[node] == node) {
      return node;
    }
    return find(parent[node]);
  }

  private void union(int x, int y) {
    int px = find(x);
    int py = find(y);

    if (px != py) {
      ans[0]--;
      if (rank[px] > rank[py]) {
        parent[py] = px;
      } else if (rank[py] > rank[px]) {
        parent[px] = py;
      } else {
        parent[py] = px;
        rank[px]++;
      }
    }
  }
}
