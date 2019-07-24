package TreesAndGraphs;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Given a directed graph, design an algorithm to find out whether there is a
 * route between two nodes.
 */
public class RouteBetweenNodes {

  static class Graph {

    //Total nbr of vertices
    private int v;
    //To store routes
    private LinkedList<Integer> adj[];

    public Graph(int nbrOfVertices) {
      this.v = nbrOfVertices;
      adj = new LinkedList[v];
      for(int i = 0; i < nbrOfVertices; i++) {
        adj[i] = new LinkedList<Integer>();
      }
    }

    public void addEdge(int s, int d) {
      adj[s].add(d);
    }

    public boolean isRouteAvailable(int s, int d) {

      //To track visited nodes
      boolean[] visited = new boolean[v];

      //To check nodes one after the other
      LinkedList<Integer> queue = new LinkedList<Integer>();

      queue.add(s);
      visited[s] = true;

      Iterator<Integer> itr;
      int n;
      while(queue.size() != 0) {
        s = queue.poll();
        itr = adj[s].listIterator();
        while(itr.hasNext()) {
          n = itr.next();
          if (n == d) {
            return true;
          }
          if (!visited[n]) {
            queue.add(n);
            visited[n] = true;
          }
        }
      }
      return false;
    }
  }

  public static void main(String[] args) {
    Graph graph = new Graph(4);
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 2);
    graph.addEdge(2, 0);
    graph.addEdge(2, 3);
    graph.addEdge(3, 3);

    System.out.println(graph.isRouteAvailable(0, 3));
    System.out.println(graph.isRouteAvailable(1, 3));
    System.out.println(graph.isRouteAvailable(3, 0));
    System.out.println(graph.isRouteAvailable(3, 1));
  }
}
