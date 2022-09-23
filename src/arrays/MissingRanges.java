package arrays;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/2/2021  6:54 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/missing-ranges/
 *
 * You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are in the inclusive range.
 *
 * A number x is considered missing if x is in the range [lower, upper] and x is not in nums.
 *
 * Return the smallest sorted list of ranges that cover every missing number exactly. That is, no element of nums is in any of the ranges, and each missing number is in one of the ranges.
 *
 * Each range [a,b] in the list should be output as:
 *
 * "a->b" if a != b
 * "a" if a == b
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,3,50,75], lower = 0, upper = 99
 * Output: ["2","4->49","51->74","76->99"]
 * Explanation: The ranges are:
 * [2,2] --> "2"
 * [4,49] --> "4->49"
 * [51,74] --> "51->74"
 * [76,99] --> "76->99"
 * Example 2:
 *
 * Input: nums = [], lower = 1, upper = 1
 * Output: ["1"]
 * Explanation: The only missing range is [1,1], which becomes "1".
 * Example 3:
 *
 * Input: nums = [], lower = -3, upper = -1
 * Output: ["-3->-1"]
 * Explanation: The only missing range is [-3,-1], which becomes "-3->-1".
 * Example 4:
 *
 * Input: nums = [-1], lower = -1, upper = -1
 * Output: []
 * Explanation: There are no missing ranges since there are no missing numbers.
 * Example 5:
 *
 * Input: nums = [-1], lower = -2, upper = -1
 * Output: ["-2"]
 *
 *
 * Constraints:
 *
 * -109 <= lower <= upper <= 109
 * 0 <= nums.length <= 100
 * lower <= nums[i] <= upper
 * All the values of nums are unique.
 */
public class MissingRanges {

  public static void main(String[] args) {
    MissingRanges o = new MissingRanges();
    System.out.println(o.findMissingRanges(new int[]{0,1,3,50,75}, 0, 99));
  }

  public List<String> findMissingRanges(int[] nums, int lower, int upper) {
    List<String> ret = new ArrayList<>();

    if (nums.length == 0) {
      ret.add(genRange(lower, upper));
      return ret;
    }

    // check lower to nums[0]
    if (nums[0] - lower > 0) {
      ret.add(genRange(lower, nums[0] - 1));
    }

    // middle
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] - nums[i - 1] > 1) {
        ret.add(genRange(nums[i - 1] + 1, nums[i] - 1));
      }
    }

    // check nums[last] to upper
    if (upper - nums[nums.length - 1] > 0) {
      ret.add(genRange(nums[nums.length -1] + 1, upper));
    }

    return ret;
  }

  private String genRange(int l, int u) {
    StringBuilder b = new StringBuilder();
    if (l == u) {
      b.append(l);
    } else {
      b.append(l).append("->").append(u);
    }

    return b.toString();
  }
}
