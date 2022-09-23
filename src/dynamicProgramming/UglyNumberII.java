package dynamicProgramming;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 10/25/2021  12:30 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.TreeSet;

/**
 * https://leetcode.com/problems/ugly-number-ii/
 *
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 *
 * Given an integer n, return the nth ugly number.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: 12
 * Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1690
 */
public class UglyNumberII {
    public static void main(String[] args) {
        UglyNumberII o = new UglyNumberII();
        System.out.println(o.nthUglyNumber(10));
    }

    public int nthUglyNumber_faster(int n) {
        int[] result = new int[n];
        result[0] = 1;

        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;

        for(int i=1;i<n;i++){
            int min = Math.min(Math.min(factor2,factor3),factor5);

            result[i] = min;
            if(factor2 == min)
                factor2 = 2*result[++index2];
            if(factor3 == min)
                factor3 = 3*result[++index3];
            if(factor5 == min)
                factor5 = 5*result[++index5];
        }

        return result[n-1];
    }

    private long nthUglyNumber(int n) {
        TreeSet<Long> t = new TreeSet<>();
        // Ugly number sequence starts with 1
        t.add(1L);
        int i = 1;
        // when i==n we have the nth ugly number
        while (i < n) {
            // remove the ith ugly number and add back its
            // multiples of 2,3 and 5 back to the set
            long temp = t.pollFirst();
            t.add(temp * 2);
            t.add(temp * 3);
            t.add(temp * 5);
            i++;
            // the first element of set is always the ith
            // ugly number
        }
        return t.pollFirst();
    }
}
