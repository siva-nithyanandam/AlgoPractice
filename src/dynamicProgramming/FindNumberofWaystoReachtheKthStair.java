package dynamicProgramming;
/**
 * @time 5/19/24-2:27 PM
 * @author sivaprakashnithyanandam
 */

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/contest/weekly-contest-398/problems/find-number-of-ways-to-reach-the-k-th-stair/description/
 *
 * You are given a non-negative integer k. There exists a staircase with an infinite number of stairs, with the lowest stair numbered 0.
 *
 * Alice has an integer jump, with an initial value of 0. She starts on stair 1 and wants to reach stair k using any number of operations. If she is on stair i, in one operation she can:
 *
 * Go down to stair i - 1. This operation cannot be used consecutively or on stair 0.
 * Go up to stair i + 2jump. And then, jump becomes jump + 1.
 * Return the total number of ways Alice can reach stair k.
 *
 * Note that it is possible that Alice reaches the stair k, and performs some operations to reach the stair k again.
 *
 *
 *
 * Example 1:
 *
 * Input: k = 0
 *
 * Output: 2
 *
 * Explanation:
 *
 * The 2 possible ways of reaching stair 0 are:
 *
 * Alice starts at stair 1.
 * Using an operation of the first type, she goes down 1 stair to reach stair 0.
 * Alice starts at stair 1.
 * Using an operation of the first type, she goes down 1 stair to reach stair 0.
 * Using an operation of the second type, she goes up 20 stairs to reach stair 1.
 * Using an operation of the first type, she goes down 1 stair to reach stair 0.
 * Example 2:
 *
 * Input: k = 1
 *
 * Output: 4
 *
 * Explanation:
 *
 * The 4 possible ways of reaching stair 1 are:
 *
 * Alice starts at stair 1. Alice is at stair 1.
 * Alice starts at stair 1.
 * Using an operation of the first type, she goes down 1 stair to reach stair 0.
 * Using an operation of the second type, she goes up 20 stairs to reach stair 1.
 * Alice starts at stair 1.
 * Using an operation of the second type, she goes up 20 stairs to reach stair 2.
 * Using an operation of the first type, she goes down 1 stair to reach stair 1.
 * Alice starts at stair 1.
 * Using an operation of the first type, she goes down 1 stair to reach stair 0.
 * Using an operation of the second type, she goes up 20 stairs to reach stair 1.
 * Using an operation of the first type, she goes down 1 stair to reach stair 0.
 * Using an operation of the second type, she goes up 21 stairs to reach stair 2.
 * Using an operation of the first type, she goes down 1 stair to reach stair 1.
 *
 *
 * Constraints:
 *
 * 0 <= k <= 109
 */
public class FindNumberofWaystoReachtheKthStair {

    public static void main(String[] args) {
        FindNumberofWaystoReachtheKthStair o = new FindNumberofWaystoReachtheKthStair();
        System.out.println(o.waysToReachStair(8388597));
        System.out.println(o.waysToReachStair(0));
        System.out.println(o.waysToReachStair(1));
    }

    public int waysToReachStair(int k) {
        Map<Integer, Map<Integer, Map<Integer, Integer>>> mem = new HashMap<>();
        return solve(1, k, 0, 0, mem);
    }

    private int solve(int i, int k, int wasDownJump, int jump, Map<Integer, Map<Integer, Map<Integer, Integer>>> mem) {
        if (i > k+1) {
            return 0;
        }
        if (mem.containsKey(i)) {
            if (mem.get(i).containsKey(wasDownJump)) {
                if (mem.get(i).get(wasDownJump).containsKey(jump)) {
                    return mem.get(i).get(wasDownJump).get(jump);
                }
            }
        }

        int ans = 0;

        ans += i == k ? 1 : 0;

        //Go up
        ans += solve(i + (1 << jump), k, 0, jump+1, mem);

        //Go down
        if (i != 0 && wasDownJump == 0) {
            ans += solve(i-1, k, 1, jump, mem);
        }
        mem.computeIfAbsent(i, none -> new HashMap<>()).computeIfAbsent(wasDownJump, none -> new HashMap<>()).put(jump, ans);
        return ans;
    }
}
