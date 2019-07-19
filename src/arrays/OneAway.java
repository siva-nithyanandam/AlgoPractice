package arrays;

/**
 * There are three types of edit that can be performed on strings:
 * Insert, remove or replace a character. Given two strings, write a function to
 * check if they are one edit(or zero edits) away.
 * Example:
 * pale, ple -> true
 * pales, pale -> true
 * pale, bale -> true
 * pale, bake -> false
 */
public class OneAway {

    public static void main(String[] args) {
        System.out.println(isOneAway("pale", "ple"));
        System.out.println(isOneAway("pales", "pale"));
        System.out.println(isOneAway("pale", "bale"));
        System.out.println(isOneAway("paless", "ple"));
        System.out.println(isOneAway("pale", "bake"));
        System.out.println(isOneAway("bakee", "pale"));
        System.out.println(isOneAway("pales", "bale"));
    }

    /**
     * Compare at same level of index when lengths are equal,
     * Increment index of max length string while other one as same when
     * there is a difference in given string lengths.
     * Do this until index reaches any one of the string length.
     * @param s1
     * @param s2
     * @return
     */
    private static boolean isOneAway(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (Math.abs(m-n) > 1) {
            return false;
        }
        int i = 0, j = 0, count = 0;
        while((i < m) && (j < n)) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            }
            else {
                if (m == n) {
                    i++;
                    j++;
                }
                else if (m > n) {
                    i++;
                }
                else {
                    j++;
                }
                count++;
            }
        }
        return count <= 1;
    }
}
