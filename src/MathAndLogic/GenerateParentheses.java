package MathAndLogic;

import java.util.ArrayList;
import java.util.List;

/**
 *  Implement an algorithm to print all valid (e.g., properly opened and closed) combinations
 *  of n pairs of parentheses.
 * EXAMPLE
 * Input: 3 Output: ((())), (()()), (())(), ()(()), ()()()
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        GenerateParentheses o = new GenerateParentheses();
        List<String> list = o.generateParenthesis(2);
        printList(list);
    }

    private static void printList(List<String> list) {
        for (String s : list) {
            System.out.println(s);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        char[] chars = new char[n*2];
        generateParenthesis(n, 0, 0, chars, res);
        return res;
    }

    private void generateParenthesis(int n, int opens, int closes, char[] chars, List<String> res) {

        int tot = opens + closes;
        if (tot == n*2) {
            res.add(String.valueOf(chars));
            return;
        }
        if (opens < n) {
            chars[tot] = '(';
            generateParenthesis(n, opens+1, closes, chars, res);
        }
        if (closes < opens) {
            chars[tot] = ')';
            generateParenthesis(n, opens, closes+1, chars, res);
        }
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
