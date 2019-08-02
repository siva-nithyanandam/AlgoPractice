package BitManipulation;

/**
 * Write a function to determine the number of bits you would need to flip to convert
 * integer A to integer B.
 * EXAMPLE
 * Input: 29 (or: 11101), 15 (or: 10111)
 * Output: 2
 */

/**
 * https://www.hackerearth.com/practice/notes/bit-manipulation/
 */
public class Conversion {
  public static void main(String[] args) {
    System.out.println(findNbrOfFlips(29, 15));
    char[] a = {'a', 'b', 'c'};
    possibleSubsets(a, 3);
  }

  private static int findNbrOfFlips(int i, int j) {
    int count = 0;
    int k = i ^ j;
    while(k != 0) {
      if ((k & 1) == 1) {
        count++;
      }
      k >>= 1;
    }
    return count;
  }

  private static void possibleSubsets(char A[], int N)
  {
    for(int i = 0;i < (1 << N); ++i)
    {
      for(int j = 0;j < N;++j)
        if((i & (1 << j)) == 1) {
          System.out.println(A[j]);
        }
    }
  }
}
