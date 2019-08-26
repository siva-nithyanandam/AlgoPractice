package MathAndLogic;

/**
 * Write a method to return all subsets of a set.
 */

/**
 * Using binary concept. Use 1 as an intermediater to AAND with possibilities.
 */
public class PowerSet {
    public static void main(String[] args) {
        char[] set = {'a', 'b', 'c'};
        String[] powerSets = getPowerSets(set);
        for (String s : powerSets) {
            System.out.println(s);
        }
    }

    private static String[] getPowerSets(char[] nums) {
        int possibilities = (int)Math.pow(2, nums.length);
        String[] s = new String[possibilities];
        for (int i = 0; i < possibilities; i++) {
            s[i] = "";
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    s[i] += nums[j];
                }
            }
        }
        return s;
    }
}
