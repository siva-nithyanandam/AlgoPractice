package recursion;

/**
 * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents),
 * and pennies (1 cent), write code to calculate the number of ways of representing n cents.
 */
public class Coins {
    public static void main(String[] args) {
        System.out.println(findNbrOfWays(10));
        System.out.println(findNbrOfWays(15));
    }

    private static int findNbrOfWays(int n) {
        int count = 0;
        for (int c25 = 0; c25 <= n; c25 += 25) {
            for (int c10 = c25; c10 <= n; c10 += 10) {
                for (int c5 = c10; c5 <= n; c5 += 5) {
                    if (c5 <= n) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
