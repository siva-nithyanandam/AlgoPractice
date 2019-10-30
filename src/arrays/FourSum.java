package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 * Note:
 * The solution set must not contain duplicate quadruplets.
 * Example:
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
public class FourSum {

  public static void main(String[] args) {
    FourSum o = new FourSum();
    List<List<Integer>> fourSum;
    fourSum = o.fourSum(new int[]{-2, -1, 0, 0, 1, 2}, 0);
    printList(fourSum);

    fourSum = o.fourSum(new int[]{1,0,-1,0,-2,2}, 0);
    printList(fourSum);
  }

  private static void printList(List<List<Integer>> fourSum) {
    for(List<Integer> list : fourSum) {
      for (Integer i : list) {
        System.out.print(i + ",");
      }
      System.out.println();
    }
    System.out.println();
  }

  public List<List<Integer>> fourSum(int[] nums, int target) {
    int n = nums.length;
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> subRes;
    int pairSize = (n * (n-1) / 2);
    int[][] pairArr = new int[pairSize][3];
    int count = 0;
    for (int i = 0; i < n-1; i++) {
      for (int j = i+1; j < n; j++) {
        pairArr[count][0] = nums[i];
        pairArr[count][1] = nums[j];
        pairArr[count][2] = nums[i] + nums[j];
        count++;
      }
    }
    Arrays.sort(pairArr, (o1, o2) -> o1[2] - o2[2]);

    int i = 0;
    int j = pairArr.length - 1;
    int sum;
    while (i < j) {
      sum = pairArr[i][2] + pairArr[j][2];
      if (sum == target && noCommon(pairArr[i], pairArr[j])) {
        subRes = new ArrayList<>();
        subRes.add(pairArr[i][0]);
        subRes.add(pairArr[i][1]);
        subRes.add(pairArr[j][0]);
        subRes.add(pairArr[j][1]);
        res.add(subRes);
        i++;
        while (i < j && !noCommon(pairArr[i], pairArr[i-1])) {
          i++;
        }
      } else if (sum < target) {
        i++;
        while (i < j && !noCommon(pairArr[i], pairArr[i-1])) {
          i++;
        }
      } else {
        j--;
        while (j > i && !noCommon(pairArr[j], pairArr[j+1])) {
          j--;
        }
      }
    }
    return res;
  }

  private boolean noCommon(int[] pair1, int[] pair2) {
    if (pair1[0] == pair2[0] || pair1[0] == pair2[1]
        || pair1[1] == pair2[0] || pair1[1] == pair2[1]) {
      if (pair1[0] == 0 || pair1[1] != 0) {
        return true;
      }
      return false;
    }
    return true;
  }
}
