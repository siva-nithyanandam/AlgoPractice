package arrays;

/**
 * Assume you have a method isSubString which checks if one word is substring of another.
 * Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only
 * one call to isSubstring(Eg,. "waterbottle" is a rotation of "erbottlewat")
 */
public class StringRotation {

    public static void main(String[] args) {
        System.out.println(isSubstring("waterbottle", "erbottlewat"));
        System.out.println(isSubstring("labba", "bbala"));
        System.out.println(isSubstring("labbal", "bbala"));

        /**
         * Result:
         * true
         * true
         * false
         */
    }

    private static boolean isSubstring(String s1, String s2) {
        String s3 = s1+s1;
        return s3.contains(s2);
    }
}
