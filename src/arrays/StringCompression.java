package arrays;

/**
 * Implement a method to perform basic string compression using the counts of repeated
 * characters. For example, the string aabcccccaaa would become a2b1c5a3. If the "compressed"
 * string would not become smaller than the original string, your method should return the original
 * string. You can assume the string has only uppercase and lowercase letters(a-z).
 */
public class StringCompression {

  public static void main(String[] args) {
    System.out.println(compressString("aabcccccaaa"));
    System.out.println(compressString("a"));
    System.out.println(compressString("ab"));
    System.out.println(compressString("aa"));
    System.out.println(compressString("aab"));
    System.out.println(compressString("aaab"));
    System.out.println(compressString("aaaab"));
    System.out.println(compressString(""));
    System.out.println(compressString(null));
    /**
     * Results ->
     *
     * a2b1c5a3
     * a
     * ab
     * aa
     * aab
     * aaab
     * a4b1
     *
     * null
     */
  }

  private static String compressString(String str) {
    //Base case
    if (str == null || str.length() == 0) {
      return str;
    }

    StringBuilder sb = new StringBuilder();
    //Initialize first character
    char temp = str.charAt(0);
    int count = 1;
    //Start loop from second character and count accordingly
    for (int i = 1; i < str.length(); i++) {
      //When there is a change in character, record and reset
      if(temp != str.charAt(i)) {
        sb.append(temp).append(count);
        temp = str.charAt(i);
        count = 0;
      }
      count++;
    }
    sb.append(temp).append(count);
    //Return compressed string smaller than original, else return original
    return sb.length() < str.length() ? sb.toString() : str;
  }
}
