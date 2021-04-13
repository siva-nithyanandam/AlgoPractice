package arrays;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianofTwoSortedArrays {

  public static void main(String[] args) {
    MedianofTwoSortedArrays o = new MedianofTwoSortedArrays();

    System.out.println(o.findMedianSortedArrays(new int[]{1}, new int[]{1}));
    System.out.println(o.findMedianSortedArrays(new int[]{1}, new int[]{2,3,4,5,6,7,8,9,10}));
    System.out.println(o.findMedianSortedArrays(new int[]{1,3,7,8}, new int[]{2,4,5,6}));
    System.out.println(o.findMedianSortedArrays(new int[]{1,2,6,7}, new int[]{3,4,5,8}));
    System.out.println(o.findMedianSortedArrays(new int[]{1,2,3,5}, new int[]{4,6,7,8}));
    System.out.println(o.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
    System.out.println(o.findMedianSortedArrays(new int[]{1,2,3,4,5}, new int[]{6,7,8,9,10}));
    System.out.println(o.findMedianSortedArrays(new int[]{1,2,3,4,5,6}, new int[]{6,7,8,9,10,11}));
    System.out.println(o.findMedianSortedArrays(new int[]{1,2,3,4,5,6}, new int[]{7,8,9,10,11,12}));
    System.out.println(o.findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
  }

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length == nums2.length) {
      return findMedianSortedArraysForSameSize(nums1, nums2, 0, nums1.length-1, 0, nums2.length-1);
    } else {
      return findMedianSortedArraysForDiffSize(nums1, nums2);
    }
  }

  private double findMedianSortedArraysForDiffSize(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
      return findMedianSortedArraysForDiffSize(nums2, nums1);
    }
    int low = 0;
    int high = nums1.length;
    int totalLen = nums1.length + nums2.length;
    while (low <= high) {
      int partitionX = (low + high)/2;
      int partitionY = (totalLen+1)/2 - partitionX;

      int topLeft = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX - 1];
      int topRight = partitionX == nums1.length ? Integer.MAX_VALUE : nums1[partitionX];

      int bottomLeft = partitionY == 0 ? Integer.MIN_VALUE : nums2[partitionY - 1];
      int bottomRight = partitionY == nums2.length ? Integer.MAX_VALUE : nums2[partitionY];

      if (topLeft <= bottomRight && bottomLeft <= topRight) {
        if ((totalLen & 1) == 1) {
          return Math.max(topLeft, bottomLeft);
        } else {
          return (double)(Math.max(topLeft, bottomLeft) + Math.min(topRight, bottomRight))/2;
        }
      } else if (topLeft > bottomRight) {
        high = partitionX - 1;
      } else {
        low = partitionX + 1;
      }
    }
    return -1;
  }
























  private double findMedianSortedArraysForSameSize(int[] nums1, int[] nums2, int topStart, int topEnd,
      int bottomStart, int bottomEnd) {

    int diff = topEnd - topStart;
    if (diff == 0) {
      return (double)(nums1[topStart] + nums2[bottomStart])/2;
    } else if (diff == 1) {
      return (double) (Math.max(nums1[topStart], nums2[bottomStart]) + Math.min(nums1[topEnd], nums2[bottomEnd]))/2;
    }

    double m1 = getMedian(nums1, topStart, topEnd);
    double m2 = getMedian(nums2, bottomStart, bottomEnd);

    if (m1 == m2) {
      return m1;
    } else {
      int carry = 0;
      if ((diff & 1) == 1) {
        carry = 1;
      }
      if (m1 < m2) {
        return findMedianSortedArraysForSameSize(nums1, nums2, (topStart+topEnd)/2, topEnd, bottomStart, (bottomStart+bottomEnd)/2 + carry);
      } else {
        return findMedianSortedArraysForSameSize(nums1, nums2, topStart, (topStart+topEnd)/2+carry, (bottomStart+bottomEnd)/2, bottomEnd);
      }
    }
  }

  private double getMedian(int[] nums1, int start, int end) {
    int total = start + end;
    if ((total & 1) == 0) {
      return nums1[total/2];
    } else {
      return (double)(nums1[total/2] + nums1[(total+1)/2])/2;
    }
  }
}
