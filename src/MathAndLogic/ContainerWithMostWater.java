package MathAndLogic;

/**
 *
 * https://leetcode.com/problems/container-with-most-water/
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
 *
 * Notice that you may not slant the container.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 * Example 2:
 *
 * Input: height = [1,1]
 * Output: 1
 * Example 3:
 *
 * Input: height = [4,3,2,1,4]
 * Output: 16
 * Example 4:
 *
 * Input: height = [1,2,1]
 * Output: 2
 *
 *
 * Constraints:
 *
 * n = height.length
 * 2 <= n <= 3 * 104
 * 0 <= height[i] <= 3 * 104
 */
public class ContainerWithMostWater {
  public static void main(String[] args) {
    ContainerWithMostWater o = new ContainerWithMostWater();
    System.out.println(o.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    System.out.println(o.maxArea(new int[]{1,1}));
    System.out.println(o.maxArea(new int[]{4,3,2,1,4}));
    System.out.println(o.maxArea(new int[]{1,2,1}));
  }

  public int maxArea_fastest(int[] height) {

    int start = 0, end = height.length-1;
    int res = 0;
    int h;

    while (start < end) {
      h = Math.min(height[start], height[end]);
      res = Math.max(res, h * (end-start));

      while(start < end && height[start] <= h) {
        start++;
      }
      while(start < end && height[end] <= h) {
        end--;
      }
    }
    return res;
  }

  public int maxArea(int[] height) {
    int res = 0;
    int left = 0, right = height.length-1;
    while (left < right) {
      if (height[left] < height[right]) {
        res = Math.max(res, height[left] * (right-left));
        left++;
      } else {
        res = Math.max(res, height[right] * (right-left));
        right--;
      }
    }
    return res;
  }

  public int maxArea_n2(int[] height) {
    int res = 0;
    for (int i = 0; i < height.length-1; i++) {
      for (int j = i+1; j < height.length; j++) {
        res = Math.max(res, Math.min(height[i], height[j]) * (j-i));
      }
    }
    return res;
  }
}
