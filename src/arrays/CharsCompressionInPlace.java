package arrays;

/**
 * Given an array of characters, compress it in-place.
 *
 * The length after compression must always be smaller than or equal to the original array.
 *
 * Every element of the array should be a character (not int) of length 1.
 *
 * After you are done modifying the input array in-place, return the new length of the array.
 *
 * Example :
 * Input:
 * ["a","a","b","b","c","c","c"]
 * Output:
 * Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 *
 * Input:
 * ["a"]
 * Output:
 * Return 1, and the first 1 characters of the input array should be: ["a"]
 *
 * Input:
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * Output:
 * Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 */
public class CharsCompressionInPlace {

  public static void main(String[] args) {
    char[] arr1 = {'a','a','b','b','c','c','c'};
    System.out.println(compressCharsInPlace(arr1));

    char[] arr2 = {'a'};
    System.out.println(compressCharsInPlace(arr2));

    char[] arr3 = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
    System.out.println(compressCharsInPlace(arr3));

    char[] arr4 = {'a','b'};
    System.out.println(compressCharsInPlace(arr4));
  }

  /**
   * When current and next char doesn't match, place a char and its length subsequently.
   * Note: Value 12 suppose to be in 2 places - "1" and "2".
   * @param arr
   * @return
   */
  private static int compressCharsInPlace(char[] arr) {
    int writer = 0;
    for (int reader = 0; reader < arr.length; reader++) {
      if (reader + 1 == arr.length || arr[reader + 1] != arr[reader]) {
        if (reader > writer) {
          String len = "" + (reader - writer + 1);
          arr[writer++] = arr[reader];
          for(char c : len.toCharArray()) {
            arr[writer++] = c;
          }
        } else {
          arr[writer++] = arr[reader];
        }
      }
    }
    return writer;
  }
}
