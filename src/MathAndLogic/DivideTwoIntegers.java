package MathAndLogic;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
 *
 * Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, assume that your function returns 231 − 1 when the division result overflows.
 *
 *
 *
 * Example 1:
 *
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = truncate(3.33333..) = 3.
 * Example 2:
 *
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = truncate(-2.33333..) = -2.
 * Example 3:
 *
 * Input: dividend = 0, divisor = 1
 * Output: 0
 * Example 4:
 *
 * Input: dividend = 1, divisor = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * -231 <= dividend, divisor <= 231 - 1
 * divisor != 0
 */
public class DivideTwoIntegers {

  public static void main(String[] args) {
    DivideTwoIntegers x = new DivideTwoIntegers();
    System.out.println(x.divide_faster(100, 2));
    System.out.println(x.divide_time_exceeded(100, 2));
  }

  public int divide_time_exceeded(int dividend, int divisor) {
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
      return Integer.MAX_VALUE;
    } else if (dividend == Integer.MAX_VALUE && divisor == 1) {
      return Integer.MAX_VALUE;
    } else if (dividend == Integer.MIN_VALUE && divisor == 1) {
      return Integer.MIN_VALUE;
    }
    int a = dividend;
    int b = divisor;
    int x = 1;
    int quotiant = 0;

    if (dividend < 0) {
      a = (~dividend) + 1;
      x = (~x)+1;
    }
    if (divisor < 0) {
      b = (~divisor) + 1;
      x = (~x)+1;
    }

    while (a >= b) {
      int z = 0;
      while (a > (b << 1 << z)) {
        z++;
      }
      quotiant += 1 << z;
      a = a - (b << z);
    }
    if (x < 0) {
      return (~quotiant)+1;
    } else {
      return quotiant;
    }
  }

  public int divide_faster(int dividend, int divisor) {

    int p = Integer.MAX_VALUE;
    int q = Integer.MIN_VALUE;
    if (p < q) {
      System.out.println("a < b");
    }
    if (p - q < 0) {
      System.out.println("a - b < 0");
    }


    if(dividend == Integer.MIN_VALUE && divisor == -1){
      return Integer.MAX_VALUE;
    }
    int a = Math.abs(dividend);
    int b = Math.abs(divisor);
    int res = 0;
    while(a - b >= 0){
      int x = 0;
      while( a - (b << 1 << x) >= 0){
        x++;
      }
      res += 1 << x;
      a -= b << x;
    }
    return (dividend >= 0) == (divisor >= 0) ? res :-res;
  }
}
