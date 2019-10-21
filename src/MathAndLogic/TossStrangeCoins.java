package MathAndLogic;

/**
 * You have some coins.  The i-th coin has a probability prob[i] of facing heads when tossed.
 *
 * Return the probability that the number of coins facing heads equals target if you toss every coin exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: prob = [0.4], target = 1
 * Output: 0.40000
 * Example 2:
 *
 * Input: prob = [0.5,0.5,0.5,0.5,0.5], target = 0
 * Output: 0.03125
 *
 *
 * Constraints:
 *
 * 1 <= prob.length <= 1000
 * 0 <= prob[i] <= 1
 * 0 <= target <= prob.length
 * Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 */
public class TossStrangeCoins {
    public static void main(String[] args) {
        TossStrangeCoins o = new TossStrangeCoins();
        System.out.println(o.probabilityOfHeads(new double[]{0.4, 0.4, 0.4}, 1));
    }

    public double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        double[][] p = new double[1+n][1+n];
        p[0][0] = 1;
        for(int i = 0; i< n; i++){
            for(int j = 0; j<= n; j++){
                p[i+1][j] += p[i][j]*(1-prob[i]);
                if(j < n)p[i+1][j+1] += p[i][j]*prob[i];
            }
        }
        return p[n][target];
    }
}
