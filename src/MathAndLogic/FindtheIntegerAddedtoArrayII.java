package MathAndLogic;
/**
 * @time 4/28/24-9:36 AM
 * @author sivaprakashnithyanandam
 */

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-the-integer-added-to-array-ii/description/
 *
 * You are given two integer arrays nums1 and nums2.
 *
 * From nums1 two elements have been removed, and all other elements have been increased (or decreased in the case of negative) by an integer, represented by the variable x.
 *
 * As a result, nums1 becomes equal to nums2. Two arrays are considered equal when they contain the same integers with the same frequencies.
 *
 * Return the minimum possible integer x that achieves this equivalence.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [4,20,16,12,8], nums2 = [14,18,10]
 *
 * Output: -2
 *
 * Explanation:
 *
 * After removing elements at indices [0,4] and adding -2, nums1 becomes [18,14,10].
 *
 * Example 2:
 *
 * Input: nums1 = [3,5,5,3], nums2 = [7,7]
 *
 * Output: 2
 *
 * Explanation:
 *
 * After removing elements at indices [0,3] and adding 2, nums1 becomes [7,7].
 *
 *
 *
 * Constraints:
 *
 * 3 <= nums1.length <= 200
 * nums2.length == nums1.length - 2
 * 0 <= nums1[i], nums2[i] <= 1000
 * The test cases are generated in a way that there is an integer x such that nums1 can become equal to nums2 by removing two elements and adding x to each element of nums1.
 */
public class FindtheIntegerAddedtoArrayII {

    public static void main(String[] args) {
        FindtheIntegerAddedtoArrayII o = new FindtheIntegerAddedtoArrayII();
        System.out.println(o.minimumAddedInteger(new int[]{3,5,5,3}, new int[]{7,7}));
    }

    /**
     * More elegant solution
     * Dynamically skipping(max of 2) the elements on the go
     * @param nums1
     * @param nums2
     * @return
     */
    public int minimumAddedInteger(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int min = Integer.MAX_VALUE;
        int skip;
        for (int i = 0; i < 3; i++) {
            skip = i;

            for (int j = i+1; j - skip < nums2.length && skip < 3; j++) {
                if (nums2[0] - nums1[i] != nums2[j - skip] - nums1[j]) {
                    skip++;
                }
            }

            if (skip < 3) {
                min = Math.min(min, nums2[0] - nums1[i]);
            }
        }
        return min;
    }

    /**
     * Skips the elements in the nums1 and match the diff with rest of the arrays and find min of those diff
     * More straight forward than others
     * @param nums1
     * @param nums2
     * @return
     */
    public int minimumAddedInteger_v1(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            int diff = nums2[0] - nums1[i];
            if (check(nums1, nums2, diff, 2 - i, i)) {
                min = Math.min(min, diff);
            }
        }
        return min;
    }

    private boolean check(int[] nums1, int[] nums2, int diff, int skips, int i) {

        for (int j = 0; i < nums1.length && j < nums2.length && skips >= 0; i++) {
            if (nums1[i] + diff == nums2[j]) {
                j++;
            } else {
                skips--;
            }
        }
        return skips >= 0;
    }
}
