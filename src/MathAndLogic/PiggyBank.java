package MathAndLogic;

import java.util.Scanner;

/**
 * Before ACM can do anything, a budget must be prepared and the necessary financial support obtained.
 * The main income for this action comes from Irreversibly Bound Money (IBM).
 * The idea behind is simple. Whenever some ACM member has any small money, he takes all the coins
 * and throws them into a piggy-bank.
 * You know that this process is irreversible, the coins cannot be removed without breaking the pig.
 * After a sufficiently long time, there should be enough cash in the piggy-bank to pay
 * everything that needs to be paid.
 *
 * But there is a big problem with piggy-banks. It is not possible to determine how much money is
 * inside. So we might break the pig into pieces only to find out that there is not enough money.
 * Clearly, we want to avoid this unpleasant situation. The only possibility is to weigh the
 * piggy-bank and try to guess how many coins are inside. Assume that we are able to determine the
 * weight of the pig exactly and that we know the weights of all coins of a given currency.
 * Then there is some minimum amount of money in the piggy-bank that we can guarantee.
 * Your task is to find out this worst case and determine the minimum amount of cash inside
 * the piggy-bank. We need your help. No more prematurely broken pigs!
 *
 * Input
 * The input consists of T test cases.
 * The number of them (T) is given on the first line of the input file.
 * Each test case begins with a line containing two integers E and F. They indicate the weight of
 * an empty pig and of the pig filled with coins. Both weights are given in grams.
 *
 * No pig will weigh more than 10 kg, that means 1 <= E <= F <= 10000.
 *
 * On the second line of each test case, there is an integer number N (1 <= N <= 500)
 * that gives the number of various coins used in the given currency.
 * Following this are exactly N lines, each specifying one coin type.
 * These lines contain two integers each, P and W (1 <= P <= 50000, 1 <= W <=10000).
 * P is the value of the coin in monetary units, W is it's weight in grams.
 *
 * Output
 * Print exactly one line of output for each test case. The line must contain the sentence
 * "The minimum amount of money in the piggy-bank is X." where X is the minimum amount of
 * money that can be achieved using coins with the given total weight.
 * If the weight cannot be reached exactly, print a line "This is impossible.".
 */

/**
 * This is so simple, but the description is lengthy.
 * In short, Piggy bank has initial weight and full weight when its filled with coins.
 * Put some coins inside where you know the exact coin weight and value.
 * Sum the initial piggy bank weight and total coins weight. If its less than full piggy bank weight, that is possible
 * and return total coin value.
 * If its greater than full piggy bank, return "That is impossible".
 */

/**
 * Sample input and respective for below.
 * Total test cases?
 * 2
 * Empty piggy bank weight and Full piggy bank weight?
 * 10 100
 * Number of various coins?
 * 2
 * Coin value and weight?
 * 20 20
 * Coin value and weight?
 * 50 20
 * -------------------
 * Empty piggy bank weight and Full piggy bank weight?
 * 10 100
 * Number of various coins?
 * 2
 * Coin value and weight?
 * 100 50
 * Coin value and weight?
 * 200 50
 * -------------------
 * The minimum amount of money in the piggy-bank is 70.
 * This is impossible.
 */
public class PiggyBank {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] output;

        //The input consists of T test cases.
        System.out.println("Total test cases?");
        int totalTc = scanner.nextInt();

        //Number of testcase == number of outputs
        output = new String[totalTc];

        for (int i = 0; i < totalTc; i++) {
            //a line containing two integers E and F.
            // They indicate the weight of an empty pig and of the pig filled with coins
            System.out.println("Empty piggy bank weight and Full piggy bank weight?");
            int emptyPigWt = scanner.nextInt();
            int fullPigWt = scanner.nextInt();

            //N (1 <= N <= 500) that gives the number of various coins used in the given currency.
            System.out.println("Number of various coins?");
            int nbrOfCoins = scanner.nextInt();

            //Store coin properties in an array
            int[][] coinProp = new int[nbrOfCoins][2];

            for (int j = 0; j < nbrOfCoins; j++) {
                //P and W (1 <= P <= 50000, 1 <= W <=10000).P is the value of the coin in monetary units,
                // W is it's weight in grams

                System.out.println("Coin value and weight?");
                //coinProp[j][0] stores coin value
                coinProp[j][0] = scanner.nextInt();

                //coinProp[j][1] stores coin weight
                coinProp[j][1] = scanner.nextInt();
            }

            //Collected all the inputs for a particular test case.
            //Solve this and store this in output array
            int totalCoinValue = findPigBankValue(emptyPigWt, fullPigWt, coinProp);
            if (totalCoinValue > 0) {
                output[i] = "The minimum amount of money in the piggy-bank is " + totalCoinValue + ".";
            } else {
                output[i] = "This is impossible.";
            }
            //Completed a test case here.
            System.out.println("-------------------");
        }

        //Here, Completed all the test cases. Print the outputs
        for (String s : output) {
            System.out.println(s);
        }

    }

    private static int findPigBankValue(int emptyPigWt, int fullPigWt, int[][] coinProp) {
        //When (empty piggy bank weight + all coins weights) <= full piggy bank weight, then calculate total coin value
        //When (empty piggy bank weight + all coins weights) > full piggy bank weight, that is not possible.

        int totalWtSoFar = emptyPigWt;
        int totalCoinValueSoFar = 0;

        for (int i = 0; i < coinProp.length; i++) {
            totalWtSoFar = totalWtSoFar + coinProp[i][1];
            totalCoinValueSoFar = totalCoinValueSoFar + coinProp[i][0];
        }

        if (totalWtSoFar <= fullPigWt) {
            return totalCoinValueSoFar;
        } else {
            return -1;
        }
    }
}
