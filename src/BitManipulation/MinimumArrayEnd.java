package BitManipulation;
/**
 * @time 4/28/24-12:23 PM
 * @author sivaprakashnithyanandam
 */

/**
 * https://leetcode.com/problems/minimum-array-end/description/
 *
 * You are given two integers n and x. You have to construct an array of positive integers nums of size n where for every 0 <= i < n - 1, nums[i + 1] is greater than nums[i], and the result of the bitwise AND operation between all elements of nums is x.
 *
 * Return the minimum possible value of nums[n - 1].
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, x = 4
 *
 * Output: 6
 *
 * Explanation:
 *
 * nums can be [4,5,6] and its last element is 6.
 *
 * Example 2:
 *
 * Input: n = 2, x = 7
 *
 * Output: 15
 *
 * Explanation:
 *
 * nums can be [7,15] and its last element is 15.
 *
 *
 *
 * Constraints:
 *
 * 1 <= n, x <= 108
 */
public class MinimumArrayEnd {

    public static void main(String[] args) {
        MinimumArrayEnd o = new MinimumArrayEnd();
        System.out.println(o.minEnd(4, 2));
        System.out.println(o.minEnd(4, 4));
        System.out.println(o.minEnd(3, 4));
        System.out.println(o.minEnd(2, 4));
        System.out.println(o.minEnd(2, 5));
    }

    public long minEnd(int n, int x) {
        n--;
        long a = x;
        for (long b = 1; n > 0; b <<= 1) {
            if ((b & x) == 0) {
                a |= (n & 1) * b;
                n >>= 1;
            }
        }
        return a;
    }

    /**
     * Solution 1
     * Each time increment a, then OR with x,
     * So we get the next bigger valid element.
     *
     * We do this n - 1 times,
     * and return the result.
     *
     * Time O(n)
     * Space O(1)
     * @param n
     * @param x
     * @return
     */
    public long minEnd_v1(int n, int x) {
        long tx = x;
        while (--n > 0) {
            tx = (tx+1) | x;
        }
        return tx;
    }
}
