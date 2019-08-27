package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a method to compute all permutations of a string of unique characters.
 */
public class PermutationsWithoutDups {
    public static void main(String[] args) {
        String str = "sstse";
        List<String> res = permute(str);
        for (String s : res) {
            System.out.println(s);
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
