package euler;
/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.List;

/**
 * https://projecteuler.net/problem=758
 *
 *
 */
public class BucketsOfWater {

  public static void main(String[] args) {
    BucketsOfWater o = new BucketsOfWater();
    int upto = 1000;
    List<Integer> primes = o.getPrimes(upto);
    for (int i = 0; i < primes.size(); i++) {
      for (int j = i+1; j < primes.size(); j++) {

      }
    }
    int minPourings = getMinPouringForOneLitre(3, 5);
  }

  private static int getMinPouringForOneLitre(int i, int j) {

    return 0;
  }

  private List<Integer> getPrimes(int upto) {
    int[] flags = new int[upto+1];
    List<Integer> res = new ArrayList<>();

    flags[0] = 1;
    flags[1] = 1;
    for (int i = 2; i <= Math.sqrt(upto); i++) {
      if (flags[i] == 0) {
        res.add(i);
        markRelativesAsNotPrime(i, upto, flags);
      }
    }
    return res;
  }

  private void markRelativesAsNotPrime(int prime, int upto, int[] flags) {
    for (int i = prime * prime; i <= upto; i += prime) {
      flags[i] = 1;
    }
  }
}
