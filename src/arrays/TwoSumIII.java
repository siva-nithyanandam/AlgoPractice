package arrays;

import java.util.Map;
import java.util.HashMap;

/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 *
 * For example,
 * add(1);
 * add(3);
 * add(5);
 * find(4) -> true
 * find(7) -> false
 */

/**
 * Straight forward solution. Since dealing 2 integers, make it in map and solve.
 */
public class TwoSumIII {

  Map<Integer, Integer> map = new HashMap<>();

  public static void main(String[] args) {
    TwoSumIII o = new TwoSumIII();
    o.add(1);
    o.add(3);
    o.add(3);
    o.add(7);
    System.out.println(o.find(4));
    System.out.println(o.find(7));
    System.out.println(o.find(6));
  }

  public void add(int i) {
    if (map.get(i) != null) {
      map.put(i, map.get(i) + 1);
    } else {
      map.put(i, 1);
    }
  }

  public boolean find(int given) {
    for (Integer e : map.keySet()) {
      int delta = given - e;
      if (map.get(delta) != null) {
        if (given == delta && map.get(delta) < 2) {
          continue;
        }
        return true;
      }
    }
    return false;
  }
}
