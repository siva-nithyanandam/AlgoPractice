package ShortestPath;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/31/2021  3:56 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/
 *
 * There is an undirected weighted connected graph. You are given a positive integer n which denotes that the
 * graph has n nodes labeled from 1 to n, and an array edges where each edges[i] = [ui, vi, weighti] denotes that there is an edge between nodes ui and vi with weight equal to weighti.
 *
 * A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such that z0 = start and zk = end and there is an edge between zi and zi+1 where 0 <= i <= k-1.
 *
 * The distance of a path is the sum of the weights on the edges of the path. Let distanceToLastNode(x) denote the shortest distance of a path between node n and node x. A restricted path is a path that also satisfies that distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.
 *
 * Return the number of restricted paths from node 1 to node n. Since that number may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
 * Output: 3
 * Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The three restricted paths are:
 * 1) 1 --> 2 --> 5
 * 2) 1 --> 2 --> 3 --> 5
 * 3) 1 --> 3 --> 5
 * Example 2:
 *
 *
 * Input: n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
 * Output: 1
 * Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The only restricted path is 1 --> 3 --> 7.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 104
 * n - 1 <= edges.length <= 4 * 104
 * edges[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 1 <= weighti <= 105
 * There is at most one edge between any two nodes.
 * There is at least one path between any two nodes.
 */
public class NumberofRestrictedPathsFromFirsttoLastNode {

  public static void main(String[] args) {
    NumberofRestrictedPathsFromFirsttoLastNode o = new NumberofRestrictedPathsFromFirsttoLastNode();
    System.out.println(o.countRestrictedPaths_self(5, new int[][]{{1,2,3},{1,3,3},{2,3,1},{1,4,2},{5,2,2},{3,5,1},{5,4,10}}));
    System.out.println(o.countRestrictedPaths_self(3, new int[][]{{1,2,100},{2,3,100}}));
    System.out.println(o.countRestrictedPaths(3, new int[][]{{1,3,1},{1,2,2},{2,3,5}}));
  }

  public int countRestrictedPaths_self(int n, int[][] edges) {

    Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    for (int[] edge : edges) {
      graph.computeIfAbsent(edge[0], x -> new HashMap<>()).put(edge[1], edge[2]);
      graph.computeIfAbsent(edge[1], x -> new HashMap<>()).put(edge[0], edge[2]);
    }
    int[] dist = new int[n+1];
    Arrays.fill(dist, (int)1E9);
    dist[n] = 0;
    Queue<int[]> q = new PriorityQueue<>((a,b) -> a[1] - b[1]);
    q.offer(new int[]{n, 0});

    while (!q.isEmpty()) {
      int[] node = q.poll();
      for (Map.Entry<Integer, Integer> adj : graph.get(node[0]).entrySet()) {
        int d = dist[node[0]] + adj.getValue();
        if (dist[adj.getKey()] > d) {
          dist[adj.getKey()] = d;
          q.offer(new int[]{adj.getKey(), d});
        }
      }
    }

    Integer[] mem = new Integer[n+1];
    return dfs(graph, dist, mem, n, 1);
  }

  private int dfs(Map<Integer, Map<Integer, Integer>> graph, int[] dist, Integer[] mem, int node, int target) {
    int res = 0;
    if (node == target) {
      return 1;
    }
    if (mem[node] != null) {
      return mem[node];
    }

    for (int adjKey : graph.get(node).keySet()) {
      if (dist[node] < dist[adjKey]) {
        res += dfs(graph, dist, mem, adjKey, target);
        res %= MOD;
      }
    }
    mem[node] = res;
    return res;
  }

  private static final int MOD = (int) 1E9 + 7;

  public int countRestrictedPaths(int n, int[][] edges) {
    if (n <= 0) {
      return 0;
    }
    List<Node>[] graph = buildGraph(n, edges);
    int[] dists = new int[n + 1];
    Arrays.fill(dists, Integer.MAX_VALUE);
    dists[n] = 0;
    PriorityQueue<Node> minHeap = new PriorityQueue<>(n,
        (n1, n2) -> Integer.compare(n1.dist, n2.dist));
    minHeap.add(new Node(n, 0));
    while (!minHeap.isEmpty()) {
      Node node = minHeap.poll();
      List<Node> neighbors = graph[node.id];
      for (Node neighbor : neighbors) {
        int d = dists[node.id] + neighbor.dist;
        if (dists[neighbor.id] > d) {
          dists[neighbor.id] = d;
          minHeap.add(new Node(neighbor.id, d));
        }
      }
    }
    Integer[] memo = new Integer[n + 1];
    return dfs(n, 1, dists, graph, memo);
  }

  private int dfs(int node, int target, int[] dists, List<Node>[] graph, Integer[] memo) {
    if (node == target) {
      return 1;
    }
    if (memo[node] != null) {
      return memo[node];
    }
    List<Node> neighbors = graph[node];
    int count = 0;
    for (Node neighbor : neighbors) {
      if (dists[neighbor.id] > dists[node]) {
        count += dfs(neighbor.id, target, dists, graph, memo);
        count %= MOD;
      }
    }
    memo[node] = count;
    return memo[node];
  }

  private List<Node>[] buildGraph(int n, int[][] edges) {
    List<Node>[] graph = new ArrayList[n + 1];
    for (int i = 1; i <= n; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      int u = edge[0], v = edge[1], w = edge[2];
      graph[u].add(new Node(v, w));
      graph[v].add(new Node(u, w));
    }
    return graph;
  }

  class Node {

    int id, dist;

    Node(int id, int dist) {
      this.id = id;
      this.dist = dist;
    }
  }

}
