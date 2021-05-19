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
//    System.out.println(o.nameNumber(map, "Samuthran Sivaprakash"));
    System.out.println(o.nameNumber(map, "Sagan Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Sanadhanan Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Sipan Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Saaran Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Sakon Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Sancho Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Sanden Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Sachit Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Sajeev Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Sandhanu Sivaprakash"));
    System.out.println(o.nameNumber(map, "Sampreeth Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Sakal Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Salil Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Samaksha Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Samarthan Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Sanchay Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Sparsha Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Samarth Sivaprakash"));
    System.out.println(o.nameNumber(map, "Sathaathy Sivaprakash"));
    System.out.println(o.nameNumber(map, "Samridh Sivaprakash"));
    System.out.println(o.nameNumber(map, "Samrudhan Sivaprakash"));
    System.out.println(o.nameNumber(map, "Sachiv Sivaprakash"));
    System.out.println(o.nameNumber(map, "Samadhan Sivaprakash"));
    System.out.println(o.nameNumber(map, "Samadh Sivaprakash"));

//    System.out.println(o.nameNumber(map, "Sanchay Sivaprakash"));
//    System.out.println(o.nameNumber(map, "Sanju Sivaprakash"));
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
