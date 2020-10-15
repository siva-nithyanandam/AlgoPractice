package arrays;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list,
 * and each element in the array contain a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Example 2:
 *
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 */
public class PlusOne {

    public static void main(String[] args) {
        PlusOne o = new PlusOne();
        int[] res;
        res = o.plusOne(new int[] {1, 2, 3});
        printList(res);
        System.out.println();

        res = o.plusOne(new int[] {4, 3, 2, 1});
        printList(res);
        System.out.println();

        res = o.plusOne(new int[] {9, 9, 9, 9});
        printList(res);
        System.out.println();

        res = o.plusOne(new int[] {1, 9, 9, 9});
        printList(res);
        System.out.println();

        res = o.plusOne(new int[] {0});
        printList(res);
        System.out.println();

        res = o.plusOne(new int[] {});
        printList(res);
        System.out.println();
    }

    private static void printList(int[] res) {
        for (int i : res) {
            System.out.print(i);
        }
    }

    public int[] plusOne(int[] digits) {
        if (digits.length == 0) {
            return digits;
        }
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int j = digits[i] + carry;
            digits[i] = j % 10;
            carry = j / 10;
        }
        if (carry > 0) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            return res;
        } else {
            return digits;
        }
    }
}
