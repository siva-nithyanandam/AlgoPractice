package Heap;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 * May 09,2024 - 9:52 PM
 */

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/k-th-smallest-prime-fraction/description/?envType=daily-question&envId=2024-05-10
 *
 * You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique. You are also given an integer k.
 *
 * For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].
 *
 * Return the kth smallest fraction considered. Return your answer as an array of integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j].
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,5], k = 3
 * Output: [2,5]
 * Explanation: The fractions to be considered in sorted order are:
 * 1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
 * The third fraction is 2/5.
 * Example 2:
 *
 * Input: arr = [1,7], k = 1
 * Output: [1,7]
 *
 *
 * Constraints:
 *
 * 2 <= arr.length <= 1000
 * 1 <= arr[i] <= 3 * 104
 * arr[0] == 1
 * arr[i] is a prime number for i > 0.
 * All the numbers of arr are unique and sorted in strictly increasing order.
 * 1 <= k <= arr.length * (arr.length - 1) / 2
 *
 *
 * Follow up: Can you solve the problem with better than O(n2) complexity?
 */

/**
 * Few References: https://leetcode.com/problems/k-th-smallest-prime-fraction/solutions/115819/summary-of-solutions-for-problems-reducible-to-leetcode-378/
 * Very good one: almost O(N) solution: https://www.youtube.com/watch?v=sJdJTXhxqjo
 */
public class KthSmallestPrimeFraction {

    public static void main(String[] args) {
        KthSmallestPrimeFraction o = new KthSmallestPrimeFraction();
        System.out.println(o.kthSmallestPrimeFraction(new int[]{1,2,3,5}, 3));
    }

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;

        double low = 0;
        double high = 1.0;

        while(low<high)
        {
            double mid = (low+high)/2;
            int res[] = getFractionsLessThanMid(arr, k, n, mid);

            if(res[0] == k) return new int[]{arr[res[1]],arr[res[2]]};
            else if(res[0]>k) high = mid;
            else low = mid;
        }

        return new int[]{};
    }

    private int[] getFractionsLessThanMid(int[] arr, int k, int n, double mid)
    {
        double maxLessThanMid = 0.0;
        int x = 0, y = 0;

        int total = 0;
        int j = 1;

        for(int i=0;i<n-1;i++)
        {
            while(j<n && arr[i]>arr[j]*mid)
            {
                j++;
            }

            if(j==n) break;

            total += (n-j);

            double fraction = (double)arr[i]/arr[j];
            if(fraction > maxLessThanMid)
            {
                maxLessThanMid = fraction;
                x = i;
                y = j;
            }
        }
        return new int[]{total, x ,y};
    }

    public int[] kthSmallestPrimeFraction_own(int[] arr, int k) {
        PriorityQueue<float[]> pq = new PriorityQueue<>((a,b) -> (b[0] > a[0] ? 1 : -1));
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i+1; j < n; j++) {
                pq.add(new float[]{(float)arr[i]/arr[j], arr[i], arr[j]});
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
        float[] ans = pq.poll();
        return new int[]{(int)ans[1], (int)ans[2]};
    }
}
