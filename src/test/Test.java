package test;

import java.util.List;

/**
 * Author - Sivaprakash Nithyanandam Timestamp - 5/21/2022  11:27 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */


public class Test {

  public static void main(String[] args) {
    Test o = new Test();
    List<Integer> nums = List.of(4, 2);
    int k = 3;
    List<List<Integer>> edges = List.of();
    System.out.println(o.maximumValueSum(nums, k, edges));  // Example usage
  }

  public long maximumValueSum(List<Integer> nums, int k, List<List<Integer>> edges) {
    long sum = 0;
    int smallestGain = 2 * k + 1;
    int smallestLost = 2 * k + 1;
    int gainCount = 0;

    for (int num : nums) {
      int gain = (num ^ k) - num;
      if (gain > 0) {
        smallestGain = Math.min(smallestGain, gain);
        sum += num + gain;
        gainCount++;
      } else {
        smallestLost = Math.min(smallestLost, -gain);
        sum += num;
      }
    }

    if (gainCount % 2 == 1) {
      sum -= Math.min(smallestGain, smallestLost);
    }

    return sum;
  }

}
