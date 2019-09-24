package arrays;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.  Meanwhile, adjacent houses have
 * security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 * Example 1:
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 *              because they are adjacent houses.
 * Example 2:
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 */

/**
 * Calculate an interval adjacent sum right from the beginning to "end - 1", and start from
 * 1 to end. Take the maximum and return.
 */

/**
 * Results:
 * 3
 * 4
 * 4
 * 11
 * 3
 */
public class HouseRobberII {

  public static void main(String[] args) {
    HouseRobberII o = new HouseRobberII();
    System.out.println(o.rob(new int[]{2,3,2}));
    System.out.println(o.rob(new int[]{1,2,3,1}));
    System.out.println(o.rob(new int[]{1,2,3,1}));
    System.out.println(o.rob(new int[]{2,7,9,3,1}));
    System.out.println(o.rob(new int[]{2,1,1,2}));
  }

  public int rob(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return nums[0];
    }
    if (nums.length == 2) {
     return Math.max(nums[0], nums[1]);
    }

    int[] res = new int[nums.length];
    res[0] = nums[0];
    res[1] = Math.max(nums[0], nums[1]);
    for (int i = 2; i < nums.length - 1; i++) {
      res[i] = Math.max(res[i-1], res[i-2] + nums[i]);
    }
    int fSum = res[nums.length - 2];

    res[1] = nums[1];
    res[2] = Math.max(nums[1], nums[2]);
    for (int i = 3; i < nums.length; i++) {
      res[i] = Math.max(res[i-1], res[i-2] + nums[i]);
    }
    int bSum = res[nums.length - 1];
    return Math.max(fSum, bSum);
  }
}
