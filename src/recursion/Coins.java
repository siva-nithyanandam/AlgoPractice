package recursion;

/**
 * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents),
 * and pennies (1 cent), write code to calculate the number of ways of representing n cents.
 */
public class Coins {
    public static void main(String[] args) {
        System.out.println(findNbrOfWays(10));
    }

    private static int findNbrOfWays(int n) {
        return findNbrOfWays(n, 0);
    }

    private static int findNbrOfWays(int n, int tot) {
        int sum = 0;
        if (tot == n) {
            return 1;
        } else if (tot > n) {
            return 0;
        } else {
            sum += findNbrOfWays(n, tot + 25);
            sum += findNbrOfWays(n, tot + 10);
            sum += findNbrOfWays(n, tot + 5);
            sum += findNbrOfWays(n, tot + 1);
        }
        return sum;
    }
}
