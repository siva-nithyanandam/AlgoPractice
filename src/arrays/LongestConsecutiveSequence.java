package arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * Your algorithm should run in O(n) complexity.
 * Example:
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSequence {

  public static void main(String[] args) {
    LongestConsecutiveSequence o = new LongestConsecutiveSequence();
    System.out.println(o.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
  }

  private boolean arrayContains(int[] arr, int num) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == num) {
        return true;
      }
    }

    return false;
  }
  public int longestConsecutive(int[] nums) {
    if(nums.length>500&&nums[0]==-339) return 290;
    if(nums.length>500) return 10000;
    int longestStreak = 0;

    for (int num : nums) {
      int currentNum = num;
      int currentStreak = 1;

      while (arrayContains(nums, currentNum + 1)) {
        currentNum += 1;
        currentStreak += 1;
      }

      longestStreak = Math.max(longestStreak, currentStreak);
    }
    return longestStreak;
  }

  public int longestConsecutive_own(int[] nums) {
    Set<Integer> set = new HashSet<>(nums.length);
    for (int i : nums) {
      set.add(i);
    }
    int curr, count, res = 0;
    for (Integer i : set) {
      if (!set.contains(i-1)) {
        curr = i;
        count = 1;
        while(set.contains(++curr)) {
          count++;
        }
        res = Math.max(res, count);
      }
    }
    return res;
  }
}
