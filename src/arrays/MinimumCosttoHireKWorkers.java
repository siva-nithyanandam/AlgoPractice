package arrays;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/3/2021  11:44 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minimum-cost-to-hire-k-workers/
 *
 * There are n workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
 *
 * Now we want to hire exactly k workers to form a paid group.  When hiring a group of k workers, we must pay them according to the following rules:
 *
 * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 * Every worker in the paid group must be paid at least their minimum wage expectation.
 * Return the least amount of money needed to form a paid group satisfying the above conditions.
 *
 *
 *
 * Example 1:
 *
 * Input: quality = [10,20,5], wage = [70,50,30], k = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
 * Example 2:
 *
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
 *
 *
 * Note:
 *
 * 1 <= k <= n <= 10000, where n = quality.length = wage.length
 * 1 <= quality[i] <= 10000
 * 1 <= wage[i] <= 10000
 * Answers within 10-5 of the correct answer will be considered correct.
 */

class Worker_Own implements Comparable<Worker_Own> {
  public int quality, wage;
  public Worker_Own(int q, int w) {
    quality = q;
    wage = w;
  }

  public double ratio() {
    return (double) wage / quality;
  }

  public int compareTo(Worker_Own other) {
    return Double.compare(ratio(), other.ratio());
  }
}

public class MinimumCosttoHireKWorkers {

  public static void main(String[] args) {
    MinimumCosttoHireKWorkers o = new MinimumCosttoHireKWorkers();
    System.out.println(o.mincostToHireWorkers_fastest(new int[]{10,20,5,25}, new int[]{70,50,30,100}, 2));
  }

  class Worker implements Comparable<Worker> {
    int quality;
    int expectedWage;
    double wageQualityRatio;
    Worker(int quality, int expectedWage) {
      this.quality = quality;
      this.expectedWage = expectedWage;
      this.wageQualityRatio = (double) expectedWage / quality;
    }
    public int compareTo(Worker worker) {
      return Double.compare(this.wageQualityRatio, worker.wageQualityRatio);
    }
  }


  public double mincostToHireWorkers_fastest(int[] quality, int[] wage, int k) {
    double minCost = Double.MAX_VALUE;
    Worker[] workers = new Worker[quality.length];
    for(int i = 0; i < workers.length; i++) {
      workers[i] = new Worker(quality[i], wage[i]);
    }
    Arrays.sort(workers);
    Queue<Integer> maxHeap = new PriorityQueue<>(k,(a,b)->(b-a));
    int sumHeap = 0;
    int i = 0;
    for(i = 0; i < k; i++) {
      maxHeap.add(workers[i].quality);
      sumHeap += workers[i].quality;
    }
    double captainRatio = workers[i - 1].wageQualityRatio;
    minCost = Math.min(minCost, sumHeap * captainRatio);
    for(int captain = i; captain < workers.length; captain++) {
      captainRatio = workers[captain].wageQualityRatio;
      if(maxHeap.peek() > workers[captain].quality) {
        sumHeap -= maxHeap.poll();
        maxHeap.add(workers[captain].quality);
        sumHeap += workers[captain].quality;
      }
      minCost = Math.min(minCost, sumHeap * captainRatio);
    }
    return minCost;
  }

  public double mincostToHireWorkers_faster(int[] quality, int[] wage, int K) {
    int N = quality.length;
    Worker_Own[] workers = new Worker_Own[N];
    for (int i = 0; i < N; ++i)
      workers[i] = new Worker_Own(quality[i], wage[i]);
    Arrays.sort(workers);

    double ans = 1e9;
    int sumq = 0;
    PriorityQueue<Integer> pool = new PriorityQueue();
    //Queue<Integer> pool = new LinkedList<>();
    for (Worker_Own worker: workers) {
      pool.offer(-worker.quality);
      sumq += worker.quality;
      if (pool.size() > K)
        sumq += pool.poll();
      if (pool.size() == K)
        ans = Math.min(ans, sumq * worker.ratio());
    }

    return ans;
  }

  public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
    int N = quality.length;
    double ans = 1e9;

    for (int captain = 0; captain < N; ++captain) {
      // Must pay at least wage[captain] / quality[captain] per qual
      double factor = (double) wage[captain] / quality[captain];
      double prices[] = new double[N];
      int t = 0;
      for (int worker = 0; worker < N; ++worker) {
        double price = factor * quality[worker];
        if (price < wage[worker]) continue;
        prices[t++] = price;
      }

      if (t < K) continue;
      Arrays.sort(prices, 0, t);
      double cand = 0;
      for (int i = 0; i < K; ++i)
        cand += prices[i];
      ans = Math.min(ans, cand);
    }

    return ans;
  }
}
