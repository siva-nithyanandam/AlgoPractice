package MathAndLogic;

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
 */
public class EggDropProblem {

  public static void main(String[] args) {
    int f = 5;
    int e = 2;
    System.out.println(eggDrop(f, e));
    //Math.ceil((-1.0 + Math.sqrt(1 + 8 * f)) / 2.0);
    System.out.println(eggDropInMemory(e, f));

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

  private static int eggDrop(int totalFloors, int eggs) {
    if (totalFloors <= 1) {
      return totalFloors;
    } else if (eggs == 1) {
      return totalFloors;
    }

    int min = Integer.MAX_VALUE;
    int res;

    for (int floor = 1; floor <= totalFloors; floor++) {
      int notBreaked = eggDrop(totalFloors-floor, eggs);
      int breaked = eggDrop(floor-1, eggs-1);
      res = Math.max(notBreaked, breaked);
      if (res < min) {
        min = res;
      }
    }
    return min + 1;
  }
}
