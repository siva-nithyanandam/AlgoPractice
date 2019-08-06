package BitManipulation;

/**
 * Given a positive integer, print the next smallest and the next largest number that
 * have the same number of 1 bits in their binary representation.
 */

/**
 * Visit - https://www.geeksforgeeks.org/next-higher-number-with-same-number-of-set-bits/
 */
public class NextNumber {

  public static void main(String[] args) {
    int i = 156;
    System.out.println(Integer.toBinaryString(i));
    System.out.println(findNextLargeNbr(i));
    System.out.println(Integer.toBinaryString(findNextLargeNbr(i)));
  }

  private static int findNextLargeNbr(int i) {
    int next = 0;
    if (i > 0) {
      int rightMostBitAssignment = i & (-i);
      int nextHigherOneBit = i + rightMostBitAssignment;
      int rightOnesPattern = i ^ nextHigherOneBit;
      rightOnesPattern = rightOnesPattern/rightMostBitAssignment;
      rightOnesPattern >>= 2;
      next = nextHigherOneBit | rightOnesPattern;
    }
    return next;
  }
}
