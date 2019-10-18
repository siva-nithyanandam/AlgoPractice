package arrays;

/**
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [4,5,0,-2,-3,1], K = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by K = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 */
public class SubarraySumsDivisiblebyK {
    public static void main(String[] args) {
        SubarraySumsDivisiblebyK o = new SubarraySumsDivisiblebyK();
        System.out.println(o.subarraysDivByK(new int[]{4,5,0,-2,-3,1}, 5));
    }

    public int subarraysDivByK(int[] arr, int k) {
        int[] hash = new int[k];
        int prefix = 0, rem = 0, res = 0;
        hash[0] = 1;
        for (int i : arr) {
            prefix += i;
            prefix %= k;
            if (prefix < 0) {
                prefix += k;
            }
            if (hash[prefix] > 0) {
                res += hash[prefix];
                hash[prefix] += 1;
            } else {
                hash[prefix] = 1;
            }
        }
        return res;
    }
}
