package MathAndLogic;

import java.util.Arrays;

import com.temp.ValidRectangleNumber;

/**
 * Given an array consists of non-negative integers, your task is to count
 * the number of quadruplets chosen from the array that can make rectangles if
 * we take them as side lengths of a rectangle.
 */
public class ValidRectangleNumber {

    public static void main(String[] args) {
        ValidRectangleNumber o = new ValidRectangleNumber();
        System.out.println(o.findTheCountOfRectangle(new int[]{1, 2, 1, 2, 3, 3}));
        System.out.println(o.findTheCountOfRectangle(new int[]{1, 2, 1, 2, 1}));
        System.out.println(o.findTheCountOfRectangle(new int[]{1, 2, 1, 2, 3, 3, 1}));
    }

    private int findTheCountOfRectangle(int[] nums) {
        Arrays.sort(nums);
        int prevI = 0;
        int prev = nums[0];
        int[] subRes = new int[nums.length];
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != prev) {
                subRes[count] = binomialCoefficient(i-prevI, 2);
                count++;
                prevI = i;
                prev = nums[i];
            }
        }
        subRes[count] = binomialCoefficient(nums.length-prevI, 2);
        int res = 0;
        for (int i = 0; i < count; i++) {
        	for (int j = i+1; j <=count; j++) {
        		res += subRes[i] * subRes[j];
        	}
        }
        return res;
    }

    private int binomialCoefficient(int n, int k) {
        if (n == k || k == 0) {
            return 1;
        }
        return binomialCoefficient(n-1, k-1) + binomialCoefficient(n-1, k);
    }
}
