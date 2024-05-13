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
    System.out.println((Integer.MAX_VALUE));
    System.out.println(Math.log10(Integer.MAX_VALUE));
    System.out.println(o.reverse_v1(-123));
    System.out.println(o.reverse(1534236469));
    System.out.println(o.reverse(10));
    System.out.println(o.reverse(123));
  }

  public int reverse(int x) {
    int rev_num = 0;
    int prev_num = 0;
    while(x != 0) {
      rev_num = rev_num * 10 + x % 10;
      if (rev_num/10 != prev_num) {
        return 0;
      }
      x = x / 10;
      prev_num = rev_num;
    }
    return rev_num;
  }

  public int reverse_v1(int x) {
    int rev = 0;
    while(x != 0) {
      int pop = x % 10;
      x = x / 10;
      if((rev > Integer.MAX_VALUE/10) || (rev == Integer.MAX_VALUE/10 && pop > 7)) {
        return 0;
      }
      if((rev < Integer.MIN_VALUE/10) || (rev == Integer.MIN_VALUE/10 && pop < -8)) {
        return 0;
      }
      rev = rev * 10 + pop;
    }
    return rev;
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
