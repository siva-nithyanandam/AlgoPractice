package arrays;

/**
 * Given an array of integers nums sorted in ascending order,
 * find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */

/**
 * Two binary searches.
 * One to find the lower bound and other to find upper bound.
 */
public class SearchForARange {

    public static void main(String[] args) {
        int[] nums;
        int target;
        int[] res;

        nums = new int[]{121, 121, 121, 129, 129, 132, 132, 132, 132};
        target = 129;
        res = searchRange(nums, target);
        System.out.println(res[0] + " " + res[1]);

        nums = new int[]{5,7,7,8,8,10};
        target = 8;
        res = searchRange(nums, target);
        System.out.println(res[0] + " " + res[1]);

        nums = new int[]{5,7,7,8,8,10};
        target = 6;
        res = searchRange(nums, target);
        System.out.println(res[0] + " " + res[1]);
    }

    public static int[] searchRange(int[] nums, int target) {
        int lower = binarySearch(nums, 0, nums.length - 1, target, false);
        int upper = binarySearch(nums, 0, nums.length - 1, target, true);
        return new int[]{lower, upper};
    }

    private static int binarySearch(int[] nums, int start, int end, int target, boolean upper) {
        int res = -1;
        while(start <= end) {
            int mid = start + (end - start)/2;
            if (nums[mid] == target) {
                res = mid;
                if (!upper) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return res;
    }
}
