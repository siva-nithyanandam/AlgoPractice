package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1: Input:
 * [[1,3],[2,6],[8,10],[15,18]] Output: [[1,6],[8,10],[15,18]] Explanation: Since intervals [1,3]
 * and [2,6] overlaps, merge them into [1,6].
 *
 * Example 2: Input: [[1,4],[4,5]] Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */

/**
 * There are 2 solutions given below.
 * #1 - O(nlogn) - Sort the given intervals based on open(start) and move the downstream elements before if it
 * crosses previous one.
 *
 * #2 - O(n) - Find max and min elements from overall. Create integer arrays "openArr" and "closeArr"
 * of size (max - min + 1)
 * Increment wherever open and close are happening. Then if openCount and closeCount matches at
 * certain point, that is a
 * merging point. Simple but coding is medium difficult.
 */
public class MergeIntervals {

  public static void main(String[] args) {
    int[][] intervals;
    int[][] res;

    intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    res = merge(intervals);
    for (int i = 0; i < res.length; i++) {
      System.out.print(res[i][0] + ", " + res[i][1]);
      System.out.println("");
    }

    System.out.println("");

    intervals = new int[][]{{1, 4}, {4, 5}};
    res = merge(intervals);
    for (int i = 0; i < res.length; i++) {
      System.out.print(res[i][0] + ", " + res[i][1]);
      System.out.println("");
    }

    System.out.println("");

    intervals = new int[][]{{1, 4}, {0, 0}};
    res = merge(intervals);
    for (int i = 0; i < res.length; i++) {
      System.out.print(res[i][0] + ", " + res[i][1]);
      System.out.println("");
    }

    System.out.println("");

    intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    res = mergeFromInternetModified(intervals);
    for (int i = 0; i < res.length; i++) {
      System.out.print(res[i][0] + ", " + res[i][1]);
      System.out.println("");
    }
  }

  public static int[][] merge(int[][] intervals) {

    if (intervals.length == 0) {
      return intervals;
    }

    Arrays.sort(intervals, new Comparator<int[]>() {
      public int compare(int[] i1, int[] i2) {
        return i1[0] - i2[0];
      }
    });

    int pointer = 0;
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] <= intervals[pointer][1]) {
        intervals[pointer][0] = Math.min(intervals[pointer][0], intervals[i][0]);
        intervals[pointer][1] = Math.max(intervals[pointer][1], intervals[i][1]);
      } else {
        pointer++;
        intervals[pointer][0] = intervals[i][0];
        intervals[pointer][1] = intervals[i][1];
      }
    }
    return Arrays.copyOf(intervals, pointer + 1);
  }

  public static int[][] mergeFromInternetModified(int[][] intervals) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    for (int i = 0; i < intervals.length; i++) {
      min = Math.min(intervals[i][0], min);
      max = Math.max(intervals[i][1], max);
    }

    int[] openArr = new int[max - min + 1];
    int[] closeArr = new int[max - min + 1];

    for (int i = 0; i < intervals.length; i++) {
      openArr[intervals[i][0] - min]++;
      closeArr[intervals[i][1] - min]++;
    }

    List<int[]> res = new ArrayList<>();
    int openCount = 0, closeCount = 0, mergedOpen = 0;

    for (int i = 0; i < openArr.length; i++) {

      if (openCount == 0) {
        mergedOpen = i;
      }
      openCount += openArr[i];
      closeCount += closeArr[i];

      if (openCount == closeCount && openCount > 0) {
        res.add(new int[]{mergedOpen+min, i+min});
        openCount = 0;
        closeCount = 0;
      }
    }

    int[][] resArr = new int[res.size()][2];
    for(int i = 0; i < res.size(); i++) {
      resArr[i][0] = res.get(i)[0];
      resArr[i][1] = res.get(i)[1];
    }
    return resArr;
  }

}
