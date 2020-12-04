package arrays;

/**
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
 *
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,3,2]
 * Output: 3
 * Example 2:
 *
 * Input: [0,1,0,1,0,1,99]
 * Output: 99
 */
public class SingleNumberII {

  public static void main(String[] args) {
    SingleNumberII o = new SingleNumberII();
    System.out.println(o.singleNumber(new int[]{2,2,3,2}));
    System.out.println(o.singleNumber(new int[]{0,1,0,1,0,1,99}));
    System.out.println(o.singleNumber(new int[]{1}));
  }
  public int singleNumber(int[] nums) {
    int x = 0;
    int ones = 0, twos = 0, threes = 0;
    for (int i = 0; i < nums.length; i++) {
      twos |= ones & nums[i];
      ones = ones ^ nums[i];
      threes = ones & twos;
      ones = ones & (~threes);
      twos = twos & (~threes);
    }
    return ones;
  }
}
