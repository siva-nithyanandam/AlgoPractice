package SearchAndSorting;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/9/2021  4:59 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/explore/interview/card/google/63/sorting-and-searching-4/3081/
 *
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 *
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 */
public class FindFirstandLastPositionofElementinSortedArray {

  public static void main(String[] args) {
    FindFirstandLastPositionofElementinSortedArray o = new FindFirstandLastPositionofElementinSortedArray();
    System.out.println(o.searchRange(new int[]{5,7,7,8,8,10}, 8));
  }

  public int[] searchRange(int[] nums, int target) {

    int f, l = -1;
    f = binarySearch(nums, target, true);
    if (f != -1) {
      l = binarySearch(nums, target, false);
    }
    return new int[]{f, l};
  }

  private int binarySearch(int[] nums, int target, boolean isFirst) {
    int low = 0, high = nums.length-1;

    while (low <= high) {
      int mid = low + (high - low)/2;

      if (nums[mid] == target) {
        if (isFirst) {
          if (mid == 0 || nums[mid-1] != target) {
            return mid;
          } else {
            high = mid-1;
          }
        } else {
          if (mid == nums.length-1 || nums[mid+1] != target) {
            return mid;
          } else {
            low = mid+1;
          }
        }
      } else if (nums[mid] < target) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return -1;
  }

}
