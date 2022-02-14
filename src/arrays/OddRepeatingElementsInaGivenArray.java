package arrays;

/**
 * https://www.geeksforgeeks.org/find-the-two-numbers-with-odd-occurences-in-an-unsorted-array/
 *
 * Find the two numbers with odd occurrences in an unsorted array
 */
public class OddRepeatingElementsInaGivenArray {

  public static void main(String[] args) {
    OddRepeatingElementsInaGivenArray o = new OddRepeatingElementsInaGivenArray();
    System.out.println(System.currentTimeMillis());
    o.identifyNonDuplicates(new int[]{1,2,3,3,4,4});
    o.identifyNonDuplicates(new int[]{12, 23, 34, 12, 12, 23, 12, 45});
    System.out.println(System.currentTimeMillis());
    o.identifyNonDuplicates(new int[]{4, 4, 100, 5000, 4, 4, 4, 4, 100, 100});
    System.out.println(System.currentTimeMillis());
    o.identifyNonDuplicates(new int[]{10, 20});
    System.out.println(System.currentTimeMillis());
  }

  private void identifyNonDuplicates(int[] arr) {
    int xor = 0;
    for (int i : arr) {
      xor ^= i; //x ^ y
    }
    int lastBit = xor & (~(xor-1));
    int x = 0, y = 0;
    for (int i : arr) {
      if ((lastBit & i) != 0) {
        x = x ^ i;
      } else {
        y = y ^ i;
      }
    }
    System.out.println(x + " - " + y);
  }


}
