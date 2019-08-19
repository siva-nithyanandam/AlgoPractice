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
        int[] nums;
        nums = new int[]{1, 2, 3, 4};
        System.out.println(maxSubArray(nums));

        nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));

        nums = new int[]{1, 2, 3, 4};
        System.out.println(maxSubArrayByDivideAndConquer(nums, 0, nums.length - 1));

        nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArrayByDivideAndConquer(nums, 0, nums.length - 1));
    }

    private static int maxSubArrayByDivideAndConquer(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        } else if (start > end) {
            return 0;
        }
        int mid = (start + end) / 2;
        return maxCrossingSum(nums, start, mid, end);
    }

    static int maxCrossingSum(int arr[], int l,
        int m, int h)
    {
        // Include elements on left of mid.
        int sum = 0;
        int left_sum = Integer.MIN_VALUE;
        for (int i = m; i >= l; i--)
        {
            sum = sum + arr[i];
            if (sum > left_sum)
                left_sum = sum;
        }

        // Include elements on right of mid
        sum = 0;
        int right_sum = Integer.MIN_VALUE;
        for (int i = m + 1; i <= h; i++)
        {
            sum = sum + arr[i];
            if (sum > right_sum)
                right_sum = sum;
        }
        // Return sum of elements on left
        // and right of mid
        return left_sum + right_sum;
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
