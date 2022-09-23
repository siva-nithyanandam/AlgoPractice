package StacksAndQueues;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/20/2021  2:28 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator/
 *
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * Example 4:
 *
 * Input: s = "+48 + -48"
 * Output: 0
 * Explanation: Numbers can have multiple digits and start with +/-.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 * Every number and running calculation will fit in a signed 32-bit integer.
 */
public class BasicCalculator {

  public static void main(String[] args) {
    BasicCalculator o = new BasicCalculator();
    System.out.println(o.calculate_faster("(1+(4+5+2)-3)+(6+8)"));
    System.out.println(o.calculate_faster("1 + 1"));
    System.out.println(o.calculate_faster("48 + -48"));
    System.out.println(o.calculate_faster(" 2-1 + 2 "));
  }

  public int calculate_faster(String s) {

    Stack<Integer> stack = new Stack<Integer>();
    int operand = 0;
    int result = 0; // For the on-going result
    int sign = 1;  // 1 means positive, -1 means negative

    for (int i = 0; i < s.length(); i++) {

      char ch = s.charAt(i);
      if (Character.isDigit(ch)) {

        // Forming operand, since it could be more than one digit
        operand = 10 * operand + (int) (ch - '0');

      } else if (ch == '+') {

        // Evaluate the expression to the left,
        // with result, sign, operand
        result += sign * operand;

        // Save the recently encountered '+' sign
        sign = 1;

        // Reset operand
        operand = 0;

      } else if (ch == '-') {

        result += sign * operand;
        sign = -1;
        operand = 0;

      } else if (ch == '(') {

        // Push the result and sign on to the stack, for later
        // We push the result first, then sign
        stack.push(result);
        stack.push(sign);

        // Reset operand and result, as if new evaluation begins for the new sub-expression
        sign = 1;
        result = 0;

      } else if (ch == ')') {

        // Evaluate the expression to the left
        // with result, sign and operand
        result += sign * operand;

        // ')' marks end of expression within a set of parenthesis
        // Its result is multiplied with sign on top of stack
        // as stack.pop() is the sign before the parenthesis
        result *= stack.pop();

        // Then add to the next operand on the top.
        // as stack.pop() is the result calculated before this parenthesis
        // (operand on stack) + (sign on stack * (result from parenthesis))
        result += stack.pop();

        // Reset the operand
        operand = 0;
      }
    }
    return result + (sign * operand);
  }

  public int evaluateExpr(Stack<Object> stack) {

    int res = 0;

    if (!stack.empty()) {
      res = (int) stack.pop();
    }

    // Evaluate the expression till we get corresponding ')'
    while (!stack.empty() && !((char) stack.peek() == ')')) {

      char sign = (char) stack.pop();

      if (sign == '+') {
        res += (int) stack.pop();
      } else {
        res -= (int) stack.pop();
      }
    }
    return res;
  }

  public int calculate(String s) {

    int operand = 0;
    int n = 0;
    Stack<Object> stack = new Stack<Object>();

    for (int i = s.length() - 1; i >= 0; i--) {

      char ch = s.charAt(i);

      if (Character.isDigit(ch)) {

        // Forming the operand - in reverse order.
        operand = (int) Math.pow(10, n) * (int) (ch - '0') + operand;
        n += 1;

      } else if (ch != ' ') {
        if (n != 0) {

          // Save the operand on the stack
          // As we encounter some non-digit.
          stack.push(operand);
          n = 0;
          operand = 0;

        }
        if (ch == '(') {

          int res = evaluateExpr(stack);
          stack.pop();

          // Append the evaluated result to the stack.
          // This result could be of a sub-expression within the parenthesis.
          stack.push(res);

        } else {
          // For other non-digits just push onto the stack.
          stack.push(ch);
        }
      }
    }

    //Push the last operand to stack, if any.
    if (n != 0) {
      stack.push(operand);
    }

    // Evaluate any left overs in the stack.
    return evaluateExpr(stack);
  }
}
