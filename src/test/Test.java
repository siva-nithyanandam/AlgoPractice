package test;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/19/2021  11:57 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
/**
 *
 */
public class Test {

  public double[] calcEquation(List<List<String>> equations, double[] values,
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
}
