package pointers;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 * Jul 11,2023 - 6:10 PM
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode.com/problems/max-number-of-k-sum-pairs/description/?envType=study-plan-v2&envId=leetcode-75
 *
 * You are given an integer array nums and an integer k.
 *
 * In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
 *
 * Return the maximum number of operations you can perform on the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5, hence a total of 2 operations.
 * Example 2:
 *
 * Input: nums = [3,1,3,4,3], k = 6
 * Output: 1
 * Explanation: Starting with nums = [3,1,3,4,3]:
 * - Remove the first two 3's, then nums = [1,4,3]
 * There are no more pairs that sum up to 6, hence a total of 1 operation.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= k <= 109
 */
public class MaxNumberofKSumPairs {

    public static void main(String[] args) {
        MaxNumberofKSumPairs o = new MaxNumberofKSumPairs();
        System.out.println();
    }

    public int maxOperations_18ms(int[] nums, int k) {
        HashMap<Integer, AtomicInteger> numbers = new HashMap<Integer, AtomicInteger>();
        int operations = 0;
        AtomicInteger value, complement;

        for(int i=0; i < nums.length; i++) {
            if(nums[i] >= k) {
                continue;
            } else {
                complement = numbers.get(k-nums[i]);
                if(complement != null && complement.get() != 0) {
                    complement.getAndDecrement();
                    operations++;
                } else {
                    value = numbers.get(nums[i]);
                    if(value != null) {
                        value.getAndIncrement();
                    } else {
                        numbers.put(nums[i], new AtomicInteger(1));
                    }
                }
            }
        }

        return operations;
    }

    private static final String[] VOWELS = {"a"};
    private boolean isVowel(char c) {
        return Arrays.stream(VOWELS).anyMatch(x -> x.equals(c));
    }

    public int maxOperations_20ms(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        int res = 0;

        while (i < j) {
            int tempSum = nums[i] + nums[j];
            if (tempSum == k) {
                res++;
                i++;
                j--;
            } else if (tempSum < k) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }
}
