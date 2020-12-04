package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,2,1], nums2 = [2,2] Output: [2,2] Example 2:
 * <p>
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4] Output: [4,9] Note:
 * <p>
 * Each element in the result should appear as many times as it shows in both arrays. The result can
 * be in any order. Follow up:
 * <p>
 * What if the given array is already sorted? How would you optimize your algorithm? What if nums1's
 * size is small compared to nums2's size? Which algorithm is better? What if elements of nums2 are
 * stored on disk, and the memory is limited such that you cannot load all elements into the memory
 * at once?
 */
public class IntersectionofTwoArraysII {

  public static void main(String[] args) {
    IntersectionofTwoArraysII o = new IntersectionofTwoArraysII();
    System.out.println(Arrays.toString(o.intersect_fastest(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
    System.out.println(Arrays.toString(o.intersect_faster(new int[]{9,4,9,8,4}, new int[]{4, 9, 5})));
  }

  public int[] intersect_fastest(int[] nums1, int[] nums2) {
    // sort both arrays
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int i = 0, j = 0, k = 0;
    while (i < nums1.length && j < nums2.length) {
      if (nums1[i] < nums2[j]) {
        i++;
      } else if (nums2[j] < nums1[i]) {
        j++;
      } else {
        nums1[k] = nums1[i];
        i++;
        j++;
        k++;
      }
    }
    return Arrays.copyOfRange(nums1, 0, k);
  }

  public int[] intersect_faster(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
      return intersect_faster(nums2, nums1);
    }
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int start = 0, end = nums2.length-1;
    int k = 0;
    for (int i = 0; i < nums1.length; i++) {
      int idx = binarySearch(nums1[i], nums2, start, end);
      if (idx != -1) {
        nums1[k++] = nums1[i];
        start = idx+1;
      }
    }
    return Arrays.copyOfRange(nums1, 0, k);
  }

  private int binarySearch(int target, int[] nums, int start, int end) {
    if (start <= end) {
      int mid = start + (end-start)/2;
      if (nums[mid] == target) {
        while(mid > start && nums[mid] == nums[mid-1]) {
          mid--;
        }
        return mid;
      } else if (nums[mid] > target) {
        return binarySearch(target, nums, start, mid-1);
      } else {
        return binarySearch(target, nums, mid+1, end);
      }
    } else {
      return -1;
    }
  }

  public int[] intersect(int[] nums1, int[] nums2) {
    if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
      return new int[0];
    }
    // step1: Put elements in nums1 into the map
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int num : nums1) {
      if (map.containsKey(num)) {
        map.put(num, map.get(num) + 1);
      } else {
        map.put(num, 1);
      }
    }
// step 2: iterate the nums2 and get the result
    List<Integer> result = new ArrayList<>();

    for (int num : nums2) {
      if (map.containsKey(num) && map.get(num) > 0) {
        result.add(num);
        int freq = map.get(num);
        freq--;
        map.put(num, freq);
      }
    }

    return listToArray(result);
  }

  private int[] listToArray(List<Integer> list) {
    int[] result = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
      result[i] = list.get(i);
    }

    return result;
  }
}
