package MathAndLogic;

import java.util.Arrays;

/**
 * Given an array consists of non-negative integers, your task is to count the number
 * of triplets chosen from the array that can make triangles if we take them as side lengths
 * of a triangle.
 *
 * Example 1:
 * Input: [2,2,3,4]
 * Output: 3
 * Explanation:
 * Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 *
 * Note:
 * The length of the given array won't exceed 1000.
 * The integers in the given array are in the range of [0, 1000].
 */
public class ValidTriangleNumber {

    public static void main(String[] args) {
        ValidTriangleNumber o = new ValidTriangleNumber();
        System.out.println(o.findTheCountOfTriangles_faster(new int[]{1, 14,14}));
        System.out.println(o.findTheCountOfTriangles(new int[]{4, 6, 3, 7}));
        System.out.println(o.findTheCountOfTriangles_faster(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        System.out.println(o.findTheCountOfTriangles(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        System.out.println(o.findTheCountOfTriangles_faster(new int[]{2, 2, 1}));
        System.out.println(o.findTheCountOfTriangles_faster(new int[]{10, 21, 22, 100, 101, 200, 300}));
        System.out.println(o.findTheCountOfTriangles_faster(new int[]{2,2,3,4}));
        System.out.println(o.findTheCountOfTriangles_faster(new int[]{34,75,96,10,60,70,70,45}));
        System.out.println(o.findTheCountOfTriangles_faster(new int[]{10,34,45,60,70,70,75,96}));
        System.out.println(o.findTheCountOfTriangles_faster(new int[]{1, 2, 3}));
    }

    private int findTheCountOfTriangles_faster(int[] nums) {
        int result = 0;
        if (nums.length < 3) return result;
        Arrays.sort(nums);
        for(int i = 2; i < nums.length; i++){
            int left = 0;
            int right = i - 1;

            while(left < right){
                if((nums[left] + nums[right]) > nums[i]){
                    result += (right - left);
                    right--;
                }else{
                    left++;
                }
            }
        }
        return result;
    }
    private int findTheCountOfTriangles(int[] nums) {
        //Find the max
        int max = 0;
        for (int i : nums) {
            if (i > max) {
                max = i;
            }
        }
        //Count the numbers in its position
        int[] count = new int[(2*max)+1];
        for (int i : nums) {
            count[i]++;
        }
        int res = 0;
        //Iterate 0 as static and move rights
        for (int i = 0; i < nums.length - 2; i++) {
            count[nums[i]]--;
            int j;
            for (j = i+1; j < nums.length - 1; j++) {
                count[nums[j]]--;
                int diff = Math.abs(nums[i] - nums[j]);
                int sum = nums[i] + nums[j];
                while(++diff < sum) {
                    res += count[diff];
                }
            }
            while (--j > i) {
                count[nums[j]]++;
            }
        }
        return res;
    }
}
