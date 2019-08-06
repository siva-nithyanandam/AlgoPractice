package MathAndLogic;

/**
 * Get all the prime numbers until a given number.
 */

/**
 * Sieve of Eratosthenes
 */
public class ListOfPrimesNew {

  public static void main(String[] args) {
    int[] arr = getPrimesUntilGiven(100);
    System.out.print(2 + ",");
    for(int i = 3; i < arr.length; i+=2) {
      if (arr[i] == 0) {
        System.out.print(i + ",");
      }
    }
  }

  private static int[] getPrimesUntilGiven(int max) {
    int[] flags = new int[max + 1];
    flags[0] = 1;
    flags[1] = 1;
    int prime = 3;
    while(prime < Math.sqrt(max)) {
      crossOff(flags, prime);
      prime = getNextPrime(flags, prime);
    }
    return flags;
  }

  private static int getNextPrime(int[] flags, int prime) {
    int next = prime + 2;
    while(flags[next] == 1) {
      next += 2;
    }
    return next;
  }

  private static void crossOff(int[] flags, int prime) {
    int j = prime;
    int i = prime * j;
    while(i < flags.length) {
      flags[i] = 1;
      j += 2;
      i = prime * j;
    }
  }
}
