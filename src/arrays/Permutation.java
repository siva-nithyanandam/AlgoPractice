package arrays;

import java.util.Arrays;

/**
 * Print all permutations of a string
 */
public class Permutation {
    public static void main(String[] args) {
        String s = "ABC";
        printAllPermutations(s);
    }

    private static void printAllPermutations(String s) {
        printAllPermutations(s.toCharArray(), new StringBuilder());
    }

    private static void printAllPermutations(char[] str, StringBuilder ans) {
        if (str.length == 0) {
            System.out.println(ans.toString());
            ans.delete(0, ans.length() - 1);
            return;
        }
        for(int i = 0; i < str.length; i++) {
            ans.append(str[i]);
            char[] a = new char[str.length - 1];
            System.arraycopy(str, 0, a, 0, i);
            System.arraycopy(str, i+1, a, i, str.length - i - 1);
            printAllPermutations(a, ans);
        }
    }
}
