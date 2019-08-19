package arrays;

/**
 * Given an integer array nums, find the contiguous subarray
 * (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * <p>
 * Follow up:
 * If you have figured out the O(n) solution,
 * try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));

        //TODO yet to complete
        System.out.println(maxSubArrayByDivideAndConquer(nums, 0, nums.length - 1));
    }

    private static int maxSubArrayByDivideAndConquer(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        } else if (start > end) {
            return 0;
        }
        int mid = (start + end) / 2;
        int leftMax = maxSubArrayByDivideAndConquer(nums, start, mid - 1);
        int rightMax = maxSubArrayByDivideAndConquer(nums, mid + 1, end);
        return maxVal(leftMax, nums[mid], rightMax, leftMax + nums[mid], nums[mid] + rightMax, leftMax + nums[mid] + rightMax);
    }

    private static int maxVal(int a, int b, int c, int ab, int bc, int abc) {
        int max = a > b ? a : b;
        max = max > c ? max : c;
        max = max > ab ? max : ab;
        max = max > bc ? max : bc;
        max = max > abc ? max : abc;
        return max;
    }

    private static int maxSubArray(int[] nums) {
        int ans = nums[0], cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i] + cur, nums[i]);
            if (ans < cur) ans = cur;
        }
        return ans;
    }
}
