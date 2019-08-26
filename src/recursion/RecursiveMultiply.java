package recursion;

/**
 * Write a recursive function to multiply two positive integers without using the * operator.
 * You can use addition, subtraction, and bit shifting, but you should minimize the number of
 * those operations.
 */

/**
 * O(log multiplier)
 */
public class RecursiveMultiply {

    public static void main(String[] args) {
        System.out.println(doRecursiveMultiply(3, 6));
        System.out.println(doRecursiveMultiply(3, 7));
        System.out.println(doRecursiveMultiply(3, 100));
        System.out.println(doRecursiveMultiply(100, -3));
        System.out.println(doRecursiveMultiply(3, -6));
        System.out.println(doRecursiveMultiply(5, -11));
    }

    private static int doRecursiveMultiply(int x, int y) {
        if (y == 0) {
            return 0;
        }
        int res;
        if (y > 0) {
            res = doRecursiveMultiply(x, y >> 1);
            if ((y & 1) == 0) {
                return res << 1;
            } else {
                return x + (res << 1);
            }
        } else {
            if ((y & 1) == 0) {
                res = doRecursiveMultiply(x, y >> 1);
                return res << 1;
            } else {
                res = doRecursiveMultiply(x, (y >> 1)+1);
                return -x + (res << 1);
            }
        }
    }
}
