package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * Note:
 * The solution set must not contain duplicate triplets.
 * Example:
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class ThreeSum {
  public static void main(String[] args) {
    ThreeSum o = new ThreeSum();
    List<List<Integer>> res = o.threeSum_faster(new int[]{-1, 0, 1, 2, -1, -4});
    printList(res);

    res = o.threeSum_faster(new int[]{-2,0,0,2,2});
    printList(res);
  }

  public List<List<Integer>> threeSum_faster(int[] nums) {
      int len = nums.length;
      if (len < 1) return new ArrayList<>();
      // Sort input array 1st
      Arrays.sort(nums);
      List<List<Integer>> res = new ArrayList<>();
      // Allocate enough space to avoid check in if statement
      int max = Math.max(nums[len - 1], Math.abs(nums[0]));
      byte[] hash = new byte[(max << 1) + 1];
      // Hash and count appearing times of every num
      for (int v : nums) {
        hash[v + max]++;
      }
      // Search the position of 0.
      // It also represents the position of the last negative number in the array
      int lastNeg = Arrays.binarySearch(nums, 0);
      // The pos. of the 1st pos. number in the array
      int firstPos = lastNeg;
      // 0 not found
      if (lastNeg < 0) {
        firstPos = ~lastNeg;
        lastNeg = -lastNeg - 2;
        // see Java API
      } else {
        // found
        // skip all 0
        while (lastNeg >= 0 && nums[lastNeg] == 0) --lastNeg;
        while (firstPos < len && nums[firstPos] == 0) ++firstPos;
        int zeroCount = firstPos - lastNeg - 1;
        // 0 appears 3 times at least
        if (zeroCount >= 3) {
          res.add(Arrays.asList(0, 0, 0));
        }
        // 0 appears at least 1 time
        if (zeroCount > 0) {
          // traverse all the pos. numbers to see whether or not there's a neg. number whose abs. val.
          // equals the pos. number
          for (int i = firstPos; i < len; ++i) {
            // skip duplicate (same) elements
            if (i > firstPos && nums[i] == nums[i - 1]) continue;
            if (hash[-nums[i] + max] > 0) {
              res.add(Arrays.asList(0, nums[i], -nums[i]));
            }
          }
        }
      }
      // one positive number and two negetive numbers
      // traverse all the pos. numbers to find whether there are 2 neg. numbers to make the 3 numbers
      // add up to 0
      for (int i = firstPos; i < len; ++i) {
        // skip dups. (same elements)
        if (i > firstPos && nums[i] == nums[i - 1]) continue;
        // we can only traverse half of the pos. numbers
        int half;
        if (nums[i] % 2 != 0) half = -((nums[i] >> 1) + 1);
        else {
          half = -(nums[i] >> 1);
          if (hash[half + max] > 1) res.add(Arrays.asList(nums[i], half, half));
        }
        for (int j = lastNeg; j >= 0 && nums[j] > half; --j) {
          if (j < lastNeg && nums[j] == nums[j + 1]) continue;
          if (hash[(-nums[i] - nums[j]) + max] > 0)
            res.add(Arrays.asList(nums[i], nums[j], -nums[i] - nums[j]));
        }
      }
      // one negative number and two positive numbers
      // traverse all the negative numbers to find whether there are two positive numbers to make the
      // 3 numbers add up to 0
      for (int i = lastNeg; i >= 0; --i) {
        // skip dups. (same elements)
        if (i < lastNeg && nums[i] == nums[i + 1]) continue;
        // we can only traverse half of the negative numbers
        int half;
        if (nums[i] % 2 != 0) half = -(nums[i] / 2 - 1);
        else {
          half = -(nums[i] >> 1);
          if (hash[half + max] > 1) res.add(Arrays.asList(nums[i], half, half));
        }
        for (int j = firstPos; j < len && nums[j] < half; ++j) {
          if (j > firstPos && nums[j] == nums[j - 1]) continue;
          if (hash[(-nums[i] - nums[j]) + max] > 0)
            res.add(Arrays.asList(nums[i], nums[j], -nums[i] - nums[j]));
        }
      }
      return res;
  }

  private static void printList(List<List<Integer>> res) {
    for (List<Integer> list : res) {
      for (int i : list) {
        System.out.print(i+ ",");
      }
      System.out.println();
    }
  }

  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    if (nums.length < 3) {
      return res;
    }

    List<Integer> subRes;
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      if( i > 0 && nums[i] == nums[i-1]) continue;
      int j = i+1;
      int k = nums.length-1;
      while (j < k) {
        int sum = nums[i] + nums[j] + nums[k];
        if (sum == 0) {
          subRes = new ArrayList<>();
          subRes.add(nums[i]);
          subRes.add(nums[j]);
          subRes.add(nums[k]);
          res.add(subRes);
          j++;
          k--;
          while(j < k && nums[j] == nums[j-1]) j++;
        } else if (sum > 0) {
          k--;
        } else {
          j++;
        }
      }
    }
    return res;
  }
}