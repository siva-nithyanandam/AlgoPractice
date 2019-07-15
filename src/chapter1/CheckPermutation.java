package chapter1;

/**
 * Given two strings, write a method to decide if one is a permutation of the other.
 */
public class CheckPermutation {

  public static void main(String[] args) {
    System.out.println(checkPermutation("firstlast", "lastfirst"));
    System.out.println(checkPermutation("firstlast", "lastfirstlast"));
    System.out.println(checkPermutation("firstlast1234", "lastfirstlast"));
  }

  private static boolean checkPermutation(String s1, String s2) {
    if (s1 == null || s2 == null || s1.length() != s2.length()) {
      return false;
    }
    int[] arr = new int[256];
    for (int i = 0; i < s1.length(); i++) {
      arr[s1.charAt(i)]++;
      arr[s2.charAt(i)]--;
    }
    for(int i : arr) {
      if (i != 0) {
        return false;
      }
    }
    return true;
  }
}
