package MathAndLogic;

/**
 * https://leetcode.com/problems/jump-game/
 *
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 */
public class JumpGame {

  public static void main(String[] args) {
    JumpGame o = new JumpGame();
    System.out.println(o.canJump(new int[]{2,0,0}));
    System.out.println(o.canJump(new int[]{2,0}));
    System.out.println(o.canJump(new int[]{0,0}));
    System.out.println(o.canJump_own(new int[]{1,1,1,1,0}));
    System.out.println(o.canJump_own(new int[]{2,3,1,1,4}));
    System.out.println(o.canJump(new int[]{3,2,1,0,4}));
  }

  public boolean canJump_own(int[] nums) {

    int i = 0, maxJ = 0;

    while(i <= maxJ) {
      if (nums[i] >= maxJ) {
        maxJ = i + nums[i];
      }
      if (maxJ >= nums.length-1) {
        return true;
      }
      i++;
    }
    return false;
  }

  public boolean canJump_faster(int[] nums) {
    int last = nums.length - 1;
    for (int i = nums.length - 2; i > -1; i--) {
      if (nums[i] + i >= last) {
        last = i;
      }
    }
    return last == 0;
  }

  public boolean canJump(int[] nums) {
    int max = 0;
    for(int i=0; i<nums.length; i++) {
      if(i > max) return false;
      max = Math.max(max, nums[i]+i);
    }
    return true;
  }
}
