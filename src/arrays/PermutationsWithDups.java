package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a method to compute all permutations of a string of unique characters.
 */
public class PermutationsWithDups {
    public static void main(String[] args) {
        String str = "aab";
        List<String> res = permute(str);
        for (String s : res) {
            System.out.println(s);
        }
    }

    private static void permute1(String s) {
        printAllPermutations(s.toCharArray(), new StringBuilder());
    }

    private static void printAllPermutations(char[] str, StringBuilder ans) {
        if (str.length == 0) {
            System.out.println(ans.toString());
            return;
        }
        for (int i = 0; i < str.length; i++) {
            ans.append(str[i]);
            char[] a = new char[str.length - 1];
            System.arraycopy(str, 0, a, 0, i);
            System.arraycopy(str, i + 1, a, i, str.length - i - 1);
            printAllPermutations(a, ans);
            ans.deleteCharAt(ans.length() - 1);
        }
    }

    private static List<String> permute(String str) {
        char[] arr = str.toCharArray();
        List<String> res = new ArrayList<>();
        permuteHelper(arr, 0, res);
        return res;
    }

    private static void permuteHelper(char[] arr, int start, List<String> res) {
        if (start == arr.length) {
            res.add(new String(arr));
        }
        for (int i = start; i < arr.length; i++) {
            swap(arr, i, start);
            permuteHelper(arr, start + 1, res);
            swap(arr, start, i);
        }
    }

    private static void swap(char[] arr, int x, int y) {
        char temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
