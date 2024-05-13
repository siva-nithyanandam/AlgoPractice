package arrays;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 * Sep 24,2023 - 1:28 PM
 */

/**
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 *
 *
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 *
 * https://leetcode.com/problems/majority-element/
 */
public class MajorityElement {

    public static void main(String[] args) {
        MajorityElement o = new MajorityElement();
        System.out.println(o.majorityElement_v1(new int[]{3,2,3}));
        System.out.println(o.majorityElement(new int[]{3,2,3}));
    }

    public int majorityElement(int[] nums) {
        int res = 0, count = 0;
        for(int n: nums) {
            if(count == 0) {
                res = n;
            }
            count += n == res? 1: -1;
        }
        return res;
    }

    public int majorityElement_v1(int[] nums) {
        int len = Integer.SIZE; // equivalent to sizeof(int) * 8 in C++
        int size = nums.length;
        int count = 0, mask = 1, ret = 0;

        for (int i = 0; i < len; ++i) { // Iterate over all bit positions
            count = 0;
            for (int j = 0; j < size; ++j) { // Count the number of 1s in the current bit position
                if ((mask & nums[j]) != 0) {
                    count++;
                }
            }
            if (count > size / 2) {
                ret |= mask; // Set the bit in the result if the majority of elements have this bit set
            }
            mask <<= 1; // Move to the next bit position
        }

        return ret;
    }
}
