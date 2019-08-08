package arrays;

import java.util.Map;
import java.util.HashMap;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */

/**
 * O(n)
 * In a linear style, Store the value into a Map, while checking the (target - value) already
 * exist in the Map. If so return.
 */
public class TwoSum {
  public static void main(String[] args) {
    int[] arr = {2, 7, 11, 15, 0};
    int target = 11;

    int[] res = findSumIndices(arr, target);
    System.out.println(res[0] + " " + res[1]);
  }

  private static int[] findSumIndices(int[] arr, int target) {
    Map<Integer, Integer> checker = new HashMap<Integer, Integer>();
    for(int i = 0; i < arr.length; i++) {
      int delta = target - arr[i];
      if (checker.containsKey(delta)) {
        return new int[] {checker.get(delta), i};
      }
      checker.put(arr[i], i);
    }
    return new int[] {-1, -1};
  }
}
