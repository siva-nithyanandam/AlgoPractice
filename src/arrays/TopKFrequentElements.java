package arrays;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/14/2021  12:59 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {

  public static void main(String[] args) {
    TopKFrequentElements o = new TopKFrequentElements();
    System.out.println(Arrays.toString(o.topKFrequent(new int[]{1,1,1,2,2,3,3}, 2)));
  }

  public int[] topKFrequent(int[] nums, int k) {
    // O(1) time
    if (k == nums.length) {
      return nums;
    }

    // 1. build hash map : character and how often it appears
    // O(N) time
    Map<Integer, Integer> count = new HashMap();
    for (int n: nums) {
      count.put(n, count.getOrDefault(n, 0) + 1);
    }

    // init heap 'the less frequent element first'
    Queue<Integer> heap = new PriorityQueue<>(
        (n1, n2) -> count.get(n1) - count.get(n2));

    // 2. keep k top frequent elements in the heap
    // O(N log k) < O(N log N) time
    for (int n: count.keySet()) {
      heap.add(n);
      if (heap.size() > k) heap.poll();
    }

    // 3. build an output array
    // O(k log k) time
    int[] top = new int[k];
    for(int i = k - 1; i >= 0; --i) {
      top[i] = heap.poll();
    }
    return top;
  }
}
