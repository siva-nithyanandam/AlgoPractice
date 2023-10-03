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
    o.nameNumber(map, "SamrudhanSivaprakash");
    o.nameNumber(map, "Sivaprakash");
    o.nameNumber(map, "VaigaSivaprakash");
    o.nameNumber(map, "VarunaSivaprakash");
    o.nameNumber(map, "VarnaaSivaprakash");
    o.nameNumber(map, "VarnuSivaprakash");
    o.nameNumber(map, "VayunaSivaprakash");
    o.nameNumber(map, "ValoraSivaprakash");
    o.nameNumber(map, "VanoraSivaprakash");
    o.nameNumber(map, "VanorhaSivaprakash");
    o.nameNumber(map, "VanorSivaprakash");
    o.nameNumber(map, "VenorhaSivaprakash");
    o.nameNumber(map, "AroraSivaprakash");
  }

  private int nameNumber(Map<Character, Integer> map, String name) {
    int res = 0;
    for (char c : name.toUpperCase().toCharArray()) {
      if (map.containsKey(c)) {
        res += map.get(c);
      }
    }
    System.out.println(name + " - " + res);
    return res;
  }

  private void addValue(Map<Character, Integer> map, String letters, int value) {
    for (char c : letters.toCharArray()) {
      map.put(c, value);
    }
  }
}
