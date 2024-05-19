package dynamicProgramming;
/**
 * @time 5/19/24-12:36 PM
 * @author sivaprakashnithyanandam
 */

import java.util.List;

/**
 * There exists an undirected tree with n nodes numbered 0 to n - 1. You are given a 0-indexed 2D integer array edges of length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the tree. You are also given a positive integer k, and a 0-indexed array of non-negative integers nums of length n, where nums[i] represents the value of the node numbered i.
 *
 * Alice wants the sum of values of tree nodes to be maximum, for which Alice can perform the following operation any number of times (including zero) on the tree:
 *
 * Choose any edge [u, v] connecting the nodes u and v, and update their values as follows:
 * nums[u] = nums[u] XOR k
 * nums[v] = nums[v] XOR k
 * Return the maximum possible sum of the values Alice can achieve by performing the operation any number of times.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nums = [1,2,1], k = 3, edges = [[0,1],[0,2]]
 * Output: 6
 * Explanation: Alice can achieve the maximum sum of 6 using a single operation:
 * - Choose the edge [0,2]. nums[0] and nums[2] become: 1 XOR 3 = 2, and the array nums becomes: [1,2,1] -> [2,2,2].
 * The total sum of values is 2 + 2 + 2 = 6.
 * It can be shown that 6 is the maximum achievable sum of values.
 * Example 2:
 *
 *
 * Input: nums = [2,3], k = 7, edges = [[0,1]]
 * Output: 9
 * Explanation: Alice can achieve the maximum sum of 9 using a single operation:
 * - Choose the edge [0,1]. nums[0] becomes: 2 XOR 7 = 5 and nums[1] become: 3 XOR 7 = 4, and the array nums becomes: [2,3] -> [5,4].
 * The total sum of values is 5 + 4 = 9.
 * It can be shown that 9 is the maximum achievable sum of values.
 * Example 3:
 *
 *
 * Input: nums = [7,7,7,7,7,7], k = 3, edges = [[0,1],[0,2],[0,3],[0,4],[0,5]]
 * Output: 42
 * Explanation: The maximum achievable sum is 42 which can be achieved by Alice performing no operations.
 *
 *
 * Constraints:
 *
 * 2 <= n == nums.length <= 2 * 104
 * 1 <= k <= 109
 * 0 <= nums[i] <= 109
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= edges[i][0], edges[i][1] <= n - 1
 * The input is generated such that edges represent a valid tree.
 */
public class FindtheMaximumSumofNodeValues {

    public static void main(String[] args) {
        FindtheMaximumSumofNodeValues o = new FindtheMaximumSumofNodeValues();
        List<Integer> nums = List.of(1, 1, 1);
        int k = 1;
        List<List<Integer>> edges = List.of();
        System.out.println(o.maximumValueSum(nums, k, edges));  // Example usage
    }

    /**
     * https://leetcode.com/problems/find-the-maximum-sum-of-node-values/solutions/4819751/beat-100-o-n-o-1-most-efficient-solution-one-pass-with-easy-to-read-code/
     *
     * Intuition
     * Each node is like a toggle button, when we push it, its value becomes x XOR k; when we push it again, it's value becomes x XOR k XOR k = x. Yes it recovers its original value!
     * We are allowed to push two buttons that are adjacent in the graph. However, we can choose any path a1→a2→a3a_1 \rightarrow a_2 \rightarrow a_3a
     * 1
     * ​
     *  →a
     * 2
     * ​
     *  →a
     * 3
     * ​
     *   and apply the operations on both a1↔a2a_1 \leftrightarrow a_2a
     * 1
     * ​
     *  ↔a
     * 2
     * ​
     *   and a2↔a3a_2 \leftrightarrow a_3a
     * 2
     * ​
     *  ↔a
     * 3
     * ​
     *  , and end result is that a2a_2a
     * 2
     * ​
     *   recovers while a1a_1a
     * 1
     * ​
     *   and a3a_3a
     * 3
     * ​
     *   is pushed. We can extend the path to any length to connect any two nodes and push them at the same time. This means we can choose push any group of even number of nodes.
     * When we push a button, we gain some value to the total sum, which is (x XOR k) - x. If this gain is positive, we consider it a gain. Otherwise, we consider it a lost (negative of the gain)
     * The optimal solution, at this point, is intuitively to:
     * pick all the gains if the count of gains is even, or if it's odd, we pick the best of the following two:
     * pick all the gains except the smallest gain
     * pick all the gains and the smallest lost
     * Edges are not needed. As long as the graph is connected we can always reach the optimal solution.
     * Approach
     * We run through the nums array once to gather the necessary stats:
     *
     * sum: sum of nums + all gain
     * smallest_gain: the smallest gain (positive gain)
     * smallest_lost: the smallest -gain (negative gain)
     * gain_count: the number of gain
     * And then we apply the optimal solution.
     *
     * Complexity
     * Time complexity:
     * Θ(n)\Theta(n)Θ(n)
     *
     * Space complexity:
     * Θ(1)\Theta(1)Θ(1)
     * @param nums
     * @param k
     * @param edges
     * @return
     */
    public long maximumValueSum(List<Integer> nums, int k, List<List<Integer>> edges) {
        long sum = 0;
        int smallestGain = 2 * k + 1;
        int smallestLost = 2 * k + 1;
        int gainCount = 0;

        for (int num : nums) {
            int gain = (num ^ k) - num;
            if (gain > 0) {
                smallestGain = Math.min(smallestGain, gain);
                sum += num + gain;
                gainCount++;
            } else {
                smallestLost = Math.min(smallestLost, -gain);
                sum += num;
            }
        }

        if (gainCount % 2 == 1) {
            sum -= Math.min(smallestGain, smallestLost);
        }

        return sum;
    }
}
