package arrays;

/**
 * https://www.geeksforgeeks.org/find-duplicates-constant-array-elements-0-n-1-o1-space/?ref=rp
 *
 *
 */
public class DuplicatesInConstantArray {

  public static void main(String[] args) {
    DuplicatesInConstantArray o = new DuplicatesInConstantArray();
    o.identifyDuplicate(new int[]{1, 2, 3, 4, 5, 6, 3});
  }

  private void identifyDuplicate(int[] arr) {
    int size = arr.length;
    int slowPointer = arr[0];
    int fastPointer = arr[arr[0]];
    while (slowPointer != fastPointer) {
      slowPointer = arr[slowPointer];
      fastPointer = arr[arr[fastPointer]];
    }
    fastPointer = 0;
    while (slowPointer != fastPointer) {
      slowPointer = arr[slowPointer];
      fastPointer = arr[fastPointer];
    }
    System.out.println(slowPointer);
  }


}
