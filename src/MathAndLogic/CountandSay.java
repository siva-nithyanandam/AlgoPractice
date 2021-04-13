package MathAndLogic;

/**
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 *
 * countAndSay(1) = "1"
 * countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.
 * To determine how you "say" a digit string, split it into the minimal number of groups so that each group is a contiguous section all of the same character. Then for each group, say the number of characters, then say the character. To convert the saying into a digit string, replace the counts with a number and concatenate every saying.
 *
 * For example, the saying and conversion for digit string "3322251":
 *
 *
 * Given a positive integer n, return the nth term of the count-and-say sequence.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: "1"
 * Explanation: This is the base case.
 * Example 2:
 *
 * Input: n = 4
 * Output: "1211"
 * Explanation:
 * countAndSay(1) = "1"
 * countAndSay(2) = say "1" = one 1 = "11"
 * countAndSay(3) = say "11" = two 1's = "21"
 * countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 */
public class CountandSay {

  public static void main(String[] args) {
    CountandSay o = new CountandSay();
    System.out.println(o.countAndSay_faster(4));
    System.out.println(o.countAndSay(4));
  }

  public String countAndSay_faster(int n) {
    String seed = "1";
    for (int i = 1; i < n; i++) {
      seed = convert(seed);
    }

    return seed;
  }

  private String convert(String seed) {
    StringBuilder sb = new StringBuilder();
    char[] s = seed.toCharArray();
    int num = 1;
    int i = 0;

    while (i < s.length) {
      while (i +1 < s.length && s[i] == s[i + 1]) {
        num++;
        i++;
      }
      sb.append(num);
      sb.append(s[i]);
      i++;
      num = 1;
    }

    return sb.toString();
  }

  public String countAndSay(int n) {
    if (n == 1) {
      return "1";
    } else if (n == 2) {
      return "11";
    }

    String str = "11";
    for (int i = 3; i <= n; i++) {
      str = str + "$";
      int len = str.length();
      char[] arr = str.toCharArray();
      int cnt = 1;
      String tmp = "";

      for (int j = 1; j < len; j++) {
        if (arr[j] != arr[j-1]) {
          tmp = tmp + cnt + arr[j-1];
          cnt = 1;
        } else {
          cnt++;
        }
      }
      str = tmp;
    }
    return str;
  }
}
