package test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Arrays;
import java.util.function.Function;

/**
 * Author - Sivaprakash Nithyanandam Timestamp - 5/21/2022  11:27 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */


public class Test {

  public static void main(String[] args) {
    Test o = new Test();
    Function<Integer, Integer> square = num -> num * num;

    System.out.println(square.apply(3));
    Integer i1=127;
    Integer i2=127;
    Integer i3=200;
    Integer i4=200;

    System.out.println(i1 == i2);
    System.out.println(i3 == i4);
  }

  public int compareVersion(String version1, String version2) {
    String[] v1 = version1.split("\\.");
    String[] v2 = version2.split("\\.");

    int n1 = v1.length;
    int n2 = v2.length;

    int i1 = 0;
    int i2 = 0;

    while (i1 < n1 && i2 < n2) {
      int int1 = Integer.parseInt(v1[i1]);
      int int2 = Integer.parseInt(v2[i2]);

      if (int1 == int2) {
        i1++;
        i2++;
      } else {
        return int1 > int2 ? 1 : -1;
      }
    }

    if (i1 == n1 && i2 == n2) {
      return 0;
    } else if (i1 == n1) {
      return Integer.parseInt(v2[i2]) == 0 ? 0 : -1;
    } else {
      return Integer.parseInt(v1[i1]) == 0 ? 0 : 1;
    }
  }

  public int search(int[] nums, int target) {
    int pivot = findStart(nums);
    if (pivot == -1) {
      return -1;
    }
    if (nums[0] <= target) {
      return binarySearch(0, pivot-1, nums, target);
    } else {
      return binarySearch(pivot, nums.length-1, nums, target);
    }
  }

  private int binarySearch(int l, int r, int[] nums, int target) {

    while (l <= r) {
      int m = l + (r-l)/2;
      if (nums[m] == target) {
        return m;
      } else if (nums[m] > target) {
        r = m-1;
      } else {
        l = m+1;
      }
    }
    return -1;
  }

  private int findStart(int[] nums) {
    if (nums.length == 1) {
      return 0;
    } else if (nums[0] < nums[nums.length-1]) {
      return 0;
    }
    int l = 0;
    int r = nums.length-1;


    while (l <= r) {
      int m = l + (r-l)/2;

      if (m > 0 && nums[m] < nums[m-1]) {
        return m;
      } else if (nums[m] >= nums[0]) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return -1;
  }




}
