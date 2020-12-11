package MathAndLogic;

import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/equal/problem
 * <p>
 * Christy is interning at HackerRank. One day she has to distribute some chocolates to her
 * colleagues. She is biased towards her friends and plans to give them more than the others. One of
 * the program managers hears of this and tells her to make sure everyone gets the same number.
 * <p>
 * To make things difficult, she must equalize the number of chocolates in a series of operations.
 * For each operation, she can give  chocolates to all but one colleague. Everyone who gets
 * chocolate in a round receives the same number of pieces.
 * <p>
 * For example, assume the starting distribution is . She can give  bars to the first two and the
 * distribution will be . On the next round, she gives the same two  bars each, and everyone has the
 * same number: .
 * <p>
 * Given a starting distribution, calculate the minimum number of operations needed so that every
 * colleague has the same number of chocolates.
 * <p>
 * Function Description
 * <p>
 * Complete the equal function in the editor below. It should return an integer that reperesents the
 * minimum number of operations required.
 * <p>
 * equal has the following parameter(s):
 * <p>
 * arr: an array of integers to equalize Input Format
 * <p>
 * The first line contains an integer , the number of test cases.
 * <p>
 * Each test case has  lines. - The first line contains an integer , the number of colleagues. - The
 * second line contains  space-separated integers denoting the number of chocolates each colleague
 * has.
 * <p>
 * Constraints
 * <p>
 * <p>
 * <p>
 * Number of initial chocolates each colleague has <
 * <p>
 * Output Format
 * <p>
 * Print the minimum number of operations needed for each test case, one to a line.
 * <p>
 * Sample Input
 * <p>
 * 1 4 2 2 3 7 Sample Output
 * <p>
 * 2 Explanation
 * <p>
 * Start with Add  to all but the 3rd element Add  to all but the 4th element
 * <p>
 * Two operations were required.
 * <p>
 * Sample Input 1
 * <p>
 * 1 3 10 7 12 Sample Output 1
 * <p>
 * 3 Explanation 1
 * <p>
 * Start with Add  to the first two elements Add  to the last two elements Add  to the last two
 * elements
 * <p>
 * Three operations were required.
 */
public class EqualCookies {

  public static long find_min_actions(int[] cookies) {

    Arrays.sort(cookies);

    long sum = Long.MAX_VALUE;

    for (int base = 0; base < 3; base++) {
      int current_sum = 0;
      for (int i = 0; i < cookies.length; i++) {
        int delta = cookies[i] - cookies[0] + base;
        current_sum += (int) delta / 5 + delta % 5 / 2 + delta % 5 % 2 / 1;
      }
      sum = Math.min(current_sum, sum);
    }

    return sum;
  }

  static int equal(int[] arr) {
    int min = arr[0];
    for (int i = 1; i < arr.length; i++) {
      min = Math.min(min, arr[i]);
    }
    Long res = Long.MAX_VALUE;
    for (int base = 0; base < 3; base++) {
      int minOpr = 0;
      for (int curr = 0; curr < arr.length; curr++) {
        int delta = arr[curr] - min + base;
        minOpr += (delta / 5) + (delta % 5 / 2) + (delta % 5 % 2 / 1);
      }
      res = Math.min(res, minOpr);
    }
    return res.intValue();
  }

  public static void main(String[] args) {
    /*Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    while (n-- > 0) {
      int m = in.nextInt();
      int cookies[] = new int[m];
      for (int cookie_i = 0; cookie_i < m; cookie_i++) {
        cookies[cookie_i] = in.nextInt();
      }
      System.out.println(find_min_actions(cookies));
    }
     */
    System.out.println(find_min_actions(new int[]{1, 5, 5}));
    System.out.println(equal(new int[]{1, 5, 5}));
    System.out.println(find_min_actions(new int[]{0, 4, 4}));
  }
}
