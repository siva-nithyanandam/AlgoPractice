package arrays;/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.lintcode.com/problem/missing-ranges/
 *
 * Description
 * Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
 *
 * Example
 * Example 1
 *
 * Input:
 * nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99
 * Output:
 * ["2", "4->49", "51->74", "76->99"]
 * Explanation:
 * in range[0,99],the missing range includes:range[2,2],range[4,49],range[51,74] and range[76,99]
 * Example 2
 *
 * Input:
 * nums = [0, 1, 2, 3, 7], lower = 0 and upper = 7
 * Output:
 * ["4->6"]
 * Explanation:
 * in range[0,7],the missing range include range[4,6]
 */
public class MissingRanges {

  public static void main(String[] args) {
    MissingRanges o = new MissingRanges();
    System.out.println(o.findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 99).toString());
    System.out.println(o.findMissingRanges(new int[]{0, 1, 2, 3, 7}, 0, 7).toString());
    System.out.println(o.findMissingRanges(new int[]{0, 1, 2, 5}, 0, 8).toString());
  }

  public List<String> findMissingRanges(int[] nums, int lower, int upper) {
    // write your code here
    int start = lower;
    List<String> res = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      putRange(start, nums[i], nums, res);
      start = nums[i];
    }
    putRange(start, upper+1, nums, res);
    return res;
  }

  private void putRange(int start, int end, int[] nums, List<String> res) {
    int diff = end - start;
    if(diff < 2) {
      return;
    } else if(diff == 2) {
      res.add(""+(start+1));
    } else {
      res.add((start+1) + "->" + (end-1));
    }
  }
}
