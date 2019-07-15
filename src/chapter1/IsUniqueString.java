package chapter1;

/**
 * Implement an algorithm to determine if a string has all unique characters. Dont use additional
 * data structures.
 */
public class IsUniqueString {

  public static void main(String[] args) {
    System.out.println("\"hasDuplicate\" is unique? -> " + isUnique("hasDuplicate"));
    System.out.println("\"noDuplicate\" is unique? -> " + isUnique("noDuplicate"));
  }

  private static boolean isUnique(String str) {
    int holder = 0;
    if (str != null){
      for(char c : str.toCharArray()) {
        if((holder & (1 << c)) > 0) {
          return false;
        }
        holder |= (1 << c);
      }
    }
    return true;
  }
}
