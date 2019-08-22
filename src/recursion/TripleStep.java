package recursion;

/**
 * A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3
 * steps at a time. Implement a method to count how many possible ways the child can run up the
 * stairs.
 */

/**
 * https://www.geeksforgeeks.org/count-ways-reach-nth-stair-using-step-1-2-3/
 * http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/fibpuzzles.html
 * https://www.geeksforgeeks.org/program-for-nth-fibonacci-number/
 */
public class TripleStep {
  static int countWaysUtil(int n, int m)
  {
    if (n <= 1)
      return n;
    int res = 0;
    for (int i = 1; i<=n; i++)
      res += countWaysUtil(n-i, m);
    return res;
  }

  // Returns number of ways to reach s'th stair
  static int countWays(int s, int m)
  {
    return countWaysUtil(s+1, m);
  }


  /* Driver program to test above function */
  public static void main (String args[])
  {
    int s = 3,m = 2;
    System.out.println("Number of ways = "+ countWays(s,m));
  }
}
