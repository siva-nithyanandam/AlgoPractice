package disjointset_unionfind;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/18/2021  8:34 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/evaluate-division/
 *
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 *
 *
 *
 * Example 1:
 *
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * Example 2:
 *
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 * Example 3:
 *
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 *
 * Constraints:
 *
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class EvaluateDivision {

  public static void main(String[] args) {
    EvaluateDivision o = new EvaluateDivision();

    System.out.println(Arrays.toString(o.calcEquation_faster(Arrays.asList(Arrays.asList("a","b"),Arrays.asList("b","c")),
        new double[]{10.0,2.0}, Arrays.asList(Arrays.asList("a","c")))));

    System.out.println(Arrays.toString(o.calcEquation_faster(Arrays.asList(Arrays.asList("a","b"),Arrays.asList("a","c")),
        new double[]{2.0,3.0}, Arrays.asList(Arrays.asList("b","c")))));

    System.out.println(Arrays.toString(o.calcEquation_faster(Arrays.asList(Arrays.asList("a","b"),Arrays.asList("b","c"), Arrays.asList("bc","cd")),
        new double[]{1.5,2.5,5.0}, Arrays.asList(Arrays.asList("a","c"),Arrays.asList("c","b"), Arrays.asList("bc","cd"), Arrays.asList("cd","bc")))));

    System.out.println(Arrays.toString(o.calcEquation_faster(Arrays.asList(Arrays.asList("a","b"),Arrays.asList("b","c")), new double[]{2.0,3.0},
        Arrays.asList(Arrays.asList("a","c"),Arrays.asList("b","a"), Arrays.asList("a","e"), Arrays.asList("a","a"), Arrays.asList("x","x")))));
  }

  public double[] calcEquation_faster(List<List<String>> equations, double[] values,
      List<List<String>> queries) {

    HashMap<String, AbstractMap.SimpleEntry<String, Double>> gidWeight = new HashMap<>();

    // Step 1). build the union groups
    for (int i = 0; i < equations.size(); i++) {
      List<String> equation = equations.get(i);
      String dividend = equation.get(0), divisor = equation.get(1);
      double quotient = values[i];

      union(gidWeight, dividend, divisor, quotient);
    }

    // Step 2). run the evaluation, with "lazy" updates in find() function
    double[] results = new double[queries.size()];
    for (int i = 0; i < queries.size(); i++) {
      List<String> query = queries.get(i);
      String dividend = query.get(0), divisor = query.get(1);

      if (!gidWeight.containsKey(dividend) || !gidWeight.containsKey(divisor))
        // case 1). at least one variable did not appear before
        results[i] = -1.0;
      else {
        AbstractMap.SimpleEntry<String, Double> dividendEntry = find(gidWeight, dividend);
        AbstractMap.SimpleEntry<String, Double> divisorEntry = find(gidWeight, divisor);

        String dividendGid = dividendEntry.getKey();
        String divisorGid = divisorEntry.getKey();
        Double dividendWeight = dividendEntry.getValue();
        Double divisorWeight = divisorEntry.getValue();

        if (!dividendGid.equals(divisorGid))
          // case 2). the variables do not belong to the same chain/group
          results[i] = -1.0;
        else
          // case 3). there is a chain/path between the variables
          results[i] = dividendWeight / divisorWeight;
      }
    }

    return results;
  }

  private AbstractMap.SimpleEntry<String, Double> find(HashMap<String, AbstractMap.SimpleEntry<String, Double>> gidWeight, String nodeId) {
    if (!gidWeight.containsKey(nodeId))
      gidWeight.put(nodeId, new AbstractMap.SimpleEntry<String, Double>(nodeId, 1.0));

    AbstractMap.SimpleEntry<String, Double> entry = gidWeight.get(nodeId);
    // found inconsistency, trigger chain update
    if (!entry.getKey().equals(nodeId)) {
      AbstractMap.SimpleEntry<String, Double> newEntry = find(gidWeight, entry.getKey());
      gidWeight.put(nodeId, new AbstractMap.SimpleEntry<String, Double>(
          newEntry.getKey(), entry.getValue() * newEntry.getValue()));
    }

    return gidWeight.get(nodeId);
  }

  private void union(HashMap<String, AbstractMap.SimpleEntry<String, Double>> gidWeight, String dividend, String divisor, Double value) {
    AbstractMap.SimpleEntry<String, Double> dividendEntry = find(gidWeight, dividend);
    AbstractMap.SimpleEntry<String, Double> divisorEntry = find(gidWeight, divisor);

    String dividendGid = dividendEntry.getKey();
    String divisorGid = divisorEntry.getKey();
    Double dividendWeight = dividendEntry.getValue();
    Double divisorWeight = divisorEntry.getValue();

    // merge the two groups together,
    // by attaching the dividend group to the one of divisor
    if (!dividendGid.equals(divisorGid)) {
      gidWeight.put(dividendGid, new AbstractMap.SimpleEntry<String, Double>(divisorGid,
          divisorWeight * value / dividendWeight));
    }
  }

  public double[] calcEquation(List<List<String>> equations, double[] values,
      List<List<String>> queries) {

    HashMap<String, HashMap<String, Double>> graph = new HashMap<>();

    // Step 1). build the graph from the equations
    for (int i = 0; i < equations.size(); i++) {
      List<String> equation = equations.get(i);
      String dividend = equation.get(0), divisor = equation.get(1);
      double quotient = values[i];

      if (!graph.containsKey(dividend))
        graph.put(dividend, new HashMap<String, Double>());
      if (!graph.containsKey(divisor))
        graph.put(divisor, new HashMap<String, Double>());

      graph.get(dividend).put(divisor, quotient);
      graph.get(divisor).put(dividend, 1 / quotient);
    }

    // Step 2). Evaluate each query via bactracking (DFS)
    // by verifying if there exists a path from dividend to divisor
    double[] results = new double[queries.size()];
    for (int i = 0; i < queries.size(); i++) {
      List<String> query = queries.get(i);
      String dividend = query.get(0), divisor = query.get(1);

      if (!graph.containsKey(dividend) || !graph.containsKey(divisor))
        results[i] = -1.0;
      else if (dividend == divisor)
        results[i] = 1.0;
      else {
        HashSet<String> visited = new HashSet<>();
        results[i] = backtrackEvaluate(graph, dividend, divisor, 1, visited);
      }
    }

    return results;
  }

  private double backtrackEvaluate(HashMap<String, HashMap<String, Double>> graph, String currNode,
      String targetNode, double accProduct, Set<String> visited) {

    // mark the visit
    visited.add(currNode);
    double ret = -1.0;

    Map<String, Double> neighbors = graph.get(currNode);
    if (neighbors.containsKey(targetNode))
      ret = accProduct * neighbors.get(targetNode);
    else {
      for (Map.Entry<String, Double> pair : neighbors.entrySet()) {
        String nextNode = pair.getKey();
        if (visited.contains(nextNode))
          continue;
        ret = backtrackEvaluate(graph, nextNode, targetNode,
            accProduct * pair.getValue(), visited);
        if (ret != -1.0)
          break;
      }
    }

    // unmark the visit, for the next backtracking
    visited.remove(currNode);
    return ret;
  }
}
