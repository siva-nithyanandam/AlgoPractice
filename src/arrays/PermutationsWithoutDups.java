package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Write a method to compute all permutations of a string whose characters are not necessarily unique.
 * The list of permutations should not have duplicates.
 */

/**
 * Tricky logic. Go through step by step.
 */
public class PermutationsWithoutDups {
    public static void main(String[] args) {
        String str = "aab";
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

    public List<String> permute(char input[]) {
        Map<Character, Integer> countMap = new TreeMap<>();
        for (char ch : input) {
            countMap.compute(ch, (key, val) -> {
                if (val == null) {
                    return 1;
                } else {
                    return val + 1;
                }
            });
        }
        char str[] = new char[countMap.size()];
        int count[] = new int[countMap.size()];
        int index = 0;
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            str[index] = entry.getKey();
            count[index] = entry.getValue();
            index++;
        }
        List<String> resultList = new ArrayList<>();
        char result[] = new char[input.length];
        permuteUtil(str, count, result, 0, resultList);
        return resultList;
    }

    public void permuteUtil(char str[], int count[], char result[], int level, List<String> resultList) {
        if (level == result.length) {
            resultList.add(new String(result));
            return;
        }

        for(int i = 0; i < str.length; i++) {
            if(count[i] == 0) {
                continue;
            }
            result[level] = str[i];
            count[i]--;
            permuteUtil(str, count, result, level + 1, resultList);
            count[i]++;
        }
    }

    private void printArray(char input[]) {
        for(char ch : input) {
            System.out.print(ch);
        }
        System.out.println();
    }
}
