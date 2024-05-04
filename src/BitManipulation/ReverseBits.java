package BitManipulation;
/**
 * @time 5/4/24-12:55 PM
 * @author sivaprakashnithyanandam
 */

/**
 *
 * https://leetcode.com/problems/reverse-bits/description/
 *
 * Reverse bits of a given 32 bits unsigned integer.
 *
 * Note:
 *
 * Note that in some languages, such as Java, there is no unsigned integer type. In this case, both input and output will be given as a signed integer type. They should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above, the input represents the signed integer -3 and the output represents the signed integer -1073741825.
 *
 *
 * Example 1:
 *
 * Input: n = 00000010100101000001111010011100
 * Output:    964176192 (00111001011110000010100101000000)
 * Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
 * Example 2:
 *
 * Input: n = 11111111111111111111111111111101
 * Output:   3221225471 (10111111111111111111111111111111)
 * Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10111111111111111111111111111111.
 *
 *
 * Constraints:
 *
 * The input must be a binary string of length 32
 *
 *
 * Follow up: If this function is called many times, how would you optimize it?
 */
public class ReverseBits {

    public static void main(String[] args) {
        ReverseBits o = new ReverseBits();
        System.out.println(o.reverseBits_other(6));
        System.out.println(o.reverseBits_other(5));
        System.out.println(o.reverseBits(5));

        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(-5));
        System.out.println(Integer.toBinaryString(-5 >> 1));
        System.out.println(Integer.toBinaryString(-5 >> 2));
    }

    public int reverseBits(int n) {

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans <<= 1;
            ans |= (n & 1);
            n >>= 1;
        }
        return ans;
    }

    public int reverseBits_other(int n) {
        if (n == 0) {
            return 0;
        }

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            if ((n & 1) == 1) {
                result++;
            }
            n >>= 1;
        }
        return result;
    }
}
