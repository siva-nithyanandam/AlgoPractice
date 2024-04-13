package MathAndLogic;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 * Oct 15,2023 - 8:35 PM
 */

public class ConvertFromOneBaseToAnother {

    public static void main(String[] args) {
        ConvertFromOneBaseToAnother o = new ConvertFromOneBaseToAnother();
        System.out.println(baseToBase("12345", 10, 2));
        System.out.println(baseToBase("11000000111001", 2, 10));
        System.out.println(baseToBase("ABC11", 16, 2));
        System.out.println(baseToBase("10101011110000010001", 2, 16));
        System.out.println(baseToBase("12322", 8, 16));
        System.out.println();
    }

    static String baseToBase(String num, int base1, int base2) {
        int no = convFrmBaseToDeci(num, base1);
        return convFrmDecToBase(no, base2);
    }

    static String convFrmDecToBase(int num, int base) {

        String res = "";
        int rem;
        // Convert input number is given base by repeatedly
        // dividing it by base and taking remainder
        while (num > 0) {
            rem = num % base;
            if (base == 16) {
                if (rem == 10)
                    res += 'A';
                else if (rem == 11)
                    res += 'B';
                else if (rem == 12)
                    res += 'C';
                else if (rem == 13)
                    res += 'D';
                else if (rem == 14)
                    res += 'E';
                else if (rem == 15)
                    res += 'F';
                else
                    res += rem;
            } else
                res += rem;

            num /= base;
        }
        // Reverse the result
        return new StringBuffer(res).reverse().toString();
    }

    static int convFrmBaseToDeci(String num, int base) {

        if (base < 2 || (base > 10 && base != 16))
            return -1;

        int val = 0;
        int power = 1;

        for (int i = num.length() - 1; i >= 0; i--) {
            int digit = digitToVal(num.charAt(i));

            if (digit < 0 || digit >= base)
                return -1;

            // Decimal equivalent is str[len-1]*1 +
            // str[len-1]*base + str[len-1]*(base^2) + ...
            val += digit * power;
            power = power * base;
        }

        return val;
    }

    static int digitToVal(char c) {
        if (c >= '0' && c <= '9')
            return (int) c - '0';
        else
            return (int) c - 'A' + 10;
    }

}
