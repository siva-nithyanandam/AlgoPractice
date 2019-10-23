package arrays;

import java.util.Arrays;

/**
 * Results:
 * [1, 2, 3, 4, 5, 6, 7]
 * [-1, 2, 3, 7, 8, 10]
 * [-4, -1, 3, 6, 9]
 */
public class Sortanarraywhentwohalvesaresorted {

  public static void main(String[] args) {
    Sortanarraywhentwohalvesaresorted o = new Sortanarraywhentwohalvesaresorted();
    int[] res;
    res = o.sort(new int[]{5, 6, 7, 1, 2, 3, 4});
    System.out.print(Arrays.toString(res));
    System.out.println();

    res = o.sort(new int[]{2, 3, 8, -1, 7, 10});
    System.out.print(Arrays.toString(res));
    System.out.println();

    res = o.sort(new int[]{-4, 6, 9, -1, 3});
    System.out.print(Arrays.toString(res));
    System.out.println();

  }

  private int[] sort(int[] nums) {
    int secondMinimal = 0;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] < nums[i-1]) {
        secondMinimal = i;
        break;
      }
    }
    int i = 0, j = secondMinimal;
    while (i < j) {
      if (nums[i] > nums[j]) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
      }
      if (j < nums.length-1) {
        if (nums[j] > nums[j+1]) {
          j++;
        }
      }
      i++;
    }
    return nums;
  }
}
