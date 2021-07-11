package MathAndLogic;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/26/2021  10:49 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Random;

/**
 * https://leetcode.com/problems/random-pick-index/
 * <p>
 * Given an integer array nums with possible duplicates, randomly output the index of a given target
 * number. You can assume that the given target number must exist in the array.
 * <p>
 * Implement the Solution class:
 * <p>
 * Solution(int[] nums) Initializes the object with the array nums. int pick(int target) Picks a
 * random index i from nums where nums[i] == target. If there are multiple valid i's, then each
 * index should have an equal probability of returning.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input ["Solution", "pick", "pick", "pick"] [[[1, 2, 3, 3, 3]], [3], [1], [3]] Output [null, 4, 0,
 * 2]
 * <p>
 * Explanation Solution solution = new Solution([1, 2, 3, 3, 3]); solution.pick(3); // It should
 * return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(1); // It should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have
 * equal probability of returning.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 104 -231 <= nums[i] <= 231 - 1 target is an integer from nums. At most
 * 104 calls will be made to pick.
 */
public class RandomPickIndex {

  private static int[] nums;
  private Random rand = new Random();

  public static void main(String[] args) {
    RandomPickIndex o = new RandomPickIndex();
    nums = new int[]{1, 2, 3, 3, 3};
    System.out.println("Sivaprakash".hashCode());
    System.out.println(o.pick(3));
    System.out.println(o.pick(3));
    System.out.println(o.pick(3));
  }

  public int pick(int target) {
    int n = this.nums.length;
    int count = 0;
    int idx = 0;
    for (int i = 0; i < n; ++i) {
      // if nums[i] is equal to target, i is a potential candidate
      // which needs to be chosen uniformly at random
      if (this.nums[i] == target) {
        // increment the count of total candidates
        // available to be chosen uniformly at random
        count++;
        // we pick the current number with probability 1 / count (reservoir sampling)
        int nextInt = rand.nextInt(count);
        if (nextInt == 0) {
          idx = i;
        }
      }
    }
    return idx;
  }
}
