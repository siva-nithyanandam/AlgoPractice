package arrays;

/**
 * Reverse a given word.
 * Example:
 * Given : This is sample
 * Output: sample is This.
 */

/**
 * Used 2 solutions, with and without data structure.
 * With data structure -> Given complete word been reversed and word by word reversed
 * Without data structure -> Took last char and append at first and until a space, and then substring until a space
 * and append char.
 */
public class ReverseWord {
  public static void main(String[] args) {
    String s = "reverse this given string";
    System.out.println(reverseWordWithDataStructure(s));
    System.out.println(reverseWordWithoutDataStructure(s));
  }

  private static String reverseWordWithoutDataStructure(String s) {
    int i = 0;
    int j = s.length() - 1;
    int k = 0;
    int m = 0;

    while (i <= j) {
      if (s.charAt(j) == ' ') {
        k = k + m;
        s = s.substring(0, k) + s.charAt(j) + s.substring(k);
        k++;
        m = 0;
      } else {
        s = s.substring(0, k) + s.charAt(j) + s.substring(k);
        m++;
      }
      i++;
    }
    return s.substring(0, i);
  }

  private static String reverseWordWithDataStructure(String s) {
    if (s == null || s.length() == 0) {
      return s;
    }
    char[] nums = s.toCharArray();
    reverseString(nums, 0, nums.length-1);
    int previousI = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == ' ') {
        reverseString(nums, previousI, i-1);
        previousI = i+1;
      }
    }
    reverseString(nums, previousI, nums.length-1);
    return String.valueOf(nums);
  }

  private static void reverseString(char[] nums, int start, int end) {
    while (start < end) {
      nums[start] ^= nums[end];
      nums[end] ^= nums[start];
      nums[start] ^= nums[end];
      start++;
      end--;
    }
  }

}
