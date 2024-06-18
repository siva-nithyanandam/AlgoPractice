package test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Author - Sivaprakash Nithyanandam Timestamp - 5/21/2022  11:27 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */


public class Test {

  public static void main(String[] args) {
    Test o = new Test();
    System.out.println(nearestPowerOf2(5));
    System.out.println(o.findMaximizedCapital(2, 0, new int[]{1,2,3}, new int[]{0,1,1}));
  }

  public static long nearestPowerOf2(long N) {

    double x = Math.log(N);
    double y = Math.log(2);

    long a = (int)(x / y);

    if (Math.pow(2, a) == N)
      return N;

    return (long) Math.pow(2, a + 1);
  }

  public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

    int[][] mem = new int[1][(int)1e9];
    return helper(k, w, profits, capital, 0, w);
  }

  private int helper(int k, int w, int[] profits, int[] capital, int i, int sum) {

    if (i == profits.length || k == 0) {
      return sum;
    }

    //No skip
    int nSkip = 0;
    if (w >= capital[i]) {
      nSkip = helper(k-1, w-capital[i]+profits[i], profits, capital, i+1, sum+profits[i]);
    }

    //skip
    int skip = helper(k, w, profits, capital, i+1, sum);

    return Math.max(skip, nSkip);
  }


}
