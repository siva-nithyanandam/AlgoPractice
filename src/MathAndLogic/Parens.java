package MathAndLogic;

/**
 *  Implement an algorithm to print all valid (e.g., properly opened and closed) combinations
 *  of n pairs of parentheses.
 * EXAMPLE
 * Input: 3 Output: ((())), (()()), (())(), ()(()), ()()()
 */
public class Parens {
    public static void main(String[] args) {
        printParenthesis(3);
    }

    private static void printParenthesis(int n) {
        char[] arr = new char[2*n];
        printParenthesis(n, arr, 0, 0, 0);
    }

    private static void printParenthesis(int n, char[] arr, int index, int open, int close) {
        if (index == arr.length) {
            for (char c : arr) {
                System.out.print(c);
            }
            System.out.println();
        }
        if (open < n) {
            arr[index] = '(';
            printParenthesis(n, arr, index + 1, open+1, close);
        }
        if (close < open) {
            arr[index] = ')';
            printParenthesis(n, arr, index + 1, open, close+1);
        }
    }
}
