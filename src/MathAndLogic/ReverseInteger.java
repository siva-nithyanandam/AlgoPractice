package MathAndLogic;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 * Input: 123
 * Output: 321
 * Example 2:
 *
 * Input: -123
 * Output: -321
 * Example 3:
 *
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
 * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger {

  public static void main(String[] args) {
    ReverseInteger o = new ReverseInteger();
    System.out.println(o.reverse(1534236469));
    System.out.println(o.reverse(-123));
    System.out.println(o.reverse(10));
    System.out.println(o.reverse(123));
  }

  public int reverse(int x) {
    int val = 1;
    if (x < 0) {
      x = x * -1;
      val = -1;
    }
    int rev_num = 0;
    while(x > 0) {
      rev_num = rev_num * 10 + x % 10;
      x = x / 10;
    }
    return rev_num * val;
  }

  public static int reverseBits(int n) {
    int rev = 0;

    // traversing bits of 'n'
    // from the right
    while (n > 0)
    {
      // bitwise left shift
      // 'rev' by 1
      rev <<= 1;

      // if current bit is '1'
      if ((int)(n & 1) == 1)
        rev ^= 1;

      // bitwise right shift
      //'n' by 1
      n >>= 1;
    }
    // required number
    return rev;
  }
}
