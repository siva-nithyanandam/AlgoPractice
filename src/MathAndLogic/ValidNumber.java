package MathAndLogic;

/**
 * Validate if a given string can be interpreted as a decimal number.
 *
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 *
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:
 *
 * Numbers 0-9
 * Exponent - "e"
 * Positive/negative sign - "+"/"-"
 * Decimal point - "."
 * Of course, the context of these characters also matters in the input.
 */
public class ValidNumber {
    public static void main(String[] args) {
        ValidNumber validNumber = new ValidNumber();
        System.out.println(validNumber.isNumber("0"));
        System.out.println(validNumber.isNumber(" 0.1"));
        System.out.println(validNumber.isNumber("2e10"));
        System.out.println(validNumber.isNumber(" 6e-1"));
        System.out.println(validNumber.isNumber("53.5e93"));
        System.out.println(validNumber.isNumber(" -90e3   "));
        System.out.println(validNumber.isNumber(".1"));
        System.out.println(validNumber.isNumber(" 005047e+6"));

        System.out.println("----FALSE----");

        System.out.println(validNumber.isNumber("."));
        System.out.println(validNumber.isNumber("-."));
        System.out.println(validNumber.isNumber(".e1"));
        System.out.println(validNumber.isNumber("abc"));
        System.out.println(validNumber.isNumber("1 a"));
        System.out.println(validNumber.isNumber(" 1e"));
        System.out.println(validNumber.isNumber("e3"));
        System.out.println(validNumber.isNumber(" 99e2.5 "));
        System.out.println(validNumber.isNumber(" --6 "));
        System.out.println(validNumber.isNumber("-+3"));
        System.out.println(validNumber.isNumber("95a54e53"));
        System.out.println(validNumber.isNumber(" +0e-"));
        System.out.println(validNumber.isNumber("6+1"));
        System.out.println(validNumber.isNumber(".-4"));
    }

    public boolean isNumber(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        str = str.trim();

        boolean canBeSign = true;
        boolean canBeDot = true;
        boolean hasNumber = false;
        boolean hasEpsilon = false;

        for (int i = 0; i < str.length(); i++) {
            switch(str.charAt(i)) {
                case '+':
                case '-':
                    if (canBeSign) {
                        canBeSign = false;
                    } else {
                        return false;
                    }
                    break;
                case '.':
                    if (canBeDot) {
                        canBeDot = false;
                    } else {
                        return false;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    hasNumber = true;
                    canBeSign = false;
                    break;
                case 'e':
                    if (!hasNumber || hasEpsilon) {
                        return false;
                    }
                    hasEpsilon = true;
                    hasNumber = false;
                    canBeSign = true;
                    canBeDot = false;
                    break;
                default:
                    return false;
            }
        }
        return hasNumber;
    }


    public boolean isNumber_own(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        str = str.trim();
        int index = 0;
        char[] arr = str.toCharArray();
        //check for + or -
        if (arr[index] == '+' || arr[index] == '-') {
            index++;
        }

        boolean isDotted = false;
        boolean isNumbered = false;
        //check for numbers, decimal and exponents
        while (index < arr.length) {
            if (arr[index] >= '0' && arr[index] <= '9') {
                index++;
                isNumbered = true;
                continue;
            } else if (arr[index] == '.') {
                if (isDotted) {
                    return false;
                }
                isDotted = true;
                index++;
            } else if (arr[index] == 'e' && isNumbered) {
                return isValidExponent(arr, index+1);
            } else {
                return false;
            }
        }
        return isNumbered;
    }

    private boolean isValidExponent(char[] arr, int index) {
        if (index == 1 || index >= arr.length) {
            return false;
        }
        if (arr[index] == '-' || arr[index] == '+') {
            index++;
        }
        boolean isNumbered = false;
        while (index < arr.length) {
            if (arr[index] >= '0' && arr[index] <= '9') {
                index++;
                isNumbered = true;
            } else {
                return false;
            }
        }
        return isNumbered;
    }
}
