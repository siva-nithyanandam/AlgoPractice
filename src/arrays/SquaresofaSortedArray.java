package arrays;

import java.util.Arrays;

/**
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Example 2:
 *
 * Input: [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A is sorted in non-decreasing order.
 */
public class SquaresofaSortedArray {
    public static void main(String[] args) {
        SquaresofaSortedArray o = new SquaresofaSortedArray();
        int[] res;
        res = o.sortedSquares_self(new int[]{-7,-3,2,3,11});
        res = o.sortedSquares_self(new int[]{-4, -1, 0, 3, 10});
        printList(res);

        res = o.sortedSquares(new int[]{-4, -3, -2, -1});
        printList(res);
    }

    public int[] sortedSquares_self(int[] nums) {

        int n = nums.length;
        int[] res = new int[n];
        int pos = n-1;
        int fs = nums[0] * nums[0];
        int flip = 1;
        int ls = 0;
        int f = 0, l = n-1;

        while (f <= l) {
            if (flip == 1) {
                ls = nums[l] * nums[l];
            } else {
                fs = nums[f] * nums[f];
            }
            if (ls > fs) {
                res[pos--] = ls;
                flip = 1;
                l--;
            } else {
                res[pos--] = fs;
                flip = 0;
                f++;
            }
        }
        return res;
    }

    public int[] sortedSquares_faster(int[] A) {
        int i = A.length - 1;
        int j = 0;
        int p = i;
        int[] ans = new int[i+1];
        while (j <= i) {
            int powS = A[j]*A[j];
            int powE = A[i]*A[i];
            if (powS > powE) {
                ans[p--]  = powS;
                j++;
            } else {
                ans[p--] = powE;
                i--;
            }
        }
        return ans;
    }

    private static void printList(int[] res) {
        System.out.print(Arrays.toString(res));
        System.out.println();
    }

    public int[] sortedSquares(int[] A) {
        int minPositive = A.length;
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0) {
                minPositive = i;
                break;
            }
        }
        int maxNegative = minPositive - 1;
        int[] res = new int[A.length];
        int counter = 0;
        while(counter < A.length) {
            if (minPositive < A.length && maxNegative >= 0) {
                int positive = A[minPositive] * A[minPositive];
                int negative = A[maxNegative] * A[maxNegative];
                if (positive > negative) {
                    res[counter] = negative;
                    maxNegative--;
                } else {
                    res[counter] = positive;
                    minPositive++;
                }
            } else if (minPositive < A.length) {
                res[counter] = A[minPositive] * A[minPositive];
                minPositive++;
            } else {
                res[counter] = A[maxNegative] * A[maxNegative];
                maxNegative--;
            }
            counter++;
        }
        return res;
    }
}
