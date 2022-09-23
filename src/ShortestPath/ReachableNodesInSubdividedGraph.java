package ShortestPath;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/30/2021  1:51 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/reachable-nodes-in-subdivided-graph/
 *
 * You are given an undirected graph (the "original graph") with n nodes labeled from 0 to n - 1. You decide to subdivide each edge in the graph into a chain of nodes, with the number of new nodes varying between each edge.
 *
 * The graph is given as a 2D array of edges where edges[i] = [ui, vi, cnti] indicates that there is an edge between nodes ui and vi in the original graph, and cnti is the total number of new nodes that you will subdivide the edge into. Note that cnti == 0 means you will not subdivide the edge.
 *
 * To subdivide the edge [ui, vi], replace it with (cnti + 1) new edges and cnti new nodes. The new nodes are x1, x2, ..., xcnti, and the new edges are [ui, x1], [x1, x2], [x2, x3], ..., [xcnti+1, xcnti], [xcnti, vi].
 *
 * In this new graph, you want to know how many nodes are reachable from the node 0, where a node is reachable if the distance is maxMoves or less.
 *
 * Given the original graph and maxMoves, return the number of nodes that are reachable from node 0 in the new graph.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: edges = [[0,1,10],[0,2,1],[1,2,2]], maxMoves = 6, n = 3
 * Output: 13
 * Explanation: The edge subdivisions are shown in the image above.
 * The nodes that are reachable are highlighted in yellow.
 * Example 2:
 *
 * Input: edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], maxMoves = 10, n = 4
 * Output: 23
 * Example 3:
 *
 * Input: edges = [[1,2,4],[1,4,5],[1,3,1],[2,3,4],[3,4,5]], maxMoves = 17, n = 5
 * Output: 1
 * Explanation: Node 0 is disconnected from the rest of the graph, so only node 0 is reachable.
 *
 *
 * Constraints:
 *
 * 0 <= edges.length <= min(n * (n - 1) / 2, 104)
 * edges[i].length == 3
 * 0 <= ui < vi < n
 * There are no multiple edges in the graph.
 * 0 <= cnti <= 104
 * 0 <= maxMoves <= 109
 * 1 <= n <= 3000
 */
public class ReachableNodesInSubdividedGraph {

  public static void main(String[] args) {
    ReachableNodesInSubdividedGraph o = new ReachableNodesInSubdividedGraph();
    System.out.println(o.reachableNodes_clear(new int[][]{{0,1,10},{0,2,1},{1,2,2}}, 6, 3));
    System.out.println(o.reachableNodes2(new int[][]{{0,1,1},{0,2,1},{1,2,1},{1,3,7}}, 6, 4));
  }

  int[] e, w, ne, h;
  int idx;
  public void add(int a, int b, int c){
    e[idx] = b;
    w[idx] = c;
    ne[idx] = h[a];
    h[a] = idx++;
  }
  public int reachableNodes(int[][] edges, int maxMoves, int n) {
    int res = 0;
    int m = edges.length;
    int[] dist = new int[n];
    int INF = (int)1e9;
    idx = 0;
    boolean[] st = new boolean[n];
    Queue<Integer> q = new LinkedList<Integer>();
    h = new int[n];
    e = new int[2*m+1];
    w = new int[2*m+1];
    ne = new int[2*m+1];
    Arrays.fill(h, -1);
    Arrays.fill(dist, INF);
    dist[0] = 0;
    for (int[] e : edges){
      add(e[0], e[1], e[2]+1);
      add(e[1], e[0], e[2]+1);
    }
    q.offer(0);
    st[0] = true;
    while (!q.isEmpty()){
      int cur = q.poll();
      st[cur] = false;
      for (int i = h[cur]; i != -1; i=ne[i]){
        if (dist[e[i]] > dist[cur] + w[i]){
          dist[e[i]] = dist[cur] + w[i];
          if (!st[e[i]]){
            st[e[i]] = true;
            q.offer(e[i]);
          }
        }
      }
    }
    for (int i = 0; i < n; i++){
      if (dist[i] <= maxMoves) res++;
    }
    for (int[] e : edges){
      int left = 0, right = 0;
      if (dist[e[0]] != INF){
        left = Math.max(0, maxMoves - dist[e[0]]);
        left = Math.min(left, e[2]);
      }
      if (dist[e[1]] != INF){
        right = Math.max(0, maxMoves - dist[e[1]]);
        right = Math.min(right, e[2]);
      }
      res += Math.min(e[2], left + right);
    }
    return res;
  }

  int reachableNodes_clear(int[][] edges, int maxMoves, int n) {
    Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

    for (int[] edge : edges) {
      graph.computeIfAbsent(edge[0], m -> new HashMap<>()).put(edge[1], edge[2]);
      graph.computeIfAbsent(edge[1], m -> new HashMap<>()).put(edge[0], edge[2]);
    }

    if (graph.get(0) == null) {
      return 1;
    }

    boolean[] visited = new boolean[n];
    int res = 0;
    Queue<int[]> queue = new PriorityQueue<int[]>((a,b)-> b[1] - a[1]);
    queue.offer(new int[]{0, maxMoves});

    while (!queue.isEmpty()) {

      int[] curr = queue.poll();
      int node = curr[0];
      int moves = curr[1];

      if (visited[node]) {
        continue;
      }
      visited[node] = true;
      res++;
      for (Map.Entry<Integer, Integer> adj : graph.get(node).entrySet()) {
        int adjNode = adj.getKey();
        int adjDist = adj.getValue();

        int remMoves = moves - adjDist - 1;
        if (!visited[adjNode]) {
          if (remMoves > 0) {
            res += adjDist;
            queue.offer(new int[]{adjNode, remMoves});
            graph.get(adjNode).put(node, 0);
          } else {
            graph.get(adjNode).put(node, adjDist-moves);
            res += moves;
          }
        } else {
          res += Math.min(moves, adjDist);
        }
      }
    }
    return res;
  }

