package MathAndLogic;

/**
 * Get all the prime numbers until a given number.
 */

/**
 * Sieve of Eratosthenes
 */
public class ListOfPrimes {

  public static void main(String[] args) {
    int[] arr = getPrimesUntilGiven(1000);
    for(int i = 0; i < arr.length; i++) {
      if (arr[i] == 0) {
        System.out.print(i + ",");
      }
    }
  }

  private static int[] getPrimesUntilGiven(int max) {
    int[] flags = new int[max + 1];
    flags[0] = 1;
    flags[1] = 1;
    int prime = 2;
    while(prime < Math.sqrt(max)) {
      crossOff(flags, prime);
      prime = getNextPrime(flags, prime);
    }
    return flags;
  }

  private static int getNextPrime(int[] flags, int prime) {
    int next = prime + 1;
    while(flags[next] == 1) {
      next++;
    }
    return next;
  }

  private static void crossOff(int[] flags, int prime) {
    for(int i = prime * prime; i < flags.length; i += prime*2) {
      flags[i] = 1;
    }
  }
}
