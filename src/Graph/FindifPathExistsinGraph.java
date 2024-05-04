package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 * Nov 03,2022 - 8:44 AM
 */

/**
 * https://leetcode.com/problems/find-if-path-exists-in-graph/
 * <p>
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 * <p>
 * You want to determine if there is a valid path that exists from vertex source to vertex destination.
 * <p>
 * Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2
 * Example 2:
 * <p>
 * <p>
 * Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
 * Output: false
 * Explanation: There is no path from vertex 0 to vertex 5.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 2 * 105
 * 0 <= edges.length <= 2 * 105
 * edges[i].length == 2
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 0 <= source, destination <= n - 1
 * There are no duplicate edges.
 * There are no self edges.
 */
public class FindifPathExistsinGraph {

    public static void main(String[] args) {
        FindifPathExistsinGraph o = new FindifPathExistsinGraph();
        System.out.println(o.validPath_better(3, new int[][]{{0, 1}, {1, 2}, {2, 0}}, 0, 2));
    }

    public boolean validPath_own(int n, int[][] edges, int source, int dest) {

        if (source == dest) {
            return true;
        }
        List<Integer>[] routes = new ArrayList[n];

        IntStream.range(0, n).forEach(i -> routes[i] = new ArrayList<>());
        for (int[] edge : edges) {
            routes[edge[0]].add(edge[1]);
            routes[edge[1]].add(edge[0]);
        }

        boolean[] vis = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        vis[source] = true;

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int to : routes[node]) {
                if (vis[to]) {
                    continue;
                }
                if (to == dest) {
                    return true;
                }
                q.add(to);
                vis[to] = true;
            }
        }
        return false;
    }

    public boolean validPath_better(int n, int[][] edges, int source, int destination) {
        if (edges.length == 0) {
            return true;
        }
        boolean[] visited = new boolean[n];
        boolean flag = true;
        visited[source] = true;
        while (flag) {
            flag = false;
            for (int[] edge : edges) {
                if (visited[edge[0]] != visited[edge[1]]) {
                    visited[edge[0]] = true;
                    visited[edge[1]] = true;
                    flag = true;
                }
                if (visited[destination]) {
                    return true;
                }
            }
        }
        return false;
    }


}
