package arrays;

/**
 * Given an integer array nums, find the contiguous subarray within an array
 * (containing at least one number) which has the largest product.
 * Example 1:
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaximumProductSubarray {

  public static void main(String[] args) {
    MaximumProductSubarray o = new MaximumProductSubarray();
    System.out.println(o.maxProduct(new int[]{-1,2,3,-2,4}));
    System.out.println(o.maxProduct(new int[]{-2,3,-4}));
    System.out.println(o.maxProduct(new int[]{2,3,-2,4}));
    System.out.println(o.maxProduct(new int[]{2,3,-2,4,5}));
    System.out.println(o.maxProduct(new int[]{-2,0,-1}));
    System.out.println(o.maxProduct(new int[]{-2,0,-1,-2}));
  }

  public int maxProduct(int[] nums) {
    int maxProduct = Integer.MIN_VALUE;
    int temp = 1;
    for (int i = 0; i < nums.length; i++) {
      temp *= nums[i];
      if (temp > maxProduct) {
        maxProduct = temp;
      }
      if (temp == 0) {
        temp = 1;
      }
    }
    temp = 1;
    for (int i = nums.length-1; i >= 0; i--) {
      temp *= nums[i];
      if (temp > maxProduct) {
        maxProduct = temp;
      }
      if (temp == 0) {
        temp = 1;
      }
    }
    return maxProduct;
  }

  public int maxProduct_own(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int ans = nums[0], prevMin = nums[0], prevMax = nums[0];
    int minSoFar;
    int maxSoFar;
    for (int i = 1; i < nums.length; i++) {
      maxSoFar = getMax(prevMin * nums[i], prevMax * nums[i], nums[i]);
      minSoFar = getMin(prevMin * nums[i], prevMax * nums[i], nums[i]);
      ans = Math.max(maxSoFar, ans);
      prevMax = maxSoFar;
      prevMin = minSoFar;
    }
    return ans;
  }

  private int getMax(int a, int b, int c) {
    return Math.max(a, Math.max(b, c));
  }

  private int getMin(int a, int b, int c) {
    return Math.min(a, Math.min(b, c));
  }
}
