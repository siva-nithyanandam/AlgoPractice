package arrays;

public class SearchInRotatedSortedArray {

  public static void main(String[] args) {
    int[] nums;
    int target;

    target = 0;
    nums = new int[]{0,1};
    System.out.println(search(nums, target));

    target = 0;
    nums = new int[]{4,5,6,7,0,1,2};
    System.out.println(search(nums, target));

    target = 0;
    nums = new int[]{0,1,2,3,4,5,6,7};
    System.out.println(search(nums, target));

    target = 7;
    nums = new int[]{0,1,2,3,4,5,6,7};
    System.out.println(search(nums, target));

    target = 7;
    nums = new int[]{7,6,5,4,3,2,1,0};
    System.out.println(search(nums, target));
  }

  public static int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    //Find pivot point where ascending order starts
    int pivot = pivotedBinarySearch(nums, 0, nums.length - 1);
    pivot = pivot == -1 ? nums.length - 1 : pivot;
    if (nums[0] <= target) {
      return binarySearch(nums, 0, pivot, target);
    } else {
      return binarySearch(nums, pivot+1, nums.length - 1, target);
    }
  }

  private static int binarySearch(int[] nums, int start, int end, int target) {
    if (start > end) {
      return -1;
    } else {
      int mid = (start + end)/2;
      if (nums[mid] == target) {
        return mid;
      } else {
        if (target < nums[mid]) {
          return binarySearch(nums, start, mid-1, target);
        }
        return binarySearch(nums, mid + 1, end, target);
      }
    }
  }

  public static int pivotedBinarySearch(int[] nums, int start, int end) {
    if (start > end) {
      return -1;
    } else if (start == end) {
      return start;
    }
    int mid = (start + end) / 2;
    if (mid < end && nums[mid] > nums[mid + 1]) {
      return mid;
    } else if (start < mid && nums[mid] < nums[mid - 1]) {
      return mid - 1;
    } else {
      if (nums[mid] > nums[start]) {
        return pivotedBinarySearch(nums, mid + 1, end);
      } else {
        return pivotedBinarySearch(nums, start, mid - 1);
      }
    }
  }

}
