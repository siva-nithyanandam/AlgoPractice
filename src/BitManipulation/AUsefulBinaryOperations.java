package BitManipulation;
/**
 * @time 5/19/24-6:46 PM
 * @author sivaprakashnithyanandam
 */

/**
 * https://leetcode.com/discuss/interview-question/5159716/all-types-of-patterns-for-bits-manipulations-and-how-to-use-it
 */
public class AUsefulBinaryOperations {

    public static void main(String[] args) {
        AUsefulBinaryOperations o = new AUsefulBinaryOperations();

        /**
         * Difference between upper case letter and lower case letter binary is that
         *         In upper case letter 5th bit!=1;
         *         In lower case letter 5th bit =1;
         *         cout<<char('A'|(1<<5))<<endl;
         *         cout<<char('a'&(~(1<<5)))<<endl;
         */

        //Convert Char to lower case;
        System.out.println((char)('A' | (1<<5)));
        System.out.println((char)('Z' | (1<<5)));
        System.out.println((char)('a' | (1<<5)));
        System.out.println("----------------1");

        //Convert Char to upper case;
        System.out.println((char)('a' & ~(1<<5)));
        System.out.println((char)('z' & ~(1<<5)));
        System.out.println((char)('A' & ~(1<<5)));
        System.out.println("----------------2");

        /**
         * Actually char of 1<<5 is _(space);
         *  Take any upper case letter and its |(or) with space will get the corresponding lower case letter;
         *  cout<<char('C'|' ')<<endl;   // will make it small c
         *
         * Take any lower case letter and its &(and) with _(underscore) will get the corresponding upper //case letter;
         *  cout<<char('c'&'_')<<endl;   // will make it capital C
         */
        System.out.println((char)('A' | (' ')));
        System.out.println((char)('a' & ('_')));
        System.out.println("---------------3");

        /**
         * Find a letter’s position in alphabet
         * We can easily find a letter’s position [1-26] in the
         * alphabet by taking its bitwise AND with ASCII 31 (00011111 in binary).
         * The case of the letter is irrelevant here.
         *
         *  eg.
         *  (‘A’ & 31) returns position 1
         *  (‘c’ & 31) returns position 3
         */
        System.out.println(Integer.toBinaryString('a'));
        System.out.println(Integer.toBinaryString('b'));
        System.out.println(Integer.toBinaryString(31));
        System.out.println('A' & 31);
        System.out.println('c' & 31);
        System.out.println("---------------4");

        /**
         * Swap with XOR.
         * int a=4;
         *   int b=5;
         *   a=a^b;
         *   b=b^a;
         *   a=a^b;
         */
        System.out.println("---------------5");

        /**
         * 8 ) For clearing the set bits upto ith bit
         *
         *  int i=4;
         * //clearing upto 5 the place;
         * int a=59;
         * int b=(a&(~((1<<(i+1))-1)));
         * //clearing the lsb upto ith bit;
         *
         *
         * i=3;
         * int c=(a&((1<<(i+1))-1));
         * //clearing the msb upto ith bit;
         */
        System.out.println("---------------6");

        /**
         * Some Medium level uses
         *
         * Set union A | B
         * Set intersection A & B
         * Set subtraction A & ~B
         * Set negation ALL_BITS ^ A or ~A
         * Set bit A |= 1 << bit
         * Clear bit A &= ~(1 << bit)
         * Test bit (A & 1 << bit) != 0
         * Extract last bit A&-A or A&~(A-1) or x^(x&(x-1))
         * Remove last bit A&(A-1)
         * Get all 1-bits ~0==-1
         */
        System.out.println("---------------7");

    }

    public void pr_binary(int num) {
        for (int i = 2; i >= 0; i--) {
            System.out.print((num >> i) & 1);
        }
        System.out.println();
    }
}
