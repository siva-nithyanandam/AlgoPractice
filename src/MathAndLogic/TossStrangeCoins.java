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
//        System.out.println(o.probabilityOfHeads_faster(new double[]{0.6, 0.8, 0.4, 0.3, 0.5}, 2));
//        System.out.println(o.probabilityOfHeads(new double[]{0.5,0.5,0.5,0.5,0.5}, 0));
//        System.out.println(o.probabilityOfHeads(new double[]{0.5,0.5,0.5}, 2));
        System.out.println(o.probabilityOfHeads_faster(new double[]{0.6, 0.8, 0.4, 0.3, 0.5}, 2));
        System.out.println(o.probabilityOfHeads_own(new double[]{0.6, 0.8, 0.4, 0.3, 0.5}, 2));
    }

    public double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        double[][] arr = new double[n + 1][target + 1];
        arr[0][0] = 1;

        for (int tar = 0; tar < target + 1; tar++) {
            for (int toss = 0; toss < n; toss++) {
                arr[toss + 1][tar] += arr[toss][tar] * (1 - prob[toss]);
                if (tar < target) {
                    arr[toss + 1][tar + 1] += arr[toss][tar] * (prob[toss]);
                }
            }
        }
        return arr[n][target];
    }

    public double probabilityOfHeads_own(double[] prob, int target) {
        int n = prob.length;
        double[][] res = new double[prob.length+1][target+1];
        res[0][0] = 1;
        for (int i = 1; i <= prob.length; i++) {
            res[i][0] = res[i-1][0] * (1 - prob[i-1]);
        }
        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= prob.length; j++) {
                res[j][i] = (res[j-1][i-1] * prob[j-1]) + (res[j-1][i] * (1 - prob[j-1]));
            }
        }
        return res[prob.length][target];
    }

    public double probabilityOfHeads_faster(double[] prob, int target) {
        int n = prob.length;
        double[] array = new double[target + 1];
        array[0] = 1;
        for (int toss = 0; toss < n; toss++) {
            for (int tar = Math.min(target, toss + 1); tar > 0; tar--) {
                array[tar] = (array[tar] * (1 - prob[toss])) + (array[tar - 1] * prob[toss]);
            }
            array[0] *= 1 - prob[toss];
        }
        return array[target];
    }


    public double probabilityOfHeads1(double[] prob, int target) {
        int n = prob.length;
        double[][] p = new double[1 + n][1 + n];
        p[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                p[i + 1][j] += p[i][j] * (1 - prob[i]);
                if (j < n) {
                    p[i + 1][j + 1] += p[i][j] * prob[i];
                }
            }
        }
        return p[n][target];
    }
}
