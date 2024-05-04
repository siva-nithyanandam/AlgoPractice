package SearchAndSorting;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author sivaprakashnithyanandam
 * @time 4/13/24-9:01 AM
 */

public class SubstringSearch {

    public static void main(String[] args) {
        SubstringSearch o = new SubstringSearch();
        System.out.println(o.matchSubString_KMP_OofMplusN("abcxabcdabcdabcy", "abcdabcy"));//true
        System.out.println(o.matchSubString_bruteForce_OofMN("abcxabcdabcdabcy", "abcdabcy"));//true
        System.out.println(o.matchSubString_bruteForce_OofMN("abcxabcdabcdabcy", "abcdabct"));//false
    }

    /**
     * Compute temporary array to maintain size of suffix which is same as prefix
     * Time/space complexity is O(size of pattern)
     */
    private int[] computeTemporaryArray(char pattern[]){

        int index = 0;
        int[] lps = new int[pattern.length];

        for (int j = 1; j < pattern.length;) {
            if (pattern[j] == pattern[index]) {
                lps[j] = index+1;
                index++;
                j++;
            } else {
                if (index != 0) {
                    index = lps[index-1];
                } else {
                    j++;
                }
            }
        }
        return lps;
    }

    /**
     * Knuth Morris Pratt(KMP) Pattern
     * @param input
     * @param pattern
     * @return
     */
    private boolean matchSubString_KMP_OofMplusN(String input, String pattern) {

        int lps[] = computeTemporaryArray(pattern.toCharArray());
        System.out.println(Arrays.toString(lps));

        // "a b c x a b c d a b c d a b c y",
        // "a b c d a b c y"
        //  0 0 0 0 1 2 3 0

        int i = 0, p = 0;

        while (i < input.length() && p < pattern.length()) {
            if (input.charAt(i) == pattern.charAt(p)) {
                i++;
                p++;
            } else {
                if (p != 0) {
                    p = lps[p-1];
                } else {
                    i++;
                }
            }
        }
        return p == pattern.length();
    }

    private boolean matchSubString_bruteForce_OofMN(String input, String pattern) {

        if (input == null || pattern == null || input.length() < pattern.length()) {
            return false;
        }

        int n = input.length();
        int i = 0, j = 0, k = 0;

        while (i < n && j != pattern.length()) {
            if (input.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = 0;
                k++;
                i = k;
            }
        }
        return j == pattern.length();
    }
}
