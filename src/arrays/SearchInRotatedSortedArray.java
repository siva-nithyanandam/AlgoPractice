package arrays;

public class SearchInRotatedSortedArray {

  public static void main(String[] args) {
    int[] nums = {4,5,6,7,0,1,2};
    int target = 0;
    System.out.print(search(nums, target));
  }

  public static int search(int[] nums, int target) {
    return search(nums, target, 0, nums.length - 1);
  }

  public static int search(int[] nums, int target, int start, int end) {
    if (start > end) {
      return -1;
    }
    int mid = start + (end / 2);
    if (target > nums[start] && target < nums[mid]) {
      return search(nums, target, start, mid);
    } else {
      return search(nums, target, mid + 1, end);
    }
  }

}
