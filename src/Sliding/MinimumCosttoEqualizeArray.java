package Sliding;
/**
 * @time 5/5/24-1:34 PM
 * @author sivaprakashnithyanandam
 */

/**
 * https://leetcode.com/problems/minimum-cost-to-equalize-array/description/
 * <p>
 * You are given an integer array nums and two integers cost1 and cost2. You are allowed to perform either of the following operations any number of times:
 * <p>
 * Choose an index i from nums and increase nums[i] by 1 for a cost of cost1.
 * Choose two different indices i, j, from nums and increase nums[i] and nums[j] by 1 for a cost of cost2.
 * Return the minimum cost required to make all elements in the array equal.
 * <p>
 * Since the answer may be very large, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,1], cost1 = 5, cost2 = 2
 * <p>
 * Output: 15
 * <p>
 * Explanation:
 * <p>
 * The following operations can be performed to make the values equal:
 * <p>
 * Increase nums[1] by 1 for a cost of 5. nums becomes [4,2].
 * Increase nums[1] by 1 for a cost of 5. nums becomes [4,3].
 * Increase nums[1] by 1 for a cost of 5. nums becomes [4,4].
 * The total cost is 15.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [2,3,3,3,5], cost1 = 2, cost2 = 1
 * <p>
 * Output: 6
 * <p>
 * Explanation:
 * <p>
 * The following operations can be performed to make the values equal:
 * <p>
 * Increase nums[0] and nums[1] by 1 for a cost of 1. nums becomes [3,4,3,3,5].
 * Increase nums[0] and nums[2] by 1 for a cost of 1. nums becomes [4,4,4,3,5].
 * Increase nums[0] and nums[3] by 1 for a cost of 1. nums becomes [5,4,4,4,5].
 * Increase nums[1] and nums[2] by 1 for a cost of 1. nums becomes [5,5,5,4,5].
 * Increase nums[3] by 1 for a cost of 2. nums becomes [5,5,5,5,5].
 * The total cost is 6.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [3,5,3], cost1 = 1, cost2 = 3
 * <p>
 * Output: 4
 * <p>
 * Explanation:
 * <p>
 * The following operations can be performed to make the values equal:
 * <p>
 * Increase nums[0] by 1 for a cost of 1. nums becomes [4,5,3].
 * Increase nums[0] by 1 for a cost of 1. nums becomes [5,5,3].
 * Increase nums[2] by 1 for a cost of 1. nums becomes [5,5,4].
 * Increase nums[2] by 1 for a cost of 1. nums becomes [5,5,5].
 * The total cost is 4.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 * 1 <= cost1 <= 106
 * 1 <= cost2 <= 106
 */
public class MinimumCosttoEqualizeArray {

    public static void main(String[] args) {
        MinimumCosttoEqualizeArray o = new MinimumCosttoEqualizeArray();
        System.out.println(o.minCostToEqualizeArray(new int[]{1, 14, 14, 15}, 2, 1));
        System.out.println(o.minCostToEqualizeArray(new int[]{2, 3, 3, 3, 5}, 2, 1));
    }

    /**
     *
     * https://leetcode.com/problems/minimum-cost-to-equalize-array/solutions/5114202/java-c-python-4-cases-o-n-solution/
     *
     * Calculate the min(A) and max(A)
     * total = max(A) * n - sum(A), total is the minimum increments need to do.
     *
     * Case 1:
     * c1 * 2 < c2
     * operation 1 is always better operation 2,
     * we simply return total * c1 % mod
     *
     * Case 2:
     * c1 * 2 > c2
     * We do operation 2 as many as possible,
     * and use operation 1 to complete.
     *
     * Case 3:
     * c1 * 2 >> c2
     * Previously we increment to max(A),
     * now we can increment to max(A) + x
     * So that can do few operation 1 and mostly operation 2.
     *
     * Case 4:
     * c1 * 2 >>>>> c2
     * We won't do any operation 1.
     *
     *
     * Complexity
     * Time O(n)
     * Space O(1)
     * @param A
     * @param c1
     * @param c2
     * @return
     */
    public int minCostToEqualizeArray(int[] A, int c1, int c2) {
        int ma = A[0], mi = A[0], n = A.length, mod = 1000000007;
        long total = 0;
        for (int a : A) {
            mi = Math.min(mi, a);
            ma = Math.max(ma, a);
            total += a;
        }
        total = 1l * ma * n - total;

        // case 1
        if (c1 * 2 <= c2 || n <= 2) {
            return (int) ((total * c1) % mod);
        }

        // case 2
        long op1 = Math.max(0L, (ma - mi) * 2L - total);
        long op2 = total - op1;
        long res = (op1 + op2 % 2) * c1 + op2 / 2 * c2;

        // case 3
        total += op1 / (n - 2) * n;
        op1 %= n - 2;   
        op2 = total - op1;
        res = Math.min(res, (op1 + op2 % 2) * c1 + op2 / 2 * c2);

        // case 4
        for (int i = 0; i < 2; i++) {
            total += n;
            res = Math.min(res, total % 2 * c1 + total / 2 * c2);
        }
        return (int) (res % mod);
    }
}
