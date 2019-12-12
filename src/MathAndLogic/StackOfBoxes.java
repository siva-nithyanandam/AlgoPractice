package MathAndLogic;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You have a stack of n boxes, with widths 'w' heights 'h' and depths 'd'. The boxes
 * cannot be rotated and can only be stacked on top of one another if each box in the stack is strictly
 * larger than the box above it in width, height, and depth. Implement a method to compute the
 * height of the tallest possible stack. The height of a stack is the sum of the heights of each box
 */

/**
 * A variation of finding the longest sequences.
 * Arrange boxes in descending order of base area, and compare boxes that can be placed
 * on top of previous. If so add height.
 * https://www.youtube.com/watch?v=9mod_xRB-O0
 */
public class StackOfBoxes {
  public static void main(String[] args) {
    int[][] nums = {{2, 1, 5}, {4, 1, 2}, {5, 3, 2}, {5, 2, 3}, {3, 2, 5}, {4, 2, 1}};
    System.out.println(findMaxHeight(nums));
  }

  private static int findMaxHeight(int[][] nums) {
    sort(nums);
    int[] max = new int[nums.length];
    int[] res = new int[nums.length];
    for(int i = 0; i < nums.length; i++) {
      max[i] = nums[i][2];
    }
    Arrays.fill(res, -1);

    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i][0] < nums[j][0] && nums[i][1] < nums[j][1]) {
          res[i] = j;
          max[i] = Math.max(max[i], nums[i][2] + max[j]);
        }
      }
    }
    int maxH = 0;
    int maxI = 0;
    for (int i = 0; i < max.length; i++) {
      if (max[i] > maxH) {
        maxH = max[i];
        maxI = i;
      }
    }
    while(maxI != -1) {
      System.out.println(nums[maxI][0] + "," + nums[maxI][1] + "," + nums[maxI][2]);
      maxI = res[maxI];
    }
    return maxH;
  }

  private static void sort(int[][] nums) {
    Comparator<int[]> numsComparator = (o1, o2) -> (o2[0] * o2[1]) - (o1[0] * o1[1]);
    Arrays.sort(nums, numsComparator);
  }
}
