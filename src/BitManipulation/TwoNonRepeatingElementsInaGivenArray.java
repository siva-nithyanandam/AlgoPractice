package BitManipulation;

/**
 * https://www.geeksforgeeks.org/find-two-non-repeating-elements-in-an-array-of-repeating-elements/
 *
 * Find the two non-repeating elements in an array of repeating elements/ Unique Numbers 2
 */
public class TwoNonRepeatingElementsInaGivenArray {

  public static void main(String[] args) {
    TwoNonRepeatingElementsInaGivenArray o = new TwoNonRepeatingElementsInaGivenArray();
    System.out.println(System.currentTimeMillis());
    o.identifyNonDuplicates(new int[]{2, 3, 7, 9, 11, 2, 3, 11}, 8);
    System.out.println(System.currentTimeMillis());
  }

  private void identifyNonDuplicates(int[] arr, int n) {
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
