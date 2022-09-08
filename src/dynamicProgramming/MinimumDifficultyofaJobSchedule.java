package dynamicProgramming;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/22/2022  9:14 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
 *
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
 *
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
 *
 * You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
 *
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7
 * Example 2:
 *
 * Input: jobDifficulty = [9,9,9], d = 4
 * Output: -1
 * Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
 * Example 3:
 *
 * Input: jobDifficulty = [1,1,1], d = 3
 * Output: 3
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 *
 *
 * Constraints:
 *
 * 1 <= jobDifficulty.length <= 300
 * 0 <= jobDifficulty[i] <= 1000
 * 1 <= d <= 10
 */
public class MinimumDifficultyofaJobSchedule {

  public static void main(String[] args) {
    MinimumDifficultyofaJobSchedule o = new MinimumDifficultyofaJobSchedule();
    System.out.println(o.minDifficulty(new int[]{11,111,22,222,33,333,44,444}, 6));
    System.out.println(o.minDifficulty(new int[]{6,5,4,3,2,1}, 2));
  }

  private int n, d;
  private int[][] memo;
  private int[] jobDifficulty;
  private int[] hardestJobRemaining;

  private int dp(int i, int day) {
    // Base case, it's the last day so we need to finish all the jobs
    if (day == d) {
      return hardestJobRemaining[i];
    }

    if (memo[i][day] == -1) {
      int best = Integer.MAX_VALUE;
      int hardest = 0;
      // Iterate through the options and choose the best
      for (int j = i; j < n - (d - day); j++) {
        hardest = Math.max(hardest, jobDifficulty[j]);
        // Recurrence relation
        best = Math.min(best, hardest + dp(j + 1, day + 1));
      }
      memo[i][day] = best;
    }

    return memo[i][day];
  }

  public int minDifficulty(int[] jobDifficulty, int d) {
    n = jobDifficulty.length;
    // If we cannot schedule at least one job per day,
    // it is impossible to create a schedule
    if (n < d) {
      return -1;
    }

    hardestJobRemaining = new int[n];
    int hardestJob = 0;
    for (int i = n - 1; i >= 0; i--) {
      hardestJob = Math.max(hardestJob, jobDifficulty[i]);
      hardestJobRemaining[i] = hardestJob;
    }

    // Initialize memo array with value of -1.
    memo = new int[n][d + 1];
    for (int i = 0; i < n; i++) {
      Arrays.fill(memo[i], -1);
    }

    this.d = d;
    this.jobDifficulty = jobDifficulty;
    return dp(0, 1);
  }
}
