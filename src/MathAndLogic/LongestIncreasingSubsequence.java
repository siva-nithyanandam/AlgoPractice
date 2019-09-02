package MathAndLogic;

import java.util.Arrays;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 */

/**
 * Two solutions for this.
 * O{n^2} - Just compare all the previous elements of the curr and increase the count.
 * O(n logn) - https://www.youtube.com/watch?v=S9oUiVYEq7E
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] nums;
        nums = new int[]{10,9,2,5,3,7,101,18};
//        System.out.println(lis.lengthOfLIS(nums));

        nums = new int[]{3, 4, -1, 5, 8, 2, 3, 12, 7, 9, 10};
//        System.out.println(lis.lengthOfLIS(nums));

        nums = new int[]{4,10,4,3,8,9};
//        System.out.println(lis.lengthOfLIS(nums));

        nums = new int[]{3,5,6,2,5,4,19,5,6,7,12};
        System.out.println(lis.lengthOfLIS(nums));
    }

    public int lengthOfLIS(int[] nums) {
        int[] temp = new int[nums.length];
        int length = 0;
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[temp[0]]) {
                temp[0] = i;
            } else if (nums[i] > nums[temp[length]]) {
                length++;
                temp[length] = i;
                res[i] = temp[length - 1];
            } else {
                int pos = findPos(nums, temp, 0, length, nums[i]);
                res[i] = temp[pos-1];
                temp[pos] = i;
            }
        }
        return length+1;
    }

    private int findPos(int[] nums, int[] temp, int start, int end, int target) {
        if (start > end) {
            return start;
        }
        int mid = (start + end) / 2;
        if (target == nums[temp[mid]]) {
            return mid;
        } else if (target >= nums[temp[mid]]) {
            return findPos(nums, temp, mid+1, end, target);
        } else {
            return findPos(nums, temp, start, mid-1, target);
        }
    }

    public int lengthOfLIS_n2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = 0;
        int[] result = new int[nums.length];
        for (int curr = 1; curr < nums.length; curr++) {
            for (int i = 0; i < curr; i++) {
                if (nums[curr] > nums[i]) {
                    result[curr] = Math.max(result[curr], result[i] + 1);
                }
                max = Math.max(max, result[curr]);
            }
        }
        return max + 1;
    }
}
