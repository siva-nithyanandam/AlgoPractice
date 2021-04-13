package BitManipulation;

/**
 * Write a function to determine the number of bits you would need to flip to convert
 * integer A to integer B.
 * EXAMPLE
 * Input: 29 (or: 11101), 15 (or: 10111)
 * Output: 2
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.hackerearth.com/practice/notes/bit-manipulation/
 */
public class Subsets {
  public static void main(String[] args) {
    System.out.println(findNbrOfFlips(29, 15));
    char[] a = {'a', 'b', 'c'};
    possibleSubsets(a, 3);
    subsets(new int[]{1,2,3});
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

  private static void possibleSubsets(char A[], int N) {

    for (int i = 0; i < (1 << N); i++) {
      for (int j = 0; j < N; j++) {
        if ((i & (1 << j)) > 0) {
          System.out.print(A[j]);
        }
      }
      System.out.println();
    }
  }

  public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> initial = new ArrayList<Integer>();
    result.add(initial);
    return helper(nums, result);
  }

  private static List<List<Integer>> helper(int[] currentNums, List<List<Integer>> currentResult) {
    if (currentNums.length == 0) {
      return currentResult;
    }

    int current = currentNums[0];
    int resultSize = currentResult.size();

    for (int i = 0; i < resultSize; i++) {
      List<Integer> copyList = new ArrayList<Integer>(currentResult.get(i));
      copyList.add(current);
      currentResult.add(copyList);
    }

    int[] numsTail = Arrays.copyOfRange(currentNums, 1, currentNums.length);
    return helper(numsTail, currentResult);
  }
}
