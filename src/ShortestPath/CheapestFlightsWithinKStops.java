package ShortestPath;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 8/1/2021  12:41 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;

/**
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 *
 * There are n cities connected by some number of flights.
 * You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation: The graph is shown.
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 * Example 2:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation: The graph is shown.
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * There will not be any multiple flights between two cities.
 * 0 <= src, dst, k < n
 * src != dst
 */
public class CheapestFlightsWithinKStops {

  public static void main(String[] args) {
    CheapestFlightsWithinKStops o = new CheapestFlightsWithinKStops();
    System.out.println(o.findCheapestPrice(3, new int[][]{{0,1,100},{1,2,100},{0,2,500}}, 0, 2, 0));
  }

  //Bellman Ford Algorithm
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    int[] prev = new int[n];
    Arrays.fill(prev, Integer.MAX_VALUE);
    prev[src] = 0;

    for (int i = 0; i <= k; i++) {
      int[] curr = new int[n];
      for (int j = 0; j < n; j++) {
        curr[j] = prev[j];
      }
      for (int[] edge : flights) {
        int u = edge[0];
        int v = edge[1];
        int wt = edge[2];

        if (prev[u] != Integer.MAX_VALUE && prev[u] + wt < curr[v]) {
          curr[v] = prev[u] + wt;
        }
      }
      for (int j = 0; j < n; j++) {
        prev[j] = curr[j];
      }
    }
    return prev[dst] == Integer.MAX_VALUE ? -1 : prev[dst];
  }
}
