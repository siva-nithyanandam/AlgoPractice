package arrays;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/14/2021  12:25 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 *
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 */
public class KthLargestElementinanArray {

  public static void main(String[] args) {
    KthLargestElementinanArray o = new KthLargestElementinanArray();
    System.out.println(o.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
  }

  public int findKthLargest(int[] nums, int k) {
    // init heap 'the smallest element first'
    PriorityQueue<Integer> heap =
        new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

    // keep k largest elements in the heap
    for (int n: nums) {
      heap.add(n);
      if (heap.size() > k)
        heap.poll();
    }

    // output
    return heap.poll();
  }
}
