package arrays;

/**
 * Reverse a given word with out additional data structure.
 * Example:
 * Given : This is sample
 * Output: sample is This.
 */
public class ReverseWord {
  public static void main(String[] args) {
    String s = "This is sample";
    System.out.println(reverseWord(s));
  }

  private static String reverseWord(String s) {
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
    char temp = '\u0000';
    while (start < end) {
      temp = nums[start];
      nums[start++] = nums[end];
      nums[end--] = temp;
    }
  }

}
