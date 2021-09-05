package BiConnectedComponent;

import java.util.*;

/**
 * https://leetcode.com/problems/critical-connections-in-a-network/
 *
 * There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
 *
 * Return all critical connections in the network in any order.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 105
 * n - 1 <= connections.length <= 105
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * There are no repeated connections.
 *
 * Tarjan's algorithm
 * https://www.youtube.com/watch?v=2kREIkF9UAs
 * https://leetcode.com/problems/critical-connections-in-a-network/discuss/1373699/Easy-Java-Solution-using-DFS-(Bridge-Concept)
 */
public class CriticalConnectionsinaNetwork {

    public static void main(String[] args) {
        CriticalConnectionsinaNetwork o = new CriticalConnectionsinaNetwork();
        List<List<Integer>> connections = new ArrayList<>();
        List<Integer> conn1 = new ArrayList<>();
        conn1.add(0);
        conn1.add(1);
        connections.add(conn1);
        List<Integer> conn2 = new ArrayList<>();
        conn2.add(1);
        conn2.add(2);
        connections.add(conn2);
        List<Integer> conn3 = new ArrayList<>();
        conn3.add(2);
        conn3.add(0);
        connections.add(conn3);
        List<Integer> conn4 = new ArrayList<>();
        conn4.add(1);
        conn4.add(3);
        connections.add(conn4);
        o.criticalConnections(4, connections);
        o.criticalConnections_simple(4, connections);
    }

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> criticalConnections_simple(int n, List<List<Integer>> connections) {
        int [] visited = new int [n];
        int [] tin = new int[n];    //time of insertion or discovery time
        int [] low = new int[n];

        List<List<Integer>> adj = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // build graph
        for (int i = 0; i < connections.size(); i++) {
            int from = connections.get(i).get(0), to = connections.get(i).get(1);
            adj.get(from).add(to);
            adj.get(to).add(from);
        }

        int timer = 0;
        for(int i = 0;i<n;i++){
            if(visited[i] == 0){
                dfs(i,-1,low,tin,timer,adj,visited);
            }
        }
        return ans;
    }

    private void dfs(int node,int parent,int [] low,int [] tin,int timer,List<List<Integer>> adj,int [] visited){

        visited[node] = 1;
        low[node] = tin[node] = timer++;

        for(int it : adj.get(node)){
            if(it == parent) continue;

            if(visited[it] == 0){
                dfs(it,node,low,tin,timer,adj,visited);
                low[node] = Math.min(low[node],low[it]);

                if(low[it] > tin[node]){
                    List<Integer> inner = new ArrayList<>();
                    inner.add(it); inner.add(node);
                    ans.add(new ArrayList<>(inner));
                }
            }else{
                low[node] = Math.min(low[node],tin[it]);
            }
        }
    }


    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        int[] parents = new int[n];
        int[] visitedTime = new int[n];
        int[] lowTime = new int[n];

        Arrays.fill(parents, -1);
        Arrays.fill(visitedTime, -1);
        Arrays.fill(lowTime, -1);

        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (List<Integer> conn : connections) {
            adjMap.computeIfAbsent(conn.get(0), k -> new ArrayList<>()).add(conn.get(1));
            adjMap.computeIfAbsent(conn.get(1), k -> new ArrayList<>()).add(conn.get(0));
        }

        List<List<Integer>> res = new ArrayList<>();
        findArticulationPoint(adjMap, parents, visitedTime, lowTime, 0, res, 0);
        return res;
    }

    private void findArticulationPoint(Map<Integer, List<Integer>> adjMap, int[] parents, int[] visitedTime, int[] lowTime, int currTime, List<List<Integer>> res, int vertex) {
        // if (adjMap.get(vertex) == null) {
        //     visitedTime[vertex] = currTime-1;
        //     lowTime[vertex] = currTime-1;
        //     return;
        // }

        visitedTime[vertex] = currTime;
        lowTime[vertex] = currTime;
        int childCount = 0;
        boolean articulationPoint = false;

        for (Integer adj : adjMap.get(vertex)) {

            if (parents[vertex] == adj) {
                continue;
            }

            if (visitedTime[adj] == -1) {
                parents[adj] = vertex;
                findArticulationPoint(adjMap, parents, visitedTime, lowTime, currTime+1, res, adj);
                lowTime[vertex] = Math.min(lowTime[vertex], lowTime[adj]);

                if (lowTime[adj] > visitedTime[vertex]) {
                    List<Integer> p = new ArrayList<>();
                    p.add(vertex);
                    p.add(adj);
                    res.add(p);
                }
            } else {
                lowTime[vertex] = Math.min(lowTime[vertex], visitedTime[adj]);
            }
        }

        // if (childCount == 0 && parents[vertex] == 0) {
        //     List<Integer> p = new ArrayList<>();
        //     p.add(vertex);
        //     p.add(parents[vertex]);
        //     res.add(p);
        // }

        // if (parents[vertex] == -1 && childCount >= 2) {
        //     List<Integer> p = new ArrayList<>();
        //     p.add(vertex);
        //     p.add(vertex);
        //     res.add(p);
        // }
    }
}
