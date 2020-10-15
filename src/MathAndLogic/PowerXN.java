package MathAndLogic;

/**
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 *
 * Example 1:
 *
 * Input: 2.00000, 10
 * Output: 1024.00000
 * Example 2:
 *
 * Input: 2.10000, 3
 * Output: 9.26100
 * Example 3:
 *
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * Note:
 *
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−231, 231 − 1]
 */

/**
 * O(log n)
 * Divide and conquer strategy
 * 2 ^ 2 = (2 ^ 1) * (2 ^ 1)
 * Look for decimal places -  So use double or float NOT integer.
 */
public class PowerXN {

    public static void main(String[] args) {
        System.out.println(findPowerValue(2, -3));
        System.out.println(findPowerValue(2, -2));
        System.out.println(findPowerValue(2.1f, 3));
        System.out.println(findPowerValue(2, -2));
    }

    private static float findPowerValue(float a, int b) {
        if (b == 0) {
            return 1;
        } else {
            float temp = findPowerValue(a, b/2);
            if ((b % 2) == 0) {
                return temp * temp;
            } else {
                if (b > 0) {
                    return a * temp * temp;
                } else {
                    return temp * temp / a;
                }
            }
        }
    }
}
