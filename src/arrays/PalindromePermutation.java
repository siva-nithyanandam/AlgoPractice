package arrays;

/**
 * Given a string, write a function to check if it is a permutation of a palindrome.
 * Example:
 * Input: Tact Coa
 * Output: True (Permutation: "taco cat", "atco cta")
 */
public class PalindromePermutation {

  public static void main(String[] args) {
    System.out.println(isPermutationPalindrome("Tact coa"));
    System.out.println(isPermutationPalindrome("aab"));
    System.out.println(isPermutationPalindrome("carerac"));
    System.out.println(isPermutationPalindrome("abca"));
  }

  /**
   * Assumptions for below:
   * 1. Case insensitive
   * 2. Space doesn't count
   * Idea is, If there is more than one character having odd count, then characters are not
   * distributed. Example -> "aba" is, but "abca" is not.
   * @param str
   * @return
   */
  private static boolean isPermutationPalindrome(String str) {
    str = str.toLowerCase();
    int[] arr = new int[256];
    for(char c : str.toCharArray()) {
      if (c != ' ') {
        arr[c]++;
      }
    }
    int oddCount = 0;
    for(int i: arr) {
      if((i & 1) == 1) {
        oddCount++;
      }
    }
    return oddCount <= 1;
  }
}
