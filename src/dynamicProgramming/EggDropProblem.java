package dynamicProgramming;

/**
 * There is a building of 100 floors. If an egg drops from the Nth floor or
 * above, it will break. If it's dropped from any floor below, it will not break.
 * You're given two eggs. Find N, while minimizing the number of drops for the worst case.
 */

/**
 * References:
 * https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/
 * http://www.cs.umd.edu/~gasarch/BLOGPAPERS/eggold.pdf
 * https://www.geeksforgeeks.org/egg-dropping-puzzle-with-2-eggs-and-k-floors/
 * https://www.geeksforgeeks.org/eggs-dropping-puzzle-binomial-coefficient-and-binary-search-solution/
 */
public class EggDropProblem {

  public static void main(String[] args) {
    int f = 8;
    int e = 3;
    //Math.ceil((-1.0 + Math.sqrt(1 + 8 * f)) / 2.0);
    System.out.println(eggDropInMemory(e, f));
    System.out.println(eggDrop(e, f));

  }

  //TODO Learn this equation
  //https://brilliant.org/wiki/egg-dropping/
  public static int superEggDropFastest(int eggs, int floors) {
    int lo = 1, hi = floors;
    while (lo < hi) {
      int mi = (lo + hi) / 2;
      if (f(mi, eggs, floors) < floors) {
        lo = mi + 1;
      } else {
        hi = mi;
      }
    }

    return lo;
  }

  public static int f(int mid, int eggs, int floors) {
    int ans = 0, r = 1;
    for (int i = 1; i <= eggs; ++i) {
      r *= mid - i + 1;
      r /= i;
      ans += r;
      if (ans >= floors) {
        break;
      }
    }
    return ans;
  }

  //https://leetcode.com/problems/super-egg-drop/discuss/158974/C%2B%2BJavaPython-2D-and-1D-DP-O(KlogN)
  public static int superEggDrop1(int eggs, int floors) {
    int[][] dp = new int[floors+1][eggs+1];

    int m = 0;
    while(dp[m][eggs] < floors) {
      m++;
      for (int e = 1; e <= eggs; e++) {
        dp[m][e] = dp[m-1][e-1] + 1 + dp[m-1][e];
      }
    }
    return m;
  }

  private static int eggDropInMemory(int n, int k) {
    int[][] eggFloor = new int[n + 1][k + 1];

    for (int i = 1; i <= n; i++) {
      eggFloor[i][1] = 1;
      eggFloor[i][0] = 0;
    }
    for (int i = 1; i <= k; i++) {
      eggFloor[1][i] = i;
    }

    int res;
    for(int i = 2; i <= n; i++) {
      for (int j = 2; j <= k; j++) {
        eggFloor[i][j] = Integer.MAX_VALUE;
        for(int x = 1; x <= j; x++) {
          res = 1 + Math.max(eggFloor[i-1][x-1], eggFloor[i][j-x]);
          if (res < eggFloor[i][j]) {
            eggFloor[i][j] = res;
          }
        }
      }
    }
    return eggFloor[n][k];
  }

  private static int eggDrop(int eggs, int totalFloors) {
    if (totalFloors <= 1) {
      return totalFloors;
    } else if (eggs == 1) {
      return totalFloors;
    }

    int min = Integer.MAX_VALUE;
    int res;

    for (int floor = 1; floor <= totalFloors; floor++) {
      int notBreaked = eggDrop(eggs, totalFloors-floor);
      int breaked = eggDrop(eggs-1, floor-1);
      res = Math.max(notBreaked, breaked);
      if (res < min) {
        min = res;
      }
    }
    return min + 1;
  }

  private static int superEggDrop(int eggs, int floors) {
    int[][] mem = new int[eggs+1][floors+1];
    for (int i = 1; i <= floors; i++) {
      mem[1][i] = i;
    }
    return superEggDrop(eggs, floors, mem);
  }
  private static int superEggDrop(int eggs, int floors, int[][] mem) {
    if (floors <= 1) {
      return floors;
    }
    if (mem[eggs][floors] > 0) {
      return mem[eggs][floors];
    }
    int low = 1;
    int high = floors;
    int res = floors;
    while (low < high) {
      int mid = low + (high - low)/2;
      int breaked = superEggDrop(eggs-1, mid-1, mem);
      int notBreaked = superEggDrop(eggs, floors-mid, mem);
      res = Math.min(res, 1 + Math.max(breaked, notBreaked));
      if (breaked > notBreaked) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    mem[eggs][floors] = res;
    return res;
  }


}
