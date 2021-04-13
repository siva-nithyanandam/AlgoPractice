package arrays;

/**
 * https://www.geeksforgeeks.org/find-the-two-repeating-elements-in-a-given-array/
 *
 * You are given an array of n+2 elements.
 * All elements of the array are in range 1 to n.
 * And all elements occur once except two numbers which occur twice.
 * Find the two repeating numbers.
 *
 * Example:
 *
 * Input:
 * arr = [4, 2, 4, 5, 2, 3, 1]
 * n = 5
 * Output:
 * 4 2
 * Explanation:
 * The above array has n + 2 = 7 elements with all elements occurring once except 2 and 4 which occur twice. So the output should be 4 2.
 */
public class TwoRepeatingElementsInaGivenArray {

  public static void main(String[] args) {
    TwoRepeatingElementsInaGivenArray o = new TwoRepeatingElementsInaGivenArray();
    System.out.println(System.currentTimeMillis());
    o.identifyDuplicates_1(new int[]{4, 2, 4, 5, 2, 3, 1}, 5);
    System.out.println(System.currentTimeMillis());
    o.identifyDuplicates_2(new int[]{4, 2, 4, 5, 2, 3, 1}, 5);
    System.out.println(System.currentTimeMillis());
    o.identifyDuplicates_3(new int[]{4, 2, 4, 5, 2, 3, 1}, 5);
    System.out.println(System.currentTimeMillis());
  }

  private void identifyDuplicates_3(int[] arr, int n) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[Math.abs(arr[i])] < 0) {
        System.out.println(Math.abs(arr[i]));
      } else {
        arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])];
      }
    }
  }

  private void identifyDuplicates_2(int[] arr, int n) {
    int expXor = 0, actXor = 0;
    for (int i = 1; i <= n; i++) {
      expXor ^= i; //all xor's
    }
    for (int i : arr) {
      actXor ^= i; //Except x,y
    }
    int xor = expXor ^ actXor; //x xor y
    int lastBit = xor & (~(xor-1));
    int x = 0, y = 0;
    for (int i = 1; i <= n; i++) {
      int a = lastBit & i;
      if (a != 0) {
        x = x ^ i;
      } else {
        y = y ^ i;
      }
    }
    for (int i : arr) {
      int a = lastBit & i;
      if (a != 0) {
        x = x ^ i;
      } else {
        y = y ^ i;
      }
    }
    System.out.print(x);
    System.out.print("-");
    System.out.println(y);
  }

  private void identifyDuplicates_1(int[] arr, int n) {
    int expSum = n*(n+1)/2;
    int expMul = factorial(n);
    int actSum = 0, actMul = 1;
    for (int i : arr) {
      actSum += i;
      actMul *= i;
    }
    int xPlusY = actSum - expSum; //x+y
    int xMulY = actMul / expMul; //xy

    int xMinusy = (int)Math.sqrt((xPlusY * xPlusY) - (4 * xMulY)); //x-y
    int x = (xPlusY + xMinusy)/2;
    int y = (xPlusY - xMinusy)/2;
    System.out.print(x);
    System.out.print("-");
    System.out.println(y);
  }

  private int factorial(int n) {
    if (n == 1) {
      return 1;
    }
    return n * factorial(n-1);
  }
}