  public int reachableNodes2(int[][] edges, int M, int N){
    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
    // put a map for each node
    for(int i= 0; i< N; i++){
      map.put(i, new HashMap<>());
    }
    // queue stores current nodes and the rest of moves left at this node.
    PriorityQueue<int []> queue = new PriorityQueue<>((a,b) -> (b[1] - a[1]));
    // put each node and distance to other nodes in map;
    for(int [] edge : edges){
      map.get(edge[0]).put(edge[1], edge[2]);
      map.get(edge[1]).put(edge[0], edge[2]);
    }
    boolean[] visited = new boolean[N];
    int nodes = 0;
    // start from the origin
    queue.offer(new int[]{0, M});
    while (!queue.isEmpty()){
      int[] cur = queue.poll();
      // define current node and moves left;
      int src = cur[0], move = cur[1];
      if(visited[src]) continue;
      // set to visited if first time, and add to result;
      visited[src] = true;
      nodes++;
      // look for next move from current node
      for(int next : map.get(src).keySet()){
        int nodes_included = map.get(src).get(next);
        // this is moves left if we go to that node;
        int moves_left = move - nodes_included - 1;
        if(!visited[next]){
          // if reachable, add all the nodes in between
          // and add new current node moves left to the queue;
          if(moves_left > 0){
            nodes += nodes_included;
            map.get(next).put(src, 0);
            queue.offer(new int[]{next, moves_left});
          }
          // not enough to reach this node
          // then add all it can reach with given move, then mark the distance of unreachable from the other side
          else{
            nodes += move;
            map.get(next).put(src, map.get(next).get(src) - move);
          }
        }
        // if already visited
        else {
          nodes+= Math.min(move, nodes_included);
        }
      }
    }
    return nodes;
  }


  /*public int reachableNodes1(int[][] edges, int M, int N) {
    Queue<Pair<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.getKey(), a.getKey()));
    Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    Map<Integer, Integer> seen = new HashMap<>();
    for (int[] edge : edges) {
      graph.computeIfAbsent(edge[0], m -> new HashMap<>()).put(edge[1], edge[2]);
      graph.computeIfAbsent(edge[1], m -> new HashMap<>()).put(edge[0], edge[2]);
    }
    pq.offer(new Pair<>(M, 0));
    while (!pq.isEmpty()) {
      Pair<Integer, Integer> cur = pq.poll();
      if (!seen.containsKey(cur.getValue())) {
        seen.put(cur.getValue(), cur.getKey());
        for (int next : graph.getOrDefault(cur.getValue(), Collections.emptyMap()).keySet()) {
          if (!seen.containsKey(next)) {
            int dist = cur.getKey() - graph.get(cur.getValue()).get(next) - 1;
            if (dist >= 0) {
              pq.offer(new Pair<>(dist, next));
            }
          }
        }
      }
    }
    int res = seen.size();
    for (int[] edge : edges) {
      int a = seen.getOrDefault(edge[0], 0);
      int b = seen.getOrDefault(edge[1], 0);
      res += Math.min(a + b, edge[2]);
    }
    return res;
  }*/

  /*public int reachableNodes(int[][] edges, int maxMoves, int n) {
    // Init the graph with map node-node-edgeDist
    Map<Integer, Map<Integer,Integer>> graph = new HashMap<>();
    for(int[] edge: edges){
      graph.computeIfAbsent(edge[0], key -> new HashMap<>()).put(edge[1], edge[2]);
      graph.computeIfAbsent(edge[1], key -> new HashMap<>()).put(edge[0], edge[2]);
    }

    // min distance from node-i to node-0
    // init value are -1
    int[] reached = new int[n];
    Arrays.fill(reached, -1);

    int res = 0;

    // Dijkstra's
    Queue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(0, 0));

    while(!pq.isEmpty()){
      Node cur = pq.poll();

      // Check if the node have been reached before
      // If it has been reached before, that means current dist has to be larger then the previous computation, then there is no need to compute it again
      if(reached[cur.val] != -1){
        continue;
      }

      // Reach the new node
      ++res;
      reached[cur.val] = cur.dist;

      for(Map.Entry<Integer,Integer> edgeNode: graph.getOrDefault(cur.val, new HashMap<>()).entrySet()){
        int end = edgeNode.getKey();
        int length = edgeNode.getValue();
        if(reached[end] == -1){
          res += Math.min(length, maxMoves - cur.dist);
          if(maxMoves - cur.dist > length){
            pq.offer(new Node(end, cur.dist+length+1));
          }
        } else{
          res += Math.min(length, maxMoves - cur.dist + maxMoves - reached[end]) - Math.min(length, maxMoves - reached[end]);
        }
      }
    }
    return res;
  }*/

  static class Node implements Comparable<Node>{
    int val;
    int dist;

    Node(int val, int dist){
      this.val = val;
      this.dist = dist;
    }

    @Override
    public int compareTo(Node other){
      return this.dist - other.dist;
    }
  }
}
