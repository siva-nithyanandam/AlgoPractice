package arrays;

import java.util.Arrays;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Note:
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
 * Example 1:
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 * Input: ["4", "13", "5", "/", "+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * Output: 22
 * Explanation:
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class EvaluateReversePolishNotation {

  public static void main(String[] args) {
    EvaluateReversePolishNotation o = new EvaluateReversePolishNotation();
    System.out.println(o.evalRPN(new String[]{"18"}));
    System.out.println(o.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
    System.out.println(o.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    System.out.println(o.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
  }

  public int evalRPN(String[] tokens) {
    return recurseEval(tokens, new int[] {tokens.length - 1});
  }

  private int recurseEval(String[] tokens, int[] index) {
    final String operator = tokens[index[0]];
    index[0]--;
    int operand1;
    int operand2;
    switch (operator) {
      case "+":
        operand2 = recurseEval(tokens, index);
        operand1 = recurseEval(tokens, index);
        return operand1 + operand2;
      case "-":
        operand2 = recurseEval(tokens, index);
        operand1 = recurseEval(tokens, index);
        return operand1 - operand2;
      case "*":
        operand2 = recurseEval(tokens, index);
        operand1 = recurseEval(tokens, index);
        return operand1 * operand2;
      case "/":
        operand2 = recurseEval(tokens, index);
        operand1 = recurseEval(tokens, index);
        return operand1 / operand2;
      default:
        return Integer.parseInt(operator);
    }
  }

  public int evalRPN_own(String[] tokens) {
    if (tokens.length == 0) {
      return 0;
    } else if (tokens.length == 1) {
      return Integer.parseInt(tokens[0]);
    }
    String operators = "+-*/";
    int[] valArr = new int[tokens.length];
    int[] posArr = new int[tokens.length];
    Arrays.fill(posArr, -1);

    int i = 0;
    int j = 1;
    valArr[i] = Integer.parseInt(tokens[i]);
    valArr[j] = Integer.parseInt(tokens[j]);
    for (int k = 2; k < tokens.length; k++) {
      if (operators.contains(tokens[k])) {
        switch (tokens[k]) {
          case "+":
            valArr[k] = valArr[i] + valArr[j];
            break;
          case "-":
            valArr[k] = valArr[i] - valArr[j];
            break;
          case "*":
            valArr[k] = valArr[i] * valArr[j];
            break;
          case "/":
            valArr[k] = valArr[i] / valArr[j];
            break;
        }
        j = k;
        i = posArr[i];
      } else {
        posArr[j] = i;
        i = j;
        j++;
        valArr[j] = Integer.parseInt(tokens[j]);
      }
    }
    return valArr[j];
  }
}
