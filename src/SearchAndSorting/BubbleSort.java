package SearchAndSorting;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/14/2021  7:59 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://www.programiz.com/dsa/bubble-sort
 */
public class BubbleSort {

  public static void main(String[] args) {
    BubbleSort o = new BubbleSort();
    int[] nums = {4, 2, 1, 6, 7};
    o.bubbleSort(nums);
    System.out.println(nums);
  }

  // perform the bubble sort
  void bubbleSort(int array[]) {
    int size = array.length;

    // loop to access each array element
    for (int i = 0; i < (size-1); i++) {

      // check if swapping occurs
      boolean swapped = false;

      // loop to compare adjacent elements
      for (int j = 0; j < (size-i-1); j++) {

        // compare two array elements
        // change > to < to sort in descending order
        if (array[j] > array[j + 1]) {

          // swapping occurs if elements
          // are not in the intended order
          int temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;

          swapped = true;
        }
      }
      // no swapping means the array is already sorted
      // so no need for further comparison
      if (!swapped)
        break;

    }
  }
}
