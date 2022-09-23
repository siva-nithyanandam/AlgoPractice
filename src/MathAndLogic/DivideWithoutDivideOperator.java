package MathAndLogic;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/12/2021  8:25 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 *
 */
public class DivideWithoutDivideOperator {

  public static void main(String[] args) {
    DivideWithoutDivideOperator o = new DivideWithoutDivideOperator();
    System.out.println(o.divide(10, 3));
  }

  public long divide(long dividend, long divisor) {

// Calculate sign of divisor
// i.e., sign will be negative
// only iff either one of them
// is negative otherwise it
// will be positive
    long sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;

// remove sign of operands
    dividend = Math.abs(dividend);
    divisor = Math.abs(divisor);

// Initialize the quotient
    long quotient = 0, temp = 0;

// test down from the highest
// bit and accumulate the
// tentative value for
// valid bit
// 1<<31 behaves incorrectly and gives Integer
// Min Value which should not be the case, instead
    // 1L<<31 works correctly.
    for (int i = 31; i >= 0; --i) {

      if (temp + (divisor << i) <= dividend) {
        temp += divisor << i;
        quotient |= 1L << i;
      }
    }

    return (sign * quotient);
  }
}
