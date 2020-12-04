package arrays;

/**
 * Given an array of integers, find if the array contains any duplicates.
 *
 * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 *
 * Example 1:
 *
 * Input: [1,2,3,1]
 * Output: true
 * Example 2:
 *
 * Input: [1,2,3,4]
 * Output: false
 * Example 3:
 *
 * Input: [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 */
public class ContainsDuplicate {

  public static void main(String[] args) {
    ContainsDuplicate o = new ContainsDuplicate();
    System.out.println(o.containsDuplicate(new int[]{1,2,3,1}));
    System.out.println(o.containsDuplicate(new int[]{1,2,3,4}));
    System.out.println(o.containsDuplicate(new int[]{1,1,1,3,3,4,3,2,4,2}));
    System.out.println(o.containsDuplicate(new int[]{1,5,-2,-4,0}));
    System.out.println(o.containsDuplicate(new int[]{-1, 1073741823}));
  }

  public boolean containsDuplicate(int[] nums) {
    if (nums.length < 2) {
      return false;
    }
    boolean[] arr = new boolean[2048];
    int a = 2047;
    for (int i = 0; i < nums.length; i++) {
      if ((arr[nums[i] & a])) {
        return true;
      } else {
        (arr[nums[i] & a]) = true;
      }
    }
    return false;
  }
}
