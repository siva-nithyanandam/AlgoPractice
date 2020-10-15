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

  public static void main (String args[]) {
    int totalSteps = 6,maxHops = 4;
    System.out.println("Number of ways = "+ countWays(totalSteps,maxHops));
  }

  private static int countWays(int totalSteps, int maxHops) {

    int[] nums = new int[totalSteps+1];
    nums[0] = 1;
    nums[1] = 1;
    for(int i = 2; i < maxHops; i++) {
      nums[i] = nums[i-1] + nums[i-2];
    }
    for (int i = maxHops; i <= totalSteps; i++) {
      for(int j = 1; j <= maxHops; j++) {
        nums[i] += nums[i-j];
      }
    }
    return nums[totalSteps];
  }
}
