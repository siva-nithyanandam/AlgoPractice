package StacksAndQueues;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/20/2021  7:31 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;

/**
 *
 */
public class TaskScheduler {

  public static void main(String[] args) {
    TaskScheduler o = new TaskScheduler();
    System.out.println(o.leastInterval(new char[]{'A','A','A','B','B','B'}, 2));
  }

  public int leastInterval(char[] tasks, int n) {
    // frequencies of the tasks
    int[] frequencies = new int[26];
    for (int t : tasks) {
      frequencies[t - 'A']++;
    }

    Arrays.sort(frequencies);

    // max frequency
    int f_max = frequencies[25];
    int idle_time = (f_max - 1) * n;

    for (int i = frequencies.length - 2; i >= 0 && idle_time > 0; --i) {
      idle_time -= Math.min(f_max-1, frequencies[i]);
    }
    idle_time = Math.max(0, idle_time);

    return idle_time + tasks.length;
  }
}
