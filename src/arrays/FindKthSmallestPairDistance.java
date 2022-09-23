package arrays;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-k-th-smallest-pair-distance/
 *
 * The distance of a pair of integers a and b is defined as the absolute difference between a and b.
 *
 * Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,1], k = 1
 * Output: 0
 * Explanation: Here are all the pairs:
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
 * Example 2:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 0
 * Example 3:
 *
 * Input: nums = [1,6,1], k = 3
 * Output: 5
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 2 <= n <= 104
 * 0 <= nums[i] <= 106
 * 1 <= k <= n * (n - 1) / 2
 */
public class FindKthSmallestPairDistance {

    public static void main(String[] args) {
        FindKthSmallestPairDistance o = new FindKthSmallestPairDistance();
        System.out.println(o.smallestDistancePair(new int[]{62,100,4}, 2));
        System.out.println(o.smallestDistancePair(new int[]{2,4,5,8,9}, 9));
        System.out.println(o.smallestDistancePair(new int[]{1,2,6,7}, 3));
    }

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            int count = 0, left = 0;
            for (int right = 0; right < nums.length; ++right) {
                while (nums[right] - nums[left] > mi) {
                    left++;
                }
                count += right - left;
            }
            //count = number of pairs with distance <= mi
            if (count >= k) {
                hi = mi;
            }
            else {
                lo = mi + 1;
            }
        }
        return lo;
    }

    public int smallestDistancePair1(int[] nums, int k) {
        Arrays.sort(nums);
        int WIDTH = 2 * nums[nums.length - 1];

        //multiplicity[i] = number of nums[j] == nums[i] (j < i)
        int[] multiplicity = new int[nums.length];
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i-1]) {
                multiplicity[i] = 1 + multiplicity[i - 1];
            }
        }

        //prefix[v] = number of values <= v
        int[] prefix = new int[WIDTH];
        int left = 0;
        for (int i = 0; i < WIDTH; ++i) {
            while (left < nums.length && nums[left] == i) {
                left++;
            }
            prefix[i] = left;
        }

        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; ++i) {
                count += prefix[nums[i] + mi] - prefix[nums[i]] + multiplicity[i];
            }
            //count = number of pairs with distance <= mi
            if (count >= k) hi = mi;
            else lo = mi + 1;
        }
        return lo;
    }
}
