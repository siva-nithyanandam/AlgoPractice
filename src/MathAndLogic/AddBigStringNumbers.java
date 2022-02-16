package MathAndLogic;

/**
 * Design & Implement a BigInteger string summation.
 *
 * Add BigInteger string ("123243343434432234325324234234" + "12132123")
 */
public class AddBigStringNumbers {

    public static void main(String[] args) {

    }

    public String addStringNumbers(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        //Base Conditions
        if (s1 == null || s2 == null) {
            return null;
        } else if (s1.length() == 0) {
            return s2;
        } else if (s2.length() == 0) {
            return s1;
        }

        //Identify the operation
        char c1 = s1.charAt(0);
        char c2 = s2.charAt(0);
        char oprSign = '+';
        char finalSign = '+';
        if (c1 == '-' ^ c2 == '-') {
            oprSign = '-';
        }
        if (c1 == '-' && c2 == '-') {
            finalSign = '-';
        }

        //Do the opearion
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int i = chars1.length-1;
        int j = chars2.length-1;
        int carrySign = '+';
        int carry = 0;

        while (i >= 0 && j >= 0) {
            if (!Character.isDigit(chars1[i]) || !Character.isDigit(chars2[j])) {
                return "Invalid number";
            }
            int x = chars1[i] - '0';
            int y = chars2[j] - '0';
            int z;
            if (oprSign == '+') {
                z = x + y + carry;
                carry = z / 10;
                z = z % 10;
            } else {
                z = x - y;
                if (z < 0) {
                    carrySign = '-';
                    z = Math.abs(z);
                }
            }
            sb.insert(0, z);
            i--;
            j--;
        }
        //Take care of rest overs from s1 and s2
        while (i >= 0) {
            int z = (int)(chars1[i] - '0') + carry;
            carry = z / 10;
            z = z % 10;
            sb.insert(0, z);
            i--;
        }
        while (j >= 0) {
            int z = (int)(chars2[j] - '0') + carry;
            carry = z / 10;
            z = z % 10;
            sb.insert(0, z);
            j--;
        }

        //Deciding final sign
        if (finalSign == '-' || carrySign == '-') {
            sb.insert(0, '-');
        }

        //Return final answer
        return sb.toString();
    }
}
