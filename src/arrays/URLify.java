package arrays;

/**
 * Write a method to replace all spaces in a string with "%20". YOu may assume that the string has
 * sufficient space at the end to hold the additional characters, and that you are given the true
 * length of the string.
 * Example:
 * Input -> "Mr John Smith       ", 13
 * Output -> "Mr%20John%20Smith"
 */
public class URLify {

  public static void main(String[] args) {
    String s = "Mr John Smith    ";
    System.out.println(urlify(s.toCharArray(), 13));
  }

  /**
   * Take last character of the actual string and put it last, and if you encounter ' '(space),
   * append '%20' and move on!
   * @param arr
   * @param strLen
   * @return
   */
  private static String urlify(char[] arr, int strLen) {
    if (arr.length != strLen) {
      for(int i = arr.length-1, j = strLen - 1; i >= 0; i--, j--) {
        if (arr[j] != ' ') {
          arr[i] = arr[j];
        } else {
          arr[i--] = '0';
          arr[i--] = '2';
          arr[i] = '%';
        }
      }
    }
    return String.valueOf(arr);
  }
}
