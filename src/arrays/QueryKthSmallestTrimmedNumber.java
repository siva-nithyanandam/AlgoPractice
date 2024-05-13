package arrays;

import java.util.*;

/**
 * You are given a 0-indexed array of strings nums, where each string is of equal length and consists of only digits.
 *
 * You are also given a 0-indexed 2D integer array queries where queries[i] = [ki, trimi]. For each queries[i], you need to:
 *
 * Trim each number in nums to its rightmost trimi digits.
 * Determine the index of the kith smallest trimmed number in nums. If two trimmed numbers are equal, the number with the lower index is considered to be smaller.
 * Reset each number in nums to its original length.
 * Return an array answer of the same length as queries, where answer[i] is the answer to the ith query.
 *
 * Note:
 *
 * To trim to the rightmost x digits means to keep removing the leftmost digit, until only x digits remain.
 * Strings in nums may contain leading zeros.
 *
 *
 * Example 1:
 *
 * Input: nums = ["102","473","251","814"], queries = [[1,1],[2,3],[4,2],[1,2]]
 * Output: [2,2,1,0]
 * Explanation:
 * 1. After trimming to the last digit, nums = ["2","3","1","4"]. The smallest number is 1 at index 2.
 * 2. Trimmed to the last 3 digits, nums is unchanged. The 2nd smallest number is 251 at index 2.
 * 3. Trimmed to the last 2 digits, nums = ["02","73","51","14"]. The 4th smallest number is 73.
 * 4. Trimmed to the last 2 digits, the smallest number is 2 at index 0.
 *    Note that the trimmed number "02" is evaluated as 2.
 * Example 2:
 *
 * Input: nums = ["24","37","96","04"], queries = [[2,1],[2,2]]
 * Output: [3,0]
 * Explanation:
 * 1. Trimmed to the last digit, nums = ["4","7","6","4"]. The 2nd smallest number is 4 at index 3.
 *    There are two occurrences of 4, but the one at index 0 is considered smaller than the one at index 3.
 * 2. Trimmed to the last 2 digits, nums is unchanged. The 2nd smallest number is 24.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i].length <= 100
 * nums[i] consists of only digits.
 * All nums[i].length are equal.
 * 1 <= queries.length <= 100
 * queries[i].length == 2
 * 1 <= ki <= nums.length
 * 1 <= trimi <= nums[i].length
 */
public class QueryKthSmallestTrimmedNumber {

    QueryKthSmallestTrimmedNumber o = new QueryKthSmallestTrimmedNumber();

//    Input: nums = ["102","473","251","814"], queries = [[1,1],[2,3],[4,2],[1,2]]
//    Output: [2,2,1,0]

//    System.out.println(Arrays.toString(o.smallestTrimmedNumbers(new String[]{"64333639502","65953866768","17845691654","87148775908","58954177897","70439926174","48059986638","47548857440","18418180516","06364956881","01866627626","36824890579","14672385151","71207752868"},
//            new int[][]{{9,4},{6,1},{3,8},{12,9},{11,4},{4,9},{2,7},{10,3},{13,1},{13,1},{6,1},{5,10}})));


    public class Pair {
        String str;
        int idx;
        public Pair(String str, int idx) {
            this.str = str;
            this.idx = idx;
        }
    }
    public int[] smallestTrimmedNumbers(String[] nums, int[][] q) {
        int ans[] = new int[q.length];
        for(int i = 0; i < q.length; i++) {
            int k = q[i][0], t = q[i][1];
            List<Pair> list = new ArrayList<>();
            for(int j = 0; j < nums.length; j++)list.add(new Pair(nums[j].substring(nums[j].length() - t, nums[j].length()), j));
            Collections.sort(list, (a, b) -> {return a.str.compareTo(b.str);});
            ans[i] = list.get(k - 1).idx;
        }
        return ans;
    }

    public int[] smallestTrimmedNumbers_own(String[] nums, int[][] queries) {
        int[] ans = new int[queries.length];

        PriorityQueue<String[]> pq = new PriorityQueue<>((arr1, arr2) -> {
            if (arr1[0].equals(arr2[0])) {
                return Integer.parseInt(arr2[1]) - Integer.parseInt(arr1[1]);
            }
            return arr2[0].compareTo(arr1[0]);
        });

        int j = 0;
        for (int[] q : queries) {

            for (int i = 0; i < nums.length; i++) {
                String s = nums[i].substring(nums[i].length()-q[1]);
                pq.add(new String[]{s, String.valueOf(i)});
                if (pq.size() > q[0]) {
                    pq.poll();
                }
            }
            ans[j] = Integer.parseInt(pq.remove()[1]);
            j++;
            pq.clear();
        }
        return ans;
    }


}
