package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Write a method to compute all permutations of a string whose characters are not necessarily unique.
 * The list of permutations should not have duplicates.
 */

/**
 * Tricky logic. Go through step by step.
 */
public class PermutationsWithDups {
    public static void main(String[] args) {
        String str = "sstse";
        List<String> res = permute(str);
        for (String s : res) {
            System.out.println(s);
        }
    }

    private static List<String> permute(String str) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        boolean[] readState = new boolean[arr.length];
        int[] pos = new int[arr.length];
        List<String> res = new ArrayList<>();
        permuteHelper(arr, readState, pos, 0, res);
        return res;
    }

    private static void permuteHelper(char[] arr, boolean[] readState, int[] pos,
                                      int count, List<String> res) {
        if (count == arr.length) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < arr.length; i++) {
                sb.append(arr[pos[i]]);
            }
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (readState[i]) {
                continue;
            }
            if (i > 0 && arr[i] == arr[i-1] && !readState[i-1]) {
                continue;
            }
            readState[i] = true;
            pos[count] = i;
            permuteHelper(arr, readState, pos, count+1, res);
            readState[i] = false;
        }
    }
}
