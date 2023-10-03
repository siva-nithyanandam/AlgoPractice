package arrays;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

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
    System.out.println(o.duplicatesTwo(new int[]{1,500, 500}));
    System.out.println(o.containsDuplicate_own(new Number[]{4,3,2,1}));
    System.out.println(o.containsDuplicate_2ms(new int[]{4,3,2,1}));
    System.out.println(o.containsDuplicate_2ms(new int[]{1,2,3,1}));
    System.out.println(o.containsDuplicate(new int[]{1,2,3,4}));
    System.out.println(o.containsDuplicate(new int[]{1,1,1,3,3,4,3,2,4,2}));
    System.out.println(o.containsDuplicate(new int[]{1,5,-2,-4,0}));
    System.out.println(o.containsDuplicate(new int[]{-1, 1073741823}));
  }

  public boolean containsDuplicate_own(Number[] nums) {
    Set<Number> uniques = new HashSet<>();
    boolean inserted;
    for (Number num : nums) {
      inserted = uniques.add(num);
      if (!inserted) {
        return true;
      }
    }
    return false;
  }

  public boolean containsDuplicate_own_another_try(int[] nums) {
    //1,2,3,4,1
    int n = nums.length;
    for (int i = 1; i < n; i++) {
      int j = i-1;
      int key = nums[i];

      while (j >= 0 && key < nums[j]) {
        nums[j+1] = nums[j];
        j--;
      }
      nums[j+1] = key;
      if (j >= 0 && nums[j] == nums[j+1]) {
        return true;
      }
    }
    return false;
  }
  public boolean containsDuplicate_2ms(int[] nums) {
    boolean duplicateFound = false;
    int arraySize = nums.length;
    for (int i = 1; i < arraySize; i++){
      int key = nums[i];
      int j = i - 1;
      while (j >= 0 && key < nums[j]){
        nums[j + 1] = nums[j];
        j -= 1;
      }
      nums[j + 1] = key;
      if (j >= 0 && nums[j] == key){
        duplicateFound = true;
        i = arraySize;
      }
    }
    return duplicateFound;
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

  static boolean duplicatesTwo(final int[] nums)
  {
    final int MAXZIP = 3;

    BitSet b = new BitSet();
    //b.set(0, MAXZIP, false);
    for (int item : nums) {
      if (!b.get(item)) {
        b.set(item, true);
      } else
        return true;
    }
    return false;
  }
}
