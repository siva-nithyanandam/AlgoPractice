package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums
 * such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
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

    fourSum = o.fourSum_faster(new int[]{-1, -1, -1, 0, 0, 1, 2, 3}, 0);
    printList(fourSum);

    fourSum = o.fourSum_faster(new int[]{-2, -1, 0, 0, 1, 2}, 0);
    printList(fourSum);

    fourSum = o.fourSum(new int[]{1,0,-1,0,-2,2}, 0);
    printList(fourSum);

    fourSum = o.fourSum(new int[]{-3,-2,-1,0,0,1,2,3}, 0);
    printList(fourSum);

    fourSum = o.fourSum(new int[]{-1,0,1,2,-1,-4}, -1);
    printList(fourSum);

    fourSum = o.fourSum(new int[]{-1,0,-5,-2,-2,-4,0,1,-2}, -9);
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

  public List<List<Integer>> fourSum_faster(int[] nums, int target) {
    List<List<Integer>> res = new ArrayList<>();
    if(nums == null || nums.length < 4) {
      return res;
    }
    Arrays.sort(nums);
    int max = nums[nums.length - 1];
    if(4 * nums[0] > target || 4 * max < target) {
      return res;
    }

    int z;
    for(int i = 0; i < nums.length; i++) {
      z = nums[i];
      if(i > 0 && z == nums[i - 1])
        continue;
      if(z + 3 * max < target)
        continue;
      if(4 * z > target)
        break;
      if(4 * z == target) {
        if(i + 3 < nums.length && nums[i + 3]  == z) {
          res.add(Arrays.asList(z, z, z, z));
        }
        break;
      }
      threeSumForFourSum(nums, target - z, i + 1, nums.length - 1, res, z);
    }
    return res;
  }

  private void threeSumForFourSum(int[] nums, int target, int low, int high, List<List<Integer>> res, int z1) {
    if(low + 1 >= high)
      return;
    int max = nums[high];
    if(3* nums[low] > target || 3 * max < target)
      return;

    int i, z;
    for (i = low; i < high - 1; i++) {
      z = nums[i];
      if (i > low && z == nums[i - 1])
        continue;
      if(z + 2 * max < target)
        continue;
      if(3 * z > target)
        break;
      if(3 * z == target) {
        if(i + 1 < high && nums[i + 2] == z)
          res.add(Arrays.asList(z1, z, z, z));
        break;
      }
      twoSumForFourSum(nums, target- z, i + 1, high, res, z1, z);
    }
  }

  private void twoSumForFourSum(int[] nums, int target, int low, int high, List<List<Integer>> res, int z1, int z2) {
    if(low >= high)
      return;
    if(2 * nums[low] > target || 2 * nums[high] < target)
      return;

    int i = low, j = high, sum, x;
    while(i < j) {
      sum = nums[i] + nums[j];
      if(sum == target) {
        res.add(Arrays.asList(z1, z2, nums[i], nums[j]));
        x = nums[i];
        while(++i < j && x == nums[i])
          continue;
        x = nums[j];
        while(i < --j && x == nums[j])
          continue;
      }
      if(sum < target)
        i++;
      if(sum > target)
        j--;
    }
    return;
  }

  public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> subRes;
    if (nums.length < 4) {
      return res;
    }
    Arrays.sort(nums);
    int n = nums.length;
    int l, r;
    for (int i = 0; i < n-3; i++) {
      if (i > 0 && nums[i] == nums[i-1]) {
        continue;
      }
      if (nums[i] * 4 > target) {
        break;
      }
      for (int j = i+1; j < n-2; j++) {
        if (j != i+1 && nums[j] == nums[j-1]) {
          continue;
        }
        if (nums[j] * 3 > target-nums[i]) {
          break;
        }
        l = j+1;
        r = n-1;
        while (l < r) {
          if (nums[l] * 2 > target - nums[i] - nums[j]) {
            break;
          }
          int sum = nums[i] + nums[j] + nums[l] + nums[r];
          if (sum == target) {
            subRes = new ArrayList<>();
            subRes.add(nums[i]);
            subRes.add(nums[j]);
            subRes.add(nums[l]);
            subRes.add(nums[r]);
            res.add(subRes);
            l++;
            while (l < r && nums[l] == nums[l-1]) {
              l++;
            }
            r--;
            while (r > l && nums[r] == nums[r+1]) {
              r--;
            }
          } else if (sum < target) {
            l++;
          } else {
            r--;
          }
        }
      }
    }
    return res;
  }

  /**
   * This is for, Is Four SUM is available?
   * @param nums
   * @param target
   * @return
   */
  public boolean fourSum_isAvailable(int[] nums, int target) {
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
//    Arrays.sort(pairArr, (o1, o2) -> o1[2] - o2[2]);
    Arrays.sort(pairArr, Comparator.comparingInt(o -> o[2]));

    int i = 0;
    int j = pairArr.length - 1;
    int sum;
    while (i < j) {
      sum = pairArr[i][2] + pairArr[j][2];
      if (sum == target && noCommon(pairArr[i], pairArr[j])) {
        /*subRes = new ArrayList<>();
        subRes.add(pairArr[i][0]);
        subRes.add(pairArr[i][1]);
        subRes.add(pairArr[j][0]);
        subRes.add(pairArr[j][1]);
        res.add(subRes);
        i++;
        while (i < j && isItCommon(pairArr[i], pairArr[i-1])) {
          i++;
        }*/
        return true;
      } else if (sum < target) {
        i++;
        while (i < j && isItCommon(pairArr[i], pairArr[i-1])) {
          i++;
        }
      } else {
        j--;
        while (j > i && isItCommon(pairArr[j], pairArr[j+1])) {
          j--;
        }
      }
    }
    return false;
  }

  private boolean isItCommon(int[] first, int[] second) {
    if (first[2] == second[2]) {
      if (first[0] == second[0] || first[0] == second[1]) {
        return true;
      }
    }
    return false;
  }

  private boolean noCommon(int[] pair1, int[] pair2) {
    if (pair1[0] == pair2[0] || pair1[0] == pair2[1]
        || pair1[1] == pair2[0] || pair1[1] == pair2[1]) {
      return false;
    }
    return true;
  }
}
