package arrays;
/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class NamingNumber {

  public static void main(String[] args) {
    NamingNumber o = new NamingNumber();
    Map<Character, Integer> map = new HashMap<>();
    o.addValue(map, "AIJQK", 1);
    o.addValue(map, "BKR", 2);
    o.addValue(map, "CGLS", 3);
    o.addValue(map, "DMT", 4);
    o.addValue(map, "EHNX", 5);
    o.addValue(map, "UVW", 6);
    o.addValue(map, "OZ", 7);
    o.addValue(map, "FP", 8);
    System.out.println(o.nameNumber(map, "John Smith"));
  }

  private int nameNumber(Map<Character, Integer> map, String name) {
    int res = 0;
    for (char c : name.toUpperCase().toCharArray()) {
      if (map.containsKey(c)) {
        res += map.get(c);
      }
    }
    return res;
  }

  private void addValue(Map<Character, Integer> map, String letters, int value) {
    for (char c : letters.toCharArray()) {
      map.put(c, value);
    }
  }
}
