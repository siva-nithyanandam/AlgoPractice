package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Author - Sivaprakash Nithyanandam Timestamp - 5/21/2022  11:27 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */


public class Test {

  public static void main(String[] args) {
    Test o = new Test();
    System.out.println(Integer.toBinaryString(59));
  }

  public int sqrt(int x) {
    if (x < 2) {
      return x;
    }
    int left = sqrt(x >> 2) << 1;
    int right = left + 1;
    return right * right > x ? left : right;
  }

  public class Point implements Comparable<Point> {

    private int x;
    private int y;
    private char tag;

    public Point (int x, int y, char tag) {
      this.x = x;
      this.y = y;
      this.tag = tag;
    }

    public double findDist(Point p) {
      return Math.sqrt(p.x * p.x + p.y * p.y);
    }

    public int compareTo(Point other) {
      return Double.compare(findDist(this), findDist(other));
    }
  }

  public int maxPointsInsideSquare(int[][] points, String s) {

    Point[] arr = new Point[s.length()];
    for (int i = 0; i < s.length(); i++) {
      arr[i] = new Point(points[i][0], points[i][1], s.charAt(i));
    }

    Arrays.sort(arr);

    Set<Character> seenTags = new HashSet<>();
    int ans = 0;

    for (Point point : arr) {
      if (seenTags.contains(point.tag)) {
        return ans;
      }
      seenTags.add(point.tag);
      ans++;
    }
    return ans;
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
