package MathAndLogic;

import java.io.*;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/two-robots/problem
 *
 * You have a warehouse with  containers filled with an infinite number of candies. The containers are arranged in a single row, equally spaced to be  meter apart. You also have  robots that can pick up  piece of candy and transport it between any two containers.
 *
 * The robots take instructions in the form of queries consisting of two integers,  and , respectively. To execute a query, a robot travels to container , picks up  candy, transports it to container , and then stops at  until it receives another query.
 *
 * Calculate the minimum total distance the robots must travel to execute  queries in order.
 *
 * Note: You choose which robot executes each query.
 *
 * Input Format
 *
 * The first line contains a single integer,  (the number of test cases); each of the  test cases is described over  lines.
 *
 * The first line of a test case has two space-separated integers,  (the number of containers) and  (the number of queries).
 * The  subsequent lines each contain two space-separated integers,  and , respectively; each line  describes the  query.
 *
 * Constraints
 *
 * Output Format
 *
 * On a new line for each test case, print an integer denoting the minimum total distance that the robots must travel to execute the queries in order.
 *
 * Sample Input
 *
 * 3
 * 5 4
 * 1 5
 * 3 2
 * 4 1
 * 2 4
 * 4 2
 * 1 2
 * 4 3
 * 10 3
 * 2 4
 * 5 4
 * 9 8
 * Sample Output
 *
 * 11
 * 2
 * 5
 * Explanation
 *
 * In this explanation, we refer to the two robots as  and , each container  as , and the total distance traveled for each query  as .
 *
 * Note: For the first query a robot executes, there is no travel distance. For each subsequent query that robot executes, it must travel from the location where it completed its last query.
 *
 * Test Case 0:
 * The minimum distance traveled is :
 *
 * Robot:
 *
 *  meters.
 * Robot:
 *
 *  meter.
 * Robot:
 *
 *  meters.
 * Robot:
 *
 *  meters.
 * Sum the distances traveled () and print the result on a new line.
 *
 * Test Case 1:
 *
 * Robot:
 *
 *  meters.
 * Robot:
 *
 *  meters.
 * Sum the distances traveled () and print the result on a new line.
 *
 * Test Case 2:
 *
 * Robot:
 *
 *  meters.
 * Robot:
 *
 *  meters.
 * Robot:
 *
 *  meters.
 * Sum the distances traveled () and print the result on a new line.
 */

public class TwoRobots {

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter w = new PrintWriter(System.out);

    int t = in.nextInt();

    while(t-- > 0){
      int m = in.nextInt();
      int n = in.nextInt();

      int a[] = new int[n];
      int b[] = new int[n];

      for(int i = 0; i < n; i++){
        a[i] = in.nextInt() - 1;
        b[i] = in.nextInt() - 1;
      }

      int dp[][] = new int[n + 1][m + 1];

      for(int i = n - 1; i >= 1; i--){
        for(int j = 0; j < m; j++){
          dp[i][j] = dp[i + 1][b[i - 1]] + Math.abs(a[i] - j) + Math.abs(b[i] - a[i]);
          dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + Math.abs(b[i - 1] - a[i])  + Math.abs(b[i] - a[i]));
        }
      }

      int ans = Integer.MAX_VALUE;
      for(int j = 0; j < m; j++)
        ans = Math.min(ans, dp[1][j] + Math.abs(b[0] - a[0]));

      w.println(ans);
    }

    w.close();
  }

  static class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[8192];
    private int curChar, snumChars;
    private SpaceCharFilter filter	;

    public InputReader(InputStream stream) {
      this.stream = stream;
    }

    public int snext() {
      if (snumChars == -1)
        throw new InputMismatchException();
      if (curChar >= snumChars) {
        curChar = 0;
        try {
          snumChars = stream.read(buf);
        } catch (IOException e) {
          throw new InputMismatchException();
        }
        if (snumChars <= 0)
          return -1;
      }
      return buf[curChar++];
    }

    public int nextInt() {
      int c = snext();
      while (isSpaceChar(c))
        c = snext();
      int sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = snext();
      }
      int res = 0;
      do {
        if (c < '0' || c > '9')
          throw new InputMismatchException();
        res *= 10;
        res += c - '0';
        c = snext();
      } while (!isSpaceChar(c));
      return res * sgn;
    }

    public long nextLong() {
      int c = snext();
      while (isSpaceChar(c))
        c = snext();
      int sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = snext();
      }
      long res = 0;
      do {
        if (c < '0' || c > '9')
          throw new InputMismatchException();
        res *= 10;
        res += c - '0';
        c = snext();
      } while (!isSpaceChar(c));
      return res * sgn;
    }

    public int[] nextIntArray(int n) {
      int a[] = new int[n];
      for (int i = 0; i < n; i++)
        a[i] = nextInt();
      return a;
    }

    public String readString() {
      int c = snext();
      while (isSpaceChar(c))
        c = snext();
      StringBuilder res = new StringBuilder();
      do {
        res.appendCodePoint(c);
        c = snext();
      } while (!isSpaceChar(c));
      return res.toString();
    }

    public boolean isSpaceChar(int c) {
      if (filter != null)
        return filter.isSpaceChar(c);
      return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public interface SpaceCharFilter {
      public boolean isSpaceChar(int ch);
    }
  }
}
