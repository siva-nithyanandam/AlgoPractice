import MathAndLogic.LongestIncreasingSubsequence;

public class Test {

  public static void main(String[] args) {
    int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
    System.out.println(lengthOfLIS(nums));
  }

  private static int lengthOfLIS(int[] nums) {
    int[] temp = new int[nums.length];
    temp[0] = nums[0];
    int len = 0;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] <= temp[0]) {
        temp[0] = nums[i];
      } else if (nums[i] > temp[len]) {
        temp[++len] = nums[i];
      } else {
        int pos = findPos(temp, 0, len, nums[i]);
        temp[pos] = nums[i];
      }
    }
    return len+1;
  }

  private static int findPos(int[] temp, int start, int end, int target) {
    while (start <= end) {
      int mid = (start+end)/2;
      if (temp[mid] == target) {
        return mid;
      } else if (temp[mid] < target) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return start;
  }
}