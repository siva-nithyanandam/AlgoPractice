package Sliding;
/**
 * @time 4/28/24-4:36 PM
 * @author sivaprakashnithyanandam
 */

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/find-the-median-of-the-uniqueness-array/description/
 * <p>
 * You are given an integer array nums. The uniqueness array of nums is the sorted array that contains the number of distinct elements of all the
 * subarrays
 * of nums. In other words, it is a sorted array consisting of distinct(nums[i..j]), for all 0 <= i <= j < nums.length.
 * <p>
 * Here, distinct(nums[i..j]) denotes the number of distinct elements in the subarray that starts at index i and ends at index j.
 * <p>
 * Return the median of the uniqueness array of nums.
 * <p>
 * Note that the median of an array is defined as the middle element of the array when it is sorted in non-decreasing order. If there are two choices for a median, the smaller of the two values is taken.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * The uniqueness array of nums is [distinct(nums[0..0]), distinct(nums[1..1]), distinct(nums[2..2]), distinct(nums[0..1]), distinct(nums[1..2]), distinct(nums[0..2])] which is equal to [1, 1, 1, 2, 2, 3]. The uniqueness array has a median of 1. Therefore, the answer is 1.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [3,4,3,4,5]
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * The uniqueness array of nums is [1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3]. The uniqueness array has a median of 2. Therefore, the answer is 2.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [4,3,5,4]
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * The uniqueness array of nums is [1, 1, 1, 1, 2, 2, 2, 3, 3, 3]. The uniqueness array has a median of 2. Therefore, the answer is 2.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class FindtheMedianoftheUniquenessArray {

    public static void main(String[] args) {
        FindtheMedianoftheUniquenessArray o = new FindtheMedianoftheUniquenessArray();
        System.out.println(o.medianOfUniquenessArray(new int[]{88,68,68,88,68}));
        System.out.println(o.medianOfUniquenessArray(new int[]{1, 2, 3, 3, 4}));
        System.out.println(o.medianOfUniquenessArray(new int[]{1, 2, 3}));
    }


    public int medianOfUniquenessArray(int[] nums) {

        Set<Integer> countTotalDistincts = new HashSet<>();

        for (int x : nums) {
            countTotalDistincts.add(x);
        }

        //Now I will apply binary search from 1 to totalDistinct in nums
        int low = 1, high = countTotalDistincts.size(), ans = 0;
        long n = nums.length;
        long totalSubarrays = (n * (n + 1)) / 2;
        high = nums.length;

        //median subaarray lies at which index or number
        long medianSubarrayIndex = (totalSubarrays + 1) / 2;

        while (low <= high) {
            int k = low + (high - low) / 2;

            //if this condition true means we have number of subarrays more than or equal to required subarray count which is median subarray
            /**
             Example we have nums of size 5
             then totalSubarrays = 5 * (6) / 2 = 15
             then median subarray = (15 + 1) / 2 = 8 it means 8th subarray will be our answer
             atMostKDistinctSubarrs(k) will return count of subarrays having atMost K distinct numbers in nums
             if count >= medianIndex then it will be candidate for ans & we will move left side of array in binary search to find nearest or equal to median
             */

            if (atMostKDistinctSubarrs(nums, k) >= medianSubarrayIndex) {
                ans = k;
                high = k - 1;
            } else {
                low = k + 1;
            }
        }
        return ans;
    }

    long atMostKDistinctSubarrs(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        int low = 0;
        long numberOfSubArrays = 0;

        for (int i = 0; i < nums.length; i++) {

            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

            while (map.size() > k) {

                map.put(nums[low], map.getOrDefault(nums[low], 0) - 1);

                if (map.get(nums[low]) == 0) {
                    map.remove(nums[low]);
                }
                low++;
            }

            //by adding we will get number of subarrays having atmost k distinct values
            //Note -> For array 1, 2, 3, 3, 4 subarrays are 1,2,2,3,4,(1,2), (2, 3), (3, 3), (3, 4), (2,3,4), (3,3,4)
            //
            numberOfSubArrays += i - low + 1;
        }
        return numberOfSubArrays;
    }

    private long subarraysWithDistinctUpToK(int[] nums, int k) {
        int[] cnt = new int[100001];  // Frequency count of numbers, assuming nums[i] is within 0 to 100000
        long res = 0;
        int j = 0;
        int sz = nums.length;

        for (int i = 0; i < sz; ++i) {
            if (++cnt[nums[i]] == 1) {  // Increment count and check if it's the first occurrence
                k--;
            }
            while (j < sz && k < 0) {  // Adjust j to maintain at most k distinct elements
                if (--cnt[nums[j++]] == 0) {  // Decrement count and check if it goes to zero
                    k++;
                }
            }
            if (k >= 0) {
                res += i - j + 1;  // Count valid subarrays from j to i
            }
        }
        // Clean up remaining counts for completeness
        while (j < sz) {
            --cnt[nums[j++]];
        }
        return res;
    }
}
