package MathAndLogic;

/**
 * Given a boolean expression consisting of the symbols 0 (false), 1 (true), &
 * (AND), I (OR), and" (XOR), and a desired boolean result value result, implement a function to
 * count the number of ways of parenthesizing the expression such that it evaluates to result.
 * EXAMPLE
 * countEval("1^0|0|1", false) -> 2
 * countEval("0&0&0&1^1|0", true) -> 10
 */

/**
 * Its better to look at the theory before diving to solution.
 * https://www.geeksforgeeks.org/boolean-parenthesization-problem-dp-37/
 * https://prismoskills.appspot.com/lessons/Dynamic_Programming/Chapter_27_-_Max_ways_for_boolean_expression_to_be_true.jsp
 * https://www.youtube.com/watch?v=oyj9tRZhmis
 */
public class BooleanEvaluation {

    public static void main(String[] args) {
        System.out.println(countEval("1^0|0|1", false));
        System.out.println(countEval("0&0&0&1^1|0", true));
        System.out.println(countEval("1|1&0^1", true));
    }

    private static int countEval(String exp, boolean res) {
        int[] operands = new int[(exp.length()/2)+1];
        char[] operators = new char[exp.length()/2];

        for (int i = 0; i < exp.length(); i++) {
            // Split operands and operators separately.
            if ((i & 1) == 0) {
                operands[i/2] = exp.charAt(i) - '0';
            } else {
                operators[i/2] = exp.charAt(i);
            }
        }
        return countEval(operands, operators, res);
    }

    private static int countEval(int[] operands, char[] operators, boolean res) {
        int n = operands.length;
        int[][] T = new int[n][n];
        int[][] F = new int[n][n];

        for (int i = 0; i < n; i++) {
            T[i][i] = operands[i];
            F[i][i] = operands[i] ^ 1;
        }

        //"1 ^ 0 | 0 | 1"
        for (int gap = 1; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                for (int g = 0; g < gap; g++) {
                    int k = i + g;
                    switch(operators[k]) {
                        case '^':
                            T[i][j] += (T[i][k] * F[k+1][j]) + (F[i][k] * T[k+1][j]);
                            F[i][j] += (T[i][k] * T[k+1][j]) + (F[i][k] * F[k+1][j]);
                            break;
                        case '|':
                            T[i][j] += (T[i][k] * T[k+1][j]) + (T[i][k] * F[k+1][j]) + (F[i][k] * T[k+1][j]);
                            F[i][j] += (F[i][k] * F[k+1][j]);
                            break;
                        case '&':
                            T[i][j] += (T[i][k] * T[k+1][j]);
                            F[i][j] += (F[i][k] * F[k+1][j]) + (T[i][k] * F[k+1][j]) + (F[i][k] * T[k+1][j]);
                            break;
                    }
                }
            }
        }
        if (res) {
            return T[0][n-1];
        } else {
            return F[0][n-1];
        }
    }
}