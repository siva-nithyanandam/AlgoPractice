package BitManipulation;

/**
 * You are given two 32-bit numbers, N and M, and two bit positions, i and j.
 * Write a method to insert M into N such that M starts at bit j and ends at bit i.
 * You can assume that the bits j through i have enough space to fit all of M.
 * That is, if M = 10011, you can assume that there are at least 5 bits between j and i.
 * You would not, for example, have j = 3 and i = 2, because M could not fully fit
 * between bit 3 and bit 2.
 * EXAMPLE
 * Input: N = 10000000000, M = 10011, i 2, j 6
 * Output: N = 10001001100
 */
public class Insertion {
    public static void main(String[] args) {
        int n = Integer.parseInt("10000010000", 2);
        int m = Integer.parseInt("10011", 2);
        int i = 2;
        int j = 6;
        insert(n, m, i, j);

        n = Integer.parseInt("11111111111", 2);
        m = Integer.parseInt("0", 2);
        i = 3;
        j = 6;
        insert(n, m, i, j);
    }

    private static void insert(int n, int m, int i, int j) {
        int k = j - i + 1;
        int mask1 = (1 << k) - 1;
        int mask2 = ~(mask1 << i);
        int res = (n & mask2) | (m << i);
        System.out.println(Integer.toBinaryString(res));
    }
}
