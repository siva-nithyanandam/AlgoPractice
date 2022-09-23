package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/contest/biweekly-contest-62/problems/maximum-number-of-ways-to-partition-an-array/
 *
 * You are given a 0-indexed integer array nums of length n. The number of ways to partition nums is the number of pivot indices that satisfy both conditions:
 *
 * 1 <= pivot < n
 * nums[0] + nums[1] + ... + nums[pivot - 1] == nums[pivot] + nums[pivot + 1] + ... + nums[n - 1]
 * You are also given an integer k. You can choose to change the value of one element of nums to k, or to leave the array unchanged.
 *
 * Return the maximum possible number of ways to partition nums to satisfy both conditions after changing at most one element.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,-1,2], k = 3
 * Output: 1
 * Explanation: One optimal approach is to change nums[0] to k. The array becomes [3,-1,2].
 * There is one way to partition the array:
 * - For pivot = 2, we have the partition [3,-1 | 2]: 3 + -1 == 2.
 * Example 2:
 *
 * Input: nums = [0,0,0], k = 1
 * Output: 2
 * Explanation: The optimal approach is to leave the array unchanged.
 * There are two ways to partition the array:
 * - For pivot = 1, we have the partition [0 | 0,0]: 0 == 0 + 0.
 * - For pivot = 2, we have the partition [0,0 | 0]: 0 + 0 == 0.
 * Example 3:
 *
 * Input: nums = [22,4,-25,-20,-15,15,-16,7,19,-10,0,-13,-14], k = -33
 * Output: 4
 * Explanation: One optimal approach is to change nums[2] to k. The array becomes [22,4,-33,-20,-15,15,-16,7,19,-10,0,-13,-14].
 * There are four ways to partition the array.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 2 <= n <= 105
 * -105 <= k, nums[i] <= 105
 */
public class MaximumNumberofWaystoPartitionanArray {

    public static void main(String[] args) {
        MaximumNumberofWaystoPartitionanArray o = new MaximumNumberofWaystoPartitionanArray();
        System.out.println(o.waysToPartition(new int[]{2,-1,2}, 3));
        System.out.println(o.waysToPartition(new int[]{0,0,0,0,0,0,0,0,0,-4732,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, -4732));
        System.out.println(o.waysToPartition(new int[]{0,0,0,0,2,0,0,0,0}, 0));
    }

    public int waysToPartition(int[] nums, int k) {
        int n = nums.length;
        long[] arr = new long[n];
        long totalSum = 0;
        for (int i = 0; i < n; ++i) {
            arr[i] = (long)(nums[i]);
            totalSum += arr[i];
        }
        long[] sum = new long[n];
        sum[0] = arr[0];

        for (int i = 1; i < n; ++i) {
            sum[i] = sum[i-1] + (long)arr[i];
        }

        int res = 0;

        Map<Long, Integer> leftSum = new HashMap(), rightSum = new HashMap();

        for (int idx = 1; idx < n; ++idx) {
            if (sum[idx-1] == sum[n-1] - sum[idx-1]) {
                res ++;
            }
        }

        long temp = 0;
        for (int i = n - 1; i > 0; --i) {
            temp += arr[i];
            rightSum.put(temp, rightSum.getOrDefault(temp, 0) + 1);
        }

        temp = 0;
        long newSum = totalSum + (long)(k) - arr[0];
        if (newSum % 2 == 0 && rightSum.containsKey(newSum / 2)) {
            res = Math.max(res, rightSum.get(newSum / 2));
        }
        for (int idx = 1; idx < n; ++idx) {
            temp += arr[idx-1];
            leftSum.put(temp, leftSum.getOrDefault(temp, 0) + 1);
            if (rightSum.containsKey(totalSum - temp)) {
                int fr = rightSum.get(totalSum - temp);
                if (fr == 1)
                    rightSum.remove(totalSum - temp);
                else
                    rightSum.put(totalSum - temp, fr - 1);
            }

            newSum = totalSum + (long)(k) - arr[idx];
            if (newSum % 2 != 0)
                continue;

            int cl = leftSum.getOrDefault(newSum / 2, 0), cr = rightSum.getOrDefault(newSum / 2, 0);
            res = Math.max(res, cl + cr);
        }

        return res;
    }


    public int waysToPartition_self_TLE(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSums = new int[n];
        int[] suffixSums = new int[n];

        prefixSums[0] = nums[0];
        suffixSums[n-1] = nums[n-1];

        for (int i = 1; i < n; i++) {
            prefixSums[i] = prefixSums[i-1] + nums[i];
            suffixSums[n-i-1] = suffixSums[n-i] + nums[n-i-1];
        }
        int res = isPartitionEqual(prefixSums, suffixSums, 0, n);
        for (int i = 0; i < n; i++) {
            res = Math.max(res, isPartitionEqual(prefixSums, suffixSums, k-nums[i], i));
        }
        return res;
    }

    private int isPartitionEqual(int[] prefixSums, int[] suffixSums, int diff, int pos) {
        int res = 0;
        for (int i = 0; i < prefixSums.length-1; i++) {
            if (i >= pos) {
                if (prefixSums[i] + diff == suffixSums[i+1]) {
                    res++;
                }
            } else {
                if (prefixSums[i] == suffixSums[i+1] + diff) {
                    res++;
                }
            }
        }
        return res;
    }
}
