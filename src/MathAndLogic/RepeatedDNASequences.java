package MathAndLogic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T,
 * for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to
 * identify repeated sequences within the DNA.
 *
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more
 * than once in a DNA molecule.
 *
 * Example:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */

/**
 * TODO Analyse the first solution.
 */
public class RepeatedDNASequences {
    public static void main(String[] args) {
        RepeatedDNASequences o = new RepeatedDNASequences();
        List<String> res;
        res = o.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        printRes(res);

        res = o.findRepeatedDnaSequences("AAAAAAAAAAA");
        printRes(res);

        res = o.findRepeatedDnaSequences("AAAAAAAAAAAA");
        printRes(res);
    }

    private static void printRes(List<String> res) {
        for (String s : res) {
            System.out.println(s);
        }
    }

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ret = new ArrayList<>();
        int len = s.length();
        if (len < 11) {
            return ret;
        }
        int[] bitLabel = new int[255];
        bitLabel['A'] = 0;
        bitLabel['C'] = 1;
        bitLabel['G'] = 2;
        bitLabel['T'] = 3;
        int[] nums = new int[len];
        int[] exist = new int[1048576];
        char[] schars = s.toCharArray();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <= i; j++) {
                nums[j] <<= 2;
                nums[j] |= bitLabel[schars[i]];
            }
        }
        for (int i = 9; i < len; i++) {
            for (int j = i - 9; j <= i; j++) {
                nums[j] <<= 2;
                nums[j] |= bitLabel[schars[i]];
            }
            if (exist[nums[i - 9]]++ == 1) {
                ret.add(s.substring(i - 9, i + 1));
            }
        }
        return ret;
    }

    public List<String> findRepeatedDnaSequences_1(String s) {
        List<String> list = new ArrayList<>();

        if(s == null || s.length() < 10)
            return list;

        char[] C = s.toCharArray();
        byte[] code = new byte[26];
        code['C' - 'A'] = 1;
        code['G' - 'A'] = 2;
        code['T' - 'A'] = 3;
        byte [] seen = new byte[1 << 20];
        int n = 0;
        for (int j = 0; j < 10; ++j)
            n |= (code[C[j] - 'A'] << (2 * j));
        seen[n] = 1;
        for (int i = 1; i + 9 < s.length(); ++i)
        {
            n = n>>>2;
            n|=(code[C[i+9]-'A'] << (18));
            if (seen[n] == 0)
                seen[n] = 1;
            else if(seen[n] == 1)
            {
                seen[n] = 2;
                list.add(new String(C, i, 10));
            }
        }
        return list;
    }

    public List<String> findRepeatedDnaSequences_own(String s) {
        List<String> list = new ArrayList<>();
        if (s == null || s.length() < 10) {
            return list;
        }
        StringBuilder sb = new StringBuilder(s);
        Set<String> set = new HashSet<>();
        int i = 10;
        String ss;
        while (i <= s.length()) {
            ss = sb.substring(i-10, i);
            if (!set.add(ss)) {
                if (!list.contains(ss)) {
                    list.add(ss);
                }
            }
            i++;
        }
        return list;
    }
}
