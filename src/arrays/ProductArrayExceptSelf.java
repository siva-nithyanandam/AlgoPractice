package arrays;

/**
 * Given an array nums of n integers where n > 1,
 * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * Example:
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 * Follow up:
 * Could you solve it with constant space complexity?
 * (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class ProductArrayExceptSelf {

  public static void main(String[] args) {
    ProductArrayExceptSelf o = new ProductArrayExceptSelf();
    int[] res;

    res = o.productExceptSelf(new int[]{1,2,3,4});
    printRes(res);
  }

  private static void printRes(int[] res) {
    for (int i : res) {
      System.out.print(i + ",");
    }
  }

  public int[] productExceptSelf(int[] nums) {
    if (nums.length == 0) {
      return nums;
    }
    int[] res = new int[nums.length];
    res[0] = 1;
    for (int i = 0; i < nums.length - 1; i++) {
      res[i+1] = res[i] * nums[i];
    }
    int right = 1;
    for (int i = nums.length-1; i >= 0; i--) {
      res[i] = res[i] * right;
      right *= nums[i];
    }
    return res;
  }
}
