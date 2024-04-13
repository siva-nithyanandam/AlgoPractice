package BitManipulation;

/**
 * Write a program to swap odd and even bits in an integer with as few instructions as possible
 * e.g. bit 0 and 1 are swapped, bit 2 and bit 3 are swapped, and so on
 */

/**
 * Get oddBits and evenBits separately. Move left and right and join them.
 */
public class PairwiseSwap {

    public static void main(String[] args) {
        int i = 23;
        System.out.println("Given binary  -> " + Integer.toBinaryString(i));
        int res = doPairwiseSwap(i);
        System.out.println("Result binary -> " + Integer.toBinaryString(res));
    }

    private static int doPairwiseSwap(int given) {
        System.out.println("0xAAAAAAAA  -> " + Integer.toBinaryString(0xAAAAAAAA));
        System.out.println("0x55555555  -> " + Integer.toBinaryString(0x55555555));
        int oddBits = given & (0xAAAAAAAA);
        int evenBits = given & (0x55555555);
        return (oddBits >> 1) | (evenBits << 1);
    }
}
