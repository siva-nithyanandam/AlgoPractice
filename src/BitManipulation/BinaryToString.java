package BitManipulation;

/**
 *  Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double,
 *  print the binary representation. If the number cannot be represented accurately
 *  in binary with at most 32 characters, print "ERROR:'
 */

/**
 * First, let’s start off by asking ourselves what a non-integer number in binary looks like.
 * By analogy to a decimal number, the binary number 0 .101 would look like:
 *
 * 0. 101 = (1 * 1/2) + (0 *1/4) + (1 * 1/8).
 *
 * So subtract the given decimal by 0.5, 0.25, 0.125 and so on, when its greater than that and
 * append 1 in that case, or else append 0 and have given decimal as it is.
 *
 * And binary representation should not 31 places. If so, return ERROR.
 */
public class BinaryToString {
  public static void main(String[] args) {
    System.out.println(convertToBinary(.625));
    System.out.println(convertToBinary(.72));
  }

  private static String convertToBinary(double num) {
    if (num <= 0 || num >= 1) {
      return "ERROR";
    }
    StringBuilder sb = new StringBuilder(".");
    double frac = 0.5;
    while(num > 0) {
      if (num >= frac) {
        sb.append("1");
        num -= frac;
      } else {
        sb.append("0");
      }
      frac /= 2;
      if (sb.length() > 31) {
        return "ERROR";
      }
    }
    return sb.toString();
  }
}
