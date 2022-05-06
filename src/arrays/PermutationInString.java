package arrays;

public class PermutationInString {

  public static void main(String[] args) {
    PermutationInString o = new PermutationInString();
    System.out.println(o.checkInclusion1("ab", "eidbaooo"));
    System.out.println(o.checkInclusion1("ab", "eidboaooboa"));
  }

  public boolean checkInclusion1(String s1, String s2) {
    int[] map = new int[128];
    for (char c : s1.toCharArray()) {
      map[c]++;
    }
    int count = s1.length();
    for (int i = 0, j = 0; i < s2.length(); i++) {
      if (map[s2.charAt(i)]-- > 0) {
        count--;
      }
      while (count == 0) {
        if (i - j + 1 == s1.length()) {
          return true;
        }
        if (++map[s2.charAt(j++)] > 0) {
          count++;
        }
      }
    }
    return false;
  }

  public boolean checkInclusion(String s1, String s2) {
    int[] map = new int[128];
    for (char c : s1.toCharArray()) {
      map[c]++;
    }
    int count = s1.length();
    char[] chars = s2.toCharArray();
    int left = 0, right = 0;
    while (right < chars.length) {
      if (map[chars[right++]]-- > 0) {
        count--;
      }
      while (count == 0) {
        if (right - left == s1.length()) {
          return true;
        }
        if (++map[chars[left++]] > 0) {
          count++;
        }
      }
    }
    return false;
  }

}
