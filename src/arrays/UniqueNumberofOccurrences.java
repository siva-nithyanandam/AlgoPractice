package arrays;

import com.sun.xml.internal.ws.api.ha.HaInfo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of integers arr, write a function that returns true if and only if the number of
 * occurrences of each value in the array is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,2,1,1,3]
 * Output: true
 * Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
 * Example 2:
 *
 * Input: arr = [1,2]
 * Output: false
 * Example 3:
 *
 * Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 1000
 * -1000 <= arr[i] <= 1000
 */
public class UniqueNumberofOccurrences {

    public static void main(String[] args) {
        UniqueNumberofOccurrences o = new UniqueNumberofOccurrences();
        System.out.println(o.uniqueOccurrences(new int[]{1,2,2,1,1,3}));
        System.out.println(o.uniqueOccurrences(new int[]{1,2}));
        System.out.println(o.uniqueOccurrences(new int[]{-3,0,1,-3,1,1,1,-3,10,0}));
    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        for (Integer i : map.keySet()) {
            if (set.contains(map.get(i))) {
                return false;
            } else {
                set.add(map.get(i));
            }
        }
        return true;
    }
}
