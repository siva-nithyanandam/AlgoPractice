package arrays;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/1/2021  5:01 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/3051/
 *
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 *
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 *
 * Constraints:
 *
 * 1 <= num1.length, num2.length <= 200
 * num1 and num2 consist of digits only.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */
public class MultiplyStrings {

  public static void main(String[] args) {
    MultiplyStrings o = new MultiplyStrings();
    System.out.println(o.multiply("9133", "0"));
    System.out.println(o.multiply("123", "456"));
  }

  public String multiply_faster(String num1, String num2) {
    if (num1.equals("0") || num2.equals("0")) {
      return "0";
    }

    int[] res = new int[num1.length() + num2.length()];
    char[] c1 = num1.toCharArray();
    char[] c2 = num2.toCharArray();

    for (int i = c1.length - 1, m = 0; i >= 0; i--, m++) {
      for (int j = c2.length - 1, n = 0; j >= 0; j--, n++) {
        int prod = (c1[i] - '0') * (c2[j] - '0');
        res[m + n] += prod;
      }
    }

    for (int i = 0; i < c1.length + c2.length; i++) {
      if (res[i] >= 10) {
        res[i + 1] += res[i] / 10;
        res[i] = res[i] % 10;
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = c1.length + c2.length - 1; i >= 0; i--) {
      if (i == c1.length + c2.length - 1 && res[i] == 0) {
        continue;
      }
      sb.append((char) (res[i] + '0'));
    }

    return sb.toString();
  }

  public String multiply(String num1, String num2) {

    if (num1.equals("0") || num2.equals("0")) {
      return "0";
    }
    int n1 = num1.length();
    int n2 = num2.length();
    int[] arr = new int[n1+n2];

    for (int i = n1-1; i >= 0; i--) {
      int x = num1.charAt(i)-'0';
      for (int j = n2-1; j >= 0; j--) {
        int y = num2.charAt(j)-'0';

        int sum = (x * y) + arr[i+j+1];
        arr[i+j+1] = sum % 10;
        arr[i+j] += sum / 10;
      }
    }

    StringBuilder res = new StringBuilder();
    int z = 0;

    while(z < arr.length) {
      if (z == 0 && arr[z] == 0) {
        z++;
        continue;
      }
      res.append(arr[z++]);
    }
    return res.toString();
  }
}
